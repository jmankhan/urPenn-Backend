package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import main.Building;
import main.Scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;


public class ScraperTest {

	@Test
	public void testConstructor() {
		Scraper scrape = new Scraper("http://www.facilities.upenn.edu/maps/locations");
		assertEquals("http://www.facilities.upenn.edu/maps/locations", scrape.getURL());
	}
	
	@Test
	public void testWebsiteConnect() {
		Document doc;
		try {
			doc = Jsoup.connect("http://www.upenn.edu").get();
			assertNotNull(doc);
		} catch (IOException e) {e.printStackTrace();}

	}

	@Ignore @Test
	public void testWebsitePaginatedScraper() {
		try {
			int page = 0;
			Document doc = Jsoup.connect("http://www.facilities.upenn.edu/maps/locations").get();
			assertNotNull(doc);
			while(doc.getElementsByClass("pager-next").size() > 0) {
				doc = Jsoup.connect("http://www.facilities.upenn.edu/maps/locations?page="+ ++page).timeout(10*1000).get();
				assertNotNull(doc);
			}
			
		} catch (IOException e) {e.printStackTrace();}
	}
	
	@Test
	public void testGetPByClass() {
		Scraper scrape = new Scraper("http://www.facilities.upenn.edu/maps/locations");
		try {
			System.out.println(scrape.getPByClass("http://www.facilities.upenn.edu/maps/locations/fagin-hall-claire-m", "field-item"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveToFile() {
		Scraper scrape = new Scraper("http://www.upenn.edu");
		ArrayList<String> content = new ArrayList<String>();
		content.add("building 1 abs");
		content.add("building 1 thumb");
		content.add("building 2 abs");
		content.add("building 2 thumb");
		content.add("building 3 abs");
		content.add("building 3 thumb");
		
		try {
			scrape.saveToFile("test buildings.txt", content, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadFromFile() {
		Scraper scrape = new Scraper("http//www.upenn.edu");
		ArrayList<String> content = new ArrayList<String>();
		content.add("building 1 abs");
		content.add("building 1 thumb");
		content.add("building 2 abs");
		content.add("building 2 thumb");
		content.add("building 3 abs");
		content.add("building 3 thumb");
		
		try {
			ArrayList<String> rcontent = scrape.readFromFile("test buildings.txt");
			for(int i=0; i<content.size(); i++) {
				assertEquals(content.get(i), rcontent.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testElementStuff() throws IOException {
		Scraper scraper = new Scraper("http://www.facilities.upenn.edu/maps/locations/fagin-hall-claire-m");
		System.out.println(scraper.getPByClass("http://www.facilities.upenn.edu/maps/locations/fagin-hall-claire-m", "field-name-field-short-description"));
	}
	
	@Test
	public void testBuildings() throws IOException {
		Document doc = Jsoup.connect("http://www.facilities.upenn.edu/maps/locations?page=12").get();
		Elements buildingTable = doc.getElementsByClass("view-content");
		Elements buildings = buildingTable.get(1).getElementsByAttribute("href");
		Iterator<Element> it = buildings.iterator();

		ArrayList<Building> all = new ArrayList<Building>();
		while(it.hasNext()) {
			Building b = new Building();
			b.setThumbUrl(it.next().absUrl("href"));
			if(it.hasNext()) {
				b.setAbsUrl(it.next().absUrl("href"));
			}
				
			all.add(b);
		}
		for(Building b : all) {
			System.out.println(b);
		}
	}
}
