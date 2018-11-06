import java.util.LinkedList;

public class Node {
	// Cities name of the node
	private String cname;
	// Mainly for back-end methods to keep track of nodes
	private int nodeIndex;
	
	// To contain the nodexIndex(index of city node) that is directly connected to the node
	public LinkedList<Integer> edges = new LinkedList();
	
	// Represent the shortest distance from sourcename, used for BFS
	public int nodeLength = 0;
	
	// For back tracking and obtaining the shortest route
	public int previousIndex = -1;
	
	// Constructor
	public Node(String cname, int nodeIndex) {
		this.cname = cname;
		this.nodeIndex = nodeIndex;
	}
	
	public void addEdge(int connectedNode) {
		edges.add(connectedNode);
	}
	
	// Assessor 
	public int getNodeIndex() {
		return nodeIndex;
	}
	public String getCity() {
		return cname;
	}

}
