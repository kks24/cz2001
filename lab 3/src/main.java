import java.util.Scanner;

import structs.Graph;

public class main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		 Graph g = new Graph(8); 
		 	Scanner sc = new Scanner(System.in);
	        //Beijing -> HK
		 	g.addEdge(0, 1);
	        g.addEdge(1, 0);
	        
	        //Beijing -> Singapore
		 	g.addEdge(0, 2);
	        g.addEdge(2, 0);
	        
	        //Beijing -> Singapore
	        g.addEdge(0, 2);
	        g.addEdge(2, 0);
	        
	        //Beijing -> Shanghai
		 	g.addEdge(0, 4);
	        g.addEdge(4, 0);
	        
	        //Beijing -> Tokyo
		 	g.addEdge(0, 6);
	        g.addEdge(6, 0);
	        
	        //HK -> Shanghai
	        g.addEdge(1, 4);
	        g.addEdge(4, 1);
	        
	        //HK -> Singapore
	        g.addEdge(1, 2);
	        g.addEdge(2, 1);
	        
	        //Singapore -> Shanghai
	        g.addEdge(2, 4);
	        g.addEdge(4, 2);
	        
	        //Singapore -> Sydney
	        g.addEdge(2, 3);
	        g.addEdge(3, 2);
	        
	        //Shanghai -> Tokyo
	        g.addEdge(4, 6);
	        g.addEdge(6, 4);
	        
	        //Shanghai -> Seoul
	        g.addEdge(4, 5);
	        g.addEdge(5, 4);
	        
	        //Seoul -> Tokyo
	        g.addEdge(5, 6);
	        g.addEdge(6, 5);
	        
	        //Seoul -> Singapore
	        g.addEdge(5, 2);
	        g.addEdge(2, 5);
	        
	        //Tokyo -> Los Angeles
	        g.addEdge(5, 7);
	        g.addEdge(7, 5);
	        
	        //Los Angeles -> Tokyo
	        g.addEdge(7, 6);
	        g.addEdge(6, 7);
	        
	        System.out.println("Following is Breadth First Traversal "+ 
	                           "(starting from vertex 2)"); 
	        System.out.println("Enter your source city");
	        String source = sc.nextLine();
	        System.out.println("Enter your destination");
	        String destination = sc.nextLine();
	        g.BFS(source,destination); 
	}

}
