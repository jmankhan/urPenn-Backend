package api;

import static org.junit.Assert.*;

import org.junit.Test;

public class YelpTest {

	@Test
	public void testSearch() {
		Yelp api = new Yelp();
		String result = api.searchForBusinessesByLocation("food", "Center Valley, PA"); 
		assertNotNull(result);
		
		System.out.println(result);
		
		
	}

}
