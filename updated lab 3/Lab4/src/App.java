import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean graphFlag = false;
		Graph2 graph = new Graph2();
		int option = 0;
		
		do {
			System.out.println("1. Select vertex size");
			System.out.println("2. Select shortest path");
			System.out.println("3. Quit");
			option = sc.nextInt();
			switch (option) {
				case 1:
					System.out.println("Choose number of cities: 8 / 13 / 18");
					int citySize = sc.nextInt();
					if (citySize == 8 || citySize == 13 || citySize == 18) {
						graph = new Graph2(citySize);
						graphFlag = true;
						break;
					}
					else {						
						System.out.println("Please input the correct number of cities");
						continue;
					}
				case 2:
					if (graphFlag == false)
						break;
					// Show available cities
					System.out.println("List of Cities available:");
					for (int x = 0; x < graph.numOfVertices; x ++) {
						System.out.println(graph.adj[x].getCity());
					}
					System.out.println("Input source city name:");
					sc.nextLine();
					String sourceName = sc.nextLine();
					System.out.println("Input destination city name:");
					String destinationName = sc.nextLine();
					graph.BFS(sourceName, destinationName);
					break;
				case 3: 
					System.out.println("Shutting down");
					break;
				default:
					System.out.println("Invalid option");
					break;
			}
		} while (option != 3);
		
		

	}

}
