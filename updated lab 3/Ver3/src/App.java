import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean graphFlag = false;
		Graph2 graph = new Graph2();
		int option = 0;
		int citySize = 0;
		int numOfEdges = 0;
		
		do {
			System.out.println("1. Select based on vertex size");
			System.out.println("2. Select based on edge size");
			System.out.println("3. Select shortest path");
			System.out.println("4. Quit");
			option = sc.nextInt();
			switch (option) {
				case 1:
					System.out.println("Choose number of cities: 8 / 13 / 18");
					citySize = sc.nextInt();
					if (citySize == 8 || citySize == 13 || citySize == 18) {
						graph = new Graph2(citySize,0);
						graphFlag = true;
						break;
					}
					else {						
						System.err.println("Please input the correct number of cities");
						break;
					}
				case 2: 
					System.out.println("Choose number of cities: 8 / 13 / 18");
					citySize = sc.nextInt();
					if (citySize != 8 & citySize != 13 & citySize != 18) {
						System.err.println("Invalid City Size");
						break;
					}
					System.out.print("Input number of edges(Must be larger than city size): ");
					numOfEdges = sc.nextInt();
					if (numOfEdges >= citySize) {
						graph = new Graph2(citySize, numOfEdges);
						graphFlag = true;
						break;
					}
				case 3:
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
				case 4: 
					System.out.println("Shutting down");
					break;
				case 5:
					int sampleSize = 10000;
					int maxEdges = 21;
					long numOfRuns;
					long totalCPUTime;
					Graph2 testGraph;
					
					System.out.println("Based on vertex size & edge size, same source & destination");
					System.out.println("######################");
					for (int numOfVertices = 8; numOfVertices <= 18; numOfVertices += 5) {
						for (int edge = 17; edge <= maxEdges; edge ++) {
							System.out.println("@ Vertex Size: " + numOfVertices);
							System.out.println("@ Edge Size: " + edge);
							testGraph = new Graph2(numOfVertices, edge);
							// For Beijing and Tokyo
							numOfRuns = 0;
							totalCPUTime = 0;
							for (int i = 0; i < sampleSize; i ++) {
								testGraph.BFS("Beijing", "Tokyo",true);
								totalCPUTime += testGraph.cpuTime;
								numOfRuns ++;
							}
							System.out.println("Average CPUTime for Beijing-Tokyo: " + (totalCPUTime/numOfRuns));
							
							// For Singapore & Hong Kong
							numOfRuns = 0;
							totalCPUTime = 0;
							for (int i = 0; i < sampleSize; i ++) {
								testGraph.BFS("Singapore", "Hong Kong",true);
								totalCPUTime += testGraph.cpuTime;
								numOfRuns ++;
							}
							System.out.println("Average CPUTime for Singapore-Hong Kong: " + (totalCPUTime/numOfRuns));
							
							// Los Angeles & Tokyo
							numOfRuns = 0;
							totalCPUTime = 0;
							for (int i = 0; i < sampleSize; i ++) {
								testGraph.BFS("Los Angeles", "Tokyo",true);
								totalCPUTime += testGraph.cpuTime;
								numOfRuns ++;
							}
							System.out.println("Average CPUTime for Los Angeles-Tokyo: " + (totalCPUTime/numOfRuns));
							
							// Sydney & Singapore
							numOfRuns = 0;
							totalCPUTime = 0;
							for (int i = 0; i < sampleSize; i ++) {
								testGraph.BFS("Sydney", "Singapore",true);
								totalCPUTime += testGraph.cpuTime;
								numOfRuns ++;
							}
							System.out.println("Average CPUTime for Sydney-Singapore: " + (totalCPUTime/numOfRuns));
						}
					}
					
					System.out.println("Based on edge size & vertex size, random source & destination");
					System.out.println("######################");
					for (int numOfVertices = 8; numOfVertices <= 18; numOfVertices += 5) {
						for (int edge = 17; edge <= maxEdges; edge ++) {
							testGraph = new Graph2(numOfVertices, edge);
							System.out.println("@ Vertex Size: " + numOfVertices);
							System.out.println("@ Edge Size: " + edge);
							
							numOfRuns = 0;
							totalCPUTime = 0;
							for (int i = 0; i < sampleSize; i ++) {
								// Get 2 random source & destination
								int v1, v2;
								do {
									v1 = (int) (Math.random()*(numOfVertices - 1));
									v2 = (int) (Math.random()*(numOfVertices - 1));
								} while (v1 == v2);
								testGraph.BFS(testGraph.adj[v1].getCity(), testGraph.adj[v2].getCity(), true);
								totalCPUTime += testGraph.cpuTime;
								numOfRuns ++;
							}
							System.out.println("Average CPUTime: " + (totalCPUTime/numOfRuns));
						
						}
					}
					System.out.println();
					break;
				default:
					System.out.println("Invalid option");
					break;
			}
		} while (option != 4);
		
		

	}

}
