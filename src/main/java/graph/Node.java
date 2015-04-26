package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * A model of a Node in the Graph. This class will likely need to be extended by something to carry more information and be useful.
 * @author Jalal
 *
 */
public class Node {

	protected String id;
	protected Map<Node, Integer> edges;
	
	public Node(String id) {
		this.id = id;
		this.edges = new HashMap<Node, Integer>();
	}
	
	public String getId() {
		return this.id;
	}
	
	/**
	 * Create an edge between this Node and the specified parameter, with a cost to get there
	 * @param n
	 * @param cost
	 */
	public void addEdge(Node n, int cost) {
		this.edges.put(n, cost);
	}
	
	/**
	 * Returns a Map of all the edges connected to this Node and their associated costs 
	 * @return Map<Node, Integer>
	 */
	public Map<Node, Integer> getEdges() {
		return this.edges;
	}
	
	/**
	 * Returns the cost of traveling to the specified Node. Will return -1 if there is edge between this Node and the specified Node. 
	 * @param n Node to travel to
	 * @return int cost
	 */
	public int getCost(Node n) {
		if(!edges.containsKey(n.getId()))
			return -1;
		
		return edges.get(n.getId());
	}
	
	/**
	 * Set a new Id for this Node
	 * @param id new String id for this Node
	 */
	public void setId(String id) {
		this.id = id;
	}
}
