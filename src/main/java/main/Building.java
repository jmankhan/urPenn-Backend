package main;

import graph.Node;

/**
 * An implementation of the Node class that will be used to create a TourMap
 * @author Jalal
 *
 */
public class Building extends Node {

	private String absUrl;
	private String thumbUrl;
	private String blurb;

	private double lati, longi;
	
	public Building() {
		this(null, null);
	}
	
	public Building(String absUrl) {
		this(absUrl, null);
	}
	
	public Building(String absUrl, String thumbUrl) {
		this(null, absUrl, thumbUrl);
	}
	
	public Building(String name, String absUrl, String thumbUrl) {
		super(name);

		this.absUrl = absUrl;
		this.thumbUrl = thumbUrl;
		this.id = name;
		this.blurb = "blurb";
	}

	public void setAbsUrl(String abs) {
		this.absUrl = abs;
	}
	
	public void setThumbUrl(String thumb) {
		this.thumbUrl = thumb;
	}
	
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	
	public void setCoordinates(double lati, double longi) {
		this.lati = lati;
		this.longi = longi;
	}

	public String getAbsUrl() {
		return this.absUrl;
	}
	
	public String getThumbUrl() {
		return this.thumbUrl;
	}
	
	public String getBlurb() {
		return this.blurb;
	}

	public double getLongitude() {
		return this.longi;
	}
	
	public double getLatitude() {
		return this.lati;
	}
	@Override
	public String toString() {
		return id + "\n" + absUrl + "\n" + thumbUrl + "\n" + blurb + "\n"; 
	}
}
