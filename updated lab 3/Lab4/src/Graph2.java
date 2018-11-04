import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph2 {
	// Total number of nodes/vertices
	public int numOfVertices;
	// Adjacency Lists
	public Node[] adj;
	// List of cities
	String[] clist;
	// List of edges
	String[] elist;
	
	public Graph2() {};
	
	public Graph2(int numOfVertices) {
		// Initialise the adjacency list and its size
		this.numOfVertices = numOfVertices;
		adj = new Node[numOfVertices];
		
		// Extracting vertices and edges from files
		String cities = read(("cities"+Integer.toString(numOfVertices)+".txt"),0);
		clist = cities.split("\\|");
		String edges = read(("cities"+Integer.toString(numOfVertices)+".txt"),1); 
		elist = edges.split("\\|");
		
		// Initialise Vertices (Add in cities name and index)
		for (int i = 0; i < numOfVertices; i ++) {
			adj[i] = new Node(clist[i], i);
		}
		
		// Initialise Edges
		for (int j = 0; j < elist.length; j ++) {
			String[] temp = elist[j].split(",");
			int v1 = findNodeIndex(temp[0]);
			int v2 = findNodeIndex(temp[1]);
			if (v1 != -1 & v2 != -1) {
				insertEdges(v1,v2);
			}
		}
	}
	
	public void insertEdges(int v1, int v2) {
		adj[v1].addEdge(v2);
		adj[v2].addEdge(v1);
	}
	
	public void BFS(String sourceName, String destinationName) {
		// Reset all data in node
		for (int count = 0; count < numOfVertices; count ++) {
			adj[count].nodeLength = 0;
			adj[count].previousIndex = -1;
		}
		
		
		// Get nodeIndex of sourcename
		int source = findNodeIndex(sourceName);
		if (source == -1) {
			System.out.println("Invalid source city, please retry");
			return;
		}
		// Get nodeIndex of destinationName
		int destination = findNodeIndex(destinationName);
		if (destination == -1) {
			System.out.println("Invalid destination city, please retry");
		}
		
		boolean visited[] = new boolean[numOfVertices];
		
		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>(); 
		
		// Mark the current node as visited and enqueue it 
        visited[source]=true; 
        queue.add(source); 
        
        int dequeuedIndex;
        
        // Start CPU Time
        long startTime = System.nanoTime();
        
        while (queue.size() != 0) {
        	// Dequeue a vertex from queue
        	dequeuedIndex = queue.poll();
        	
        	// Get all adjacent vertices of the dequeued vertex
        	LinkedList<Integer> targetList = adj[dequeuedIndex].edges;
        	
        	for (int j = 0; j < targetList.size();j++) {
        		Node targetNode = adj[targetList.get(j)];
        		
        		// If never visited before, add into the queue
        		if (visited[targetList.get(j)] == false) {
        			queue.add(targetList.get(j));
        			// Mark the vertex as visited
        			visited[targetList.get(j)] = true;
        			
        			// update node's path distance
        			Node previousNode = adj[dequeuedIndex];
        			targetNode.nodeLength= previousNode.nodeLength+1; 
        			targetNode.previousIndex = previousNode.getNodeIndex();
        		}
        		// Visited node
        		else {
        			int tempNodeLength = adj[dequeuedIndex].nodeLength+1;
            		
            		if(tempNodeLength<targetNode.nodeLength)  //if new path is shorter, replace
            			targetNode.nodeLength=tempNodeLength;
        		}
        	}
        }
		long endTime = System.nanoTime();
	     
		
	    System.out.println("Number of stops (inclusive of destination) = "+adj[destination].nodeLength);
	    System.out.print("Route: ");
	    LinkedList<String> route = new LinkedList<String>();
	    
	    int tempIndex = destination;
	    while (tempIndex != -1) {
	    	route.addFirst(adj[tempIndex].getCity());
	    	tempIndex = adj[tempIndex].previousIndex;
	    }
	    for (int x = 0; x < route.size(); x ++) {
	    	System.out.print(route.get(x));
	    	if (x + 1 < route.size())
	    		System.out.print(" => ");
	    }
	    System.out.println();

	    System.out.println("CPU Time: " + (endTime - startTime) + " nanoseconds");
	}
	
	public int findNodeIndex(String cname) {
		for (int i = 0; i < numOfVertices; i ++) {
			if (clist[i].equals(cname))
				return i;
		}
		return -1;
	}
	
	// Extract data from files
	public static String read(String fileName, int line) {
		
		String content = "";
		try {
			content = Files.readAllLines(Paths.get(fileName)).get(line);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
