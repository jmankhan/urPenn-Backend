package api;

import static org.junit.Assert.assertNotNull;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class YelpTest {

	private Yelp api = new Yelp();
	
	@Test
	public void testSearch() {
		String result = api.searchForBusinessesByLocation("food", "Center Valley, PA"); 
		assertNotNull(result);
	}
	
	@Test
	public void testJSONResult() {
		JSONObject result = new JSONObject(api.searchForBusinessesByLocation("food", "Center Valley, PA"));
		assertNotNull(result);
		
		JSONArray businesses = result.getJSONArray("businesses");
		JSONObject abusiness = businesses.getJSONObject(0);
		
		System.out.println(result);
		System.out.println(businesses);
		System.out.println(businesses.length() + " businesses in Center Valley");
		System.out.println(abusiness);
	}

}
