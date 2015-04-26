package graph;

import java.util.Iterator;
import java.util.Queue;

import main.Building;

import org.eclipse.jetty.util.ArrayQueue;


public class Route {

	protected ROUTE_ID id;
	protected Building[] stops;
	
	/**
	 * Create a new Route, specified by the id
	 * @param id
	 */
	public Route(ROUTE_ID id) {
		this(id, null);
	}
	
	/**
	 * Creates a new Route, specified by the id, and adds all the stops on this route
	 * @param id
	 * @param stops
	 */
	public Route(ROUTE_ID id, Building[] stops) {
		this.id = id;
		this.stops = stops;
	}
	
	/**
	 * Returns this Route's id
	 * @return enum id
	 */
	public ROUTE_ID getID() {
		return this.id;
	}

	/**
	 * Gets all the Buildings on this Route
	 * @param stops Building[] stops
	 */
	public void setStops(Building[] stops) {
		this.stops = stops;
	}

	/**
	 * Algorithm to find the shortest path to get to all the stops in this route
	 */
	public void sort(Building[] stops) throws Exception {
		if(this.stops == null) throw new Exception("no stops specified!");
		
		//set up a queue
		Queue<Building> q = new ArrayQueue<Building>();
		for(Building b:stops)
			q.add(b);

		//create an edge or "path", with an associated walking cost, between every building
		//O(n!) == O(n) i guess
		while(!q.isEmpty()) {
			Building next = q.poll();
			for(Building b : q) {
				next.addEdge(b, (int) calcCost(next, b));
			}
		}
		
		//figure out the shortest path
	}
	
	public double calcCost(Building n1, Building n2) {
		double x = Math.abs(n1.getLatitude() - n2.getLatitude());
		double y = Math.abs(n1.getLongitude() - n2.getLongitude());
		double hypotnuse = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		
		return hypotnuse;
	}
}