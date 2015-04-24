import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Scraper {

	private String url;

	public Scraper(String url) {
		this.url = url;
	}

	/**
	 * Finds and returns all relative links to each building
	 * @return ArrayList<Element>
	 * @throws Exception if invalid url
	 */
	public ArrayList<Building> getAllBuildingLinks() throws Exception {
		if(!url.contains("locations")) throw new IllegalArgumentException("invalid url");

		ArrayList<Building> allBuildings = new ArrayList<Building>();
		int page = 0;

		try {
			Document doc = Jsoup.connect(this.url).timeout(10*1000).get();

			//keep looping until last page is hit
			while(doc.getElementsByClass("pager-next").size() > 0) {

				Elements buildingTable = doc.getElementsByClass("view-content");

				Elements building = buildingTable.get(1).getElementsByAttribute("href");

				//create Building object and populate it with links
				Building b = new Building();
				if(building.size() >= 2) {
					b.setThumbUrl(building.get(0).absUrl("href"));
					b.setAbsUrl(building.get(1).absUrl("href"));
					b.setName(getPageTitle(b.getAbsUrl()));
					b.setBlurb(getPByClass(b.getAbsUrl(), "field-item"));
				}

				allBuildings.add(b);
				//go to next page
				doc = Jsoup.connect("http://www.facilities.upenn.edu/maps/locations?page="+ ++page).get();
			}
		} catch(IOException e) {e.printStackTrace();}

		return allBuildings;
	}

	/**
	 * Finds a paragraph based on the url and the containing class
	 * @param url absolute url
	 * @param className name of containing element's class
	 * @return String paragraph
	 * @throws IOException if url cannot be accessed
	 */
	public String getPByClass(String url, String className) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements ele = doc.getElementsByClass(className);
		if(ele.size() > 0) {
			Element para = ele.select("p").first();
			return para.text();
		}
			return "error";
		
	}

	
	/**
	 * A simple method that retrieves the title of the page
	 * @param url String url of page
	 * @return String title
	 * @throws IOException if page cannot be accessed
	 */
	public String getPageTitle(String url) throws IOException {
		Document doc = Jsoup.connect(url).timeout(10*1000).get();
		return doc.title();
	}
	/**
	 * Saves a file of String values delineated by newline
	 * @param filename String name of file. Will be created if it doesn't exist
	 * @param content ArrayList<String> content to write to file
	 * @param overwrite boolean to allow/disallow overwriting previously existing file 
	 * @throws FileNotFoundException if file could not be accessed
	 */
	public void saveToFile(String filename, ArrayList<String> content, boolean overwrite) throws FileNotFoundException {
		File file = new File(filename);
		PrintWriter out = new PrintWriter(new FileOutputStream(file, overwrite));
		
		if(content == null || content.isEmpty()) {out.close(); return;}

		Iterator<String> it = content.iterator();
		
		while(it.hasNext()) {
			out.println(it.next());
		}
		
		out.close();
	}
	
	public void deleteFile(String filename) {
		File file = new File(filename);
		file.delete();
	}
	
	/**
	 * Saves all buildings urls to file
	 * @param buildings
	 */
	public void saveBuildingsToFile(ArrayList<Building> buildings) {
		ArrayList<String> buildingInfo = new ArrayList<String>();
		for(Building b:buildings) {
			buildingInfo.add(b.toString());
		}
		
		try {
			
			saveToFile("buildings.txt", buildingInfo, true);
			
		} catch (FileNotFoundException e) {e.printStackTrace(); System.out.println("saving buildings to file failed");}
	}

	/**
	 * Reads all contents from a file, reads a string until newline
	 * @param filename String name of file to read
	 * @return ArrayList<String> contents, delineated by newline
	 * @throws IOException if file could not be accessed
	 */
	public ArrayList<String> readFromFile(String filename) throws IOException {
		
		ArrayList<String> contents = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader(filename));
		
		String line = in.readLine();
		while(line != null) {
			contents.add(line);
			line = in.readLine();
		}
		
		in.close();
		
		return contents;
	}
	
	/**
	 * Get current base url as string
	 * @return String
	 */
	public String getURL() {
		return this.url;
	}

}
