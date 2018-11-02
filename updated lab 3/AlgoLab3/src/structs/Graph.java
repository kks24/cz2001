package structs;
import java.io.*;
import java.util.*;
import structs.Node;
public class Graph {
	 private int numberOfVertices;   // No. of vertices 
	    Node[] adj ; //Adjacency Lists 
	    LinkedList<String> cname = new LinkedList();
	    int numberOfNodes = 0;
	    int countEdge=0;
	    // Constructor 
	    Graph(int numberOfNodes) 
	    { 
	        numberOfVertices = numberOfNodes;
	    	adj=new Node[numberOfNodes];
	        this.numberOfNodes = numberOfNodes;
	        init();
	        for (int i=0; i<numberOfNodes; i++)
	        {
	        	adj[i]=new Node(cname.get(i),i); //add new nodes based on city names and index
	        }
	    }
	  
	    // Function to add an edge into the graph 
	    void addEdge(int v,int w) 
	    { 
	        Node n = adj[v];
	        n.edges.add(w);
	        System.out.println("Successfully add"+v+" and "+w);
	    } 
	  
	    // prints BFS traversal from a given source s 
	    void BFS(String sourceName , String destinationName) 
	    { 
	    	int source = cname.indexOf(sourceName);
	    	if(source == -1)
	    	{
	    		System.out.println("The source city is not found , please try again");
	    		return;
	    	}
	    	int destination = cname.indexOf(destinationName);
	    	if(destination ==-1 ) 
	    	{
	    		System.out.println("The destination city is not found , please try again");
	    		return;
	    	}
	        boolean visited[] = new boolean[numberOfVertices]; 
	  
	        // Create a queue for BFS 
	        LinkedList<Integer> queue = new LinkedList<Integer>(); 
	        
	        // Mark the current node as visited and enqueue it 
	        visited[source]=true; 
	        queue.add(source); 
	  
	        int dequeuedIndex;
	        
	        long startTime = System.nanoTime();
	        
	        while (queue.size() != 0) 
	        { 
	            // Dequeue a vertex from queue and print it 
	        	dequeuedIndex = queue.poll();  //removes the head of the queue from the queue, and set it to s
	            System.out.println("Dequeued node index "+ dequeuedIndex); 
	  
	            // Get all adjacent vertices of the dequeued vertex s 
	            // If a adjacent has not been visited, then mark it 
	            // visited and enqueue it 
	            LinkedList<Integer> targetList = adj[dequeuedIndex].edges;
	            
	            for(int j=0;j<targetList.size();j++) 
	            {
	            	System.out.println("Target node" + adj[targetList.get(j)].name);
	            	Node targetNode = adj[targetList.get(j)];
	            	
	            	if(visited[targetList.get(j)]==false)  //if never visited before
	            	{
	            		queue.add(targetList.get(j));
	            		System.out.println("Adding to queue index " + targetList.get(j));
	            		
	            		visited[targetList.get(j)]=true;
	            		//adj[targetList.get(j)].nodeLength=adj[source].nodeLength+1;
	            		
	            		//updating of this node's path distance
	            		Node previousNode = adj[dequeuedIndex];
	            		targetNode.nodeLength= previousNode.nodeLength+1; 
	            		System.out.println("Node length is now set to " + targetNode.nodeLength +"\n");
	            	}
	            	else
	            	{
	            		int tempNodeLength = adj[dequeuedIndex].nodeLength+1;
	            		
	            		if(tempNodeLength<targetNode.nodeLength)  //if new path is shorter, replace
	            		{
	            			System.out.println("Entered else, Previous node length is" + targetNode.nodeLength);
	            			targetNode.nodeLength=tempNodeLength;
	            			System.out.println("New node length is " + targetNode.nodeLength);
	            		}
	            	}
	            }
	        }
	        
	        long endTime = System.nanoTime();
	        
	        System.out.println("destination id: " + destination);
	        System.out.println("Maximum size of array: " + adj.length);
	       
	        System.out.println("The source city to the destination city 's route = "+adj[destination].nodeLength);
	        System.out.println("CPU Time: " + (endTime - startTime)/1000000 + "milliseconds");
	        
	    }
	    
	    void init ()
	    {    		
	 	    String s;
	 	    
	 	    //lab provided
	 	    s = "Beijing"; //0
	 	    cname.add(s);
	 	    s = "Hong Kong"; //1
	 	    cname.add(s);
	 	    s = "Singapore"; //2
	 	    cname.add(s);
	 	    s = "Sydney"; //3
	 	    cname.add(s);
	 	    s = "Shanghai"; //4
	 	    cname.add(s);
	 	    s = "Seoul"; //5
	 	    cname.add(s);
	 	    s = "Tokyo"; //6
	 	    cname.add(s);
	 	    s = "Los Angeles"; //7
	 	    cname.add(s);
	 	    
	 	    //not lab provided
//	 	    s = "Vienna"; //8
//	 	    cname.add(s);
//	 	    s = "Cairo"; //9
//	 	    cname.add(s);
//	 	    s = "Santiago";
//	 	    cname.add(s);
//	 	    s = "New Delhi";
//	 	    cname.add(s);
//	 	    s = "Jakarta";
//	 	    cname.add(s);
//	 	    s = "Budapest";
//	 	    cname.add(s);
//	 	    s = "Rome";
//	 	    cname.add(s);
//	 	    s = "Kuala Lumpur";
//	 	    cname.add(s);
//	 	    s = "Amsterdam";
//	 	    cname.add(s);
//	 	    s = "Bangkok";
//	 	    cname.add(s);
//	 	    s = "Colombo";
//	 	    cname.add(s);
//	 	    s = "Taipei";
//	 	    cname.add(s);
//	 	    s = "Manila";
//	 	    cname.add(s);
//	 	    s = "Vatican City";
//	 	    cname.add(s);
//	 	    s = "Cardiff";
//	 	    cname.add(s);
//	 	    s = "Hanoi";
//	 	    cname.add(s);
//	 	    s = "Ottawa";
//	 	    cname.add(s);
//	 	    s = "Havana";
//	 	    cname.add(s);
//	 	    s = "Baghdad";
//	 	    cname.add(s);
//	 	    s = "Lilongwe";
//	 	    cname.add(s);
//	 	    s = "Mexico City";
//	 	    cname.add(s);    	
	    }
	    
}
