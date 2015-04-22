import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.json.JSONArray;
import org.json.JSONObject;

import api.Yelp;

public class Main extends HttpServlet {
	private Yelp api;

	
	@Override
	public void init() throws ServletException {
		super.init();
		api = new Yelp();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//setup string to collect request
		StringBuilder requestBuilder = new StringBuilder();
		
		//get request headers
		Enumeration<String> headerNames = req.getHeaderNames();
		
		while (headerNames.hasMoreElements()) {

			String headerName = headerNames.nextElement();
			
			//find the necessary headers
			if(headerName.equalsIgnoreCase("request")) {
				Enumeration<String> headers = req.getHeaders(headerName);
				while (headers.hasMoreElements()) {
					
					//add them to stringbuilder for later parsing
					requestBuilder.append(headers.nextElement());
				}
			}
		}

		//parse header
		String request = requestBuilder.toString();
		JSONArray result = searchRequest(request);
		
		PrintWriter out = resp.getWriter();
		out.write(result.toString());
	}

	public JSONArray searchRequest(String term) {
		String jsonresult = api.searchForBusinessesByLocation(term, "Allentown, PA");
		return parseRequest(jsonresult);
	}

	public JSONArray parseRequest(String result) {
		
		//receive an object that contains an array of all businesses found
		JSONObject obj = new JSONObject(result);
		
		//retrieve each one as its own object and parse only name and snippet from it
		//use keyword 'businesses'
		JSONArray businesses = obj.getJSONArray("businesses");
		
		//populate parsed array with specific info
		JSONArray parsed = new JSONArray();
		for(int i=0; i<businesses.length(); i++) {
			JSONObject business = businesses.getJSONObject(i);
			JSONObject parsedBusiness = new JSONObject();
			
			parsedBusiness.put("name", business.get("name"));
			parsedBusiness.put("snippet_text", business.get("snippet_text"));
			parsedBusiness.put("location", business.get("location"));
			parsed.put(parsedBusiness);
		}
		
		return parsed;
	}
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(Integer.valueOf(System.getenv("PORT")));
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		context.addServlet(new ServletHolder(new Main()),"/*");
		server.start();
		server.join();
	}
}
