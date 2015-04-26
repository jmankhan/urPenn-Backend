package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The 'Graph' in a graph search.
 * This serves as mostly an 'abstract' model that will need to implemented and have an algorithm applied to it.
 * @author Jalal
 *
 */
public class TourMap {

	private List<Node> nodes;
	
	/**
	 * Creates a new, empty TourMap (graph)
	 */
	public TourMap() {
		nodes = new ArrayList<Node>();
	}

	/**
	 * Adds a new Node to the graph
	 * @param n
	 */
	public void addNode(Node n) {
		
	}
	
	/**
	 * Adds a connection between two Nodes. Aka an Edge.
	 * @param from N1
	 * @param to N2
	 */
	public void addEdge(Node from, Node to) {
		
	}
	
	/**
	 * Adds a connection between two Nodes and specifies how difficult it is
	 * to traverse that 'connection', aka an Edge.
	 * @param from
	 * @param to
	 * @param cost
	 */
	public void addEdge(Node from, Node to, int cost) {
		
	}
	
	/**
	 * Finds and returns the Node with the given unique id
	 * @param id String id of Node
	 * @return Node n
	 */
	public Node getNode(String id) {
		return null;
	}
	
	/**
	 * Returns all the Nodes on this TourMap (graph)
	 * @return List<Node>
	 */
	public List<Node> getNodes() {
		return this.nodes;
	}
	
	/**
	 * Determins if the Node reference exists on this TourMap (graph)
	 * @param n Node to check
	 * @return true if is on map, false if not
	 */
	public boolean exists(Node n) {
		return false;
	}
	
	/**
	 * Returns the iterator of List<Node> nodes. Hopefully this doesn't conflict with getNodeList().iterator();
	 * @return Iterator<Node>
	 */
	public Iterator<Node> getIterator() {
		return this.nodes.iterator();
	}
}
