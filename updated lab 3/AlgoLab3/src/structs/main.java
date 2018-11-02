package structs;

import java.util.Scanner;

public class main 
{
	public static Graph g = new Graph(8);  //number of nodes in the graph is 8
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		int sEdge;
		int dEdge;
		System.out.println("Welcome"); 
		
		int choice;
		do 
		{
			//initialise test edges from the lab manual
			//COMMENT THIS OUT when you want the user to completely decide ALL edges
			initTestEdges();
			
			System.out.println("------------MENU---------------");
			System.out.println("1)Enter more edges");
			System.out.println("0)Find shortest route between two nodes");
			choice = sc.nextInt();
			
			if(choice==1) 
			{
				System.out.println("Enter the node index for source edge");
				sEdge=sc.nextInt();
				
				System.out.println("Enter the node index for destination edge");
				dEdge=sc.nextInt();
				
				g.addEdge(sEdge, dEdge); //add both directions for an undirected graph
				g.addEdge(dEdge, sEdge);
			}
			
			else 
			{
				if(choice!=0) 
				{
					System.out.println("Wrong choice try again");
				}
			}
		}while(choice!=0);
		
		sc.nextLine(); //clear buffer
		
		System.out.println("Enter your source city");
        String source = sc.nextLine();

        System.out.println("Enter your destination");
        String destination = sc.nextLine();
        
        g.BFS(source,destination);
	}

	private static void initTestEdges() 
	{
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
        
        //System.out.println("Following is Breadth First Traversal "+ 
                "(starting from vertex 2)"); 
		
	}

}
