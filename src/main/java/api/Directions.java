package api;

import java.io.IOException;

import main.Building;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Directions {
	
	private String API_KEY = "AIzaSyCKWjF-MBrdTr7RQCTjY-sJlbPK8wwhuaY";
	private String baseUrl;
	private String fullUrl;
	
	public Directions(Building[] stops) {
		
		baseUrl = "https://maps.googleapis.com/maps/api/directions/json?";
		fullUrl = baseUrl;

		String[] waypoints = sanitize(stops);
		
		String origin = waypoints[0];
		String destination = waypoints[waypoints.length-1];
		
		fullUrl += "origin=" + origin;
		fullUrl += "&destination=" + destination;
		fullUrl += "&waypoints=optimize:true";
		for(String s:waypoints) 
			fullUrl += "|"+s;
		fullUrl += "&key="+API_KEY;
		fullUrl += "&mode=walking";
	}
	
	public String[] sanitize(Building[] stops) {
		String[] ids = new String[stops.length];

		for(int i=0; i<stops.length; i++) {
			ids[i] = new String("");
			ids[i] = stops[i].getId().replace(" ", "+");
		}
		
		return ids;
	}
	
	public JSONObject sendRequest(String url) throws IOException {
		Document doc = Jsoup.connect(url).ignoreContentType(true).get();
		return new JSONObject(doc.text());
	}
	
	public String getUrl() {
		return this.fullUrl;
	}
	
	public void parseJSON(JSONObject obj) {
		
	}
}
