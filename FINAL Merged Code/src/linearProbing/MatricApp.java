package linearProbing;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import app.DoubleHashThread;
import app.LinearProbingThread;

public class MatricApp {
	
	public static int numberOfSearch = 0;
	
	public static void LinearProbingApp() {
		
		LinearProbing data = new LinearProbing();
		long totalCpuTime=0;
		//===============CPU TIME CHANGES STARTS HERE
		List <LinearProbingThread> threadList = new ArrayList<LinearProbingThread>();
		//===============CPU TIME CHANGES ENDS HERE (MORE BELOW)
		
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("========================== LINEAR PROBING MENU ==========================");
			System.out.println("Option 1: Search");
			System.out.println("Option 2: Add");
			System.out.println("Option 3: Delete");
			System.out.println("Option 4: Display Hashtable");
			System.out.println("Option 5: Display Average CPU time");
			System.out.println("Option 6: Display Average Key Comparsion");
			System.out.println("Option 7: Demo Load Factor");
			System.out.println("Option 8: Reset Average CPU time and Key Comparisons");
			System.out.println("Option 0: Back to main menu"); 
			System.out.print("Choice: ");
				int option = sc.nextInt();
				String key = null;
				switch (option) {
				case 2: 
					try {
						System.out.println("Enter Key:");
						key = sc.next();
						data.addKey(key);
					}
					catch (StringIndexOutOfBoundsException e) {
						System.out.println("Invaild Input! Please Try Again!.");
					break;
					}
					break;
				case 1: 
					try {
						System.out.println("Please Enter the Student ID ");
						key = sc.next();
						//int index = data.searchKey(key);
						threadList.add(0, new LinearProbingThread("Linear Probing Thread"));
						threadList.get(0).setDaemon(true);
						
						threadList.get(0).id =key;
						
						long startTime = System.nanoTime();
						threadList.get(0).start(); //START THREAD EXECUTION

						//TERMINATE THREAD AND REMOVE FROM LIST OF ACTIVE THREADS
						try 
						{
							threadList.get(0).join();
							threadList.remove(0);


						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						
						long elapsedTime = System.nanoTime() - startTime;
						System.out.println("CPU Time (microseconds): " + elapsedTime/100000);
						totalCpuTime+=elapsedTime;

						//===============CPU TIME CHANGES ENDS HERE
					}
					catch (StringIndexOutOfBoundsException e) {
						System.out.println("Invaild Input! Please Try Again!.");
					break;
					}
					break;
				case 3: 
					try {
					System.out.println("Enter Key:");
					key = sc.next();
					data.deleteKey(key);
					}
					catch (StringIndexOutOfBoundsException e) {
						System.out.println("Invaild Input! Please Try Again!.");
					break;
					}
					break;
				case 4: 
					data.printHashTable();
					break;
				case 5:
					
					if(numberOfSearch == 0) {
						System.out.println("Please Search for at least 1");
						break;
					}
					System.out.println("======Printing Average CPU time======");
					System.out.println("Total number of Search: "+numberOfSearch);
					System.out.println("Total CPU time: "+ totalCpuTime/100000+" ms");
					System.out.println("Average CPU time = "+(totalCpuTime/(numberOfSearch*100000))+" ms");

					break;
				case 6:
					if(data.getSearch() == 0) {
						System.out.println("Please Search for at least 1");
						break;
					}
					float AverageKeyComparison = (float)data.getKeyComparison()/(float)data.getSearch();
					System.out.println("Number of Searches: " + data.getSearch() +
										"\nNumber of Key Comparisons: " + data.getKeyComparison() +
										"\nAverage Key Comparison: " + AverageKeyComparison);
					break;
				case 7: 
					System.out.println("Enter the Load Factor");
					double LoadFactor=sc.nextDouble();
					data.NumGenerator(LoadFactor);
					break; 
				case 8:
					totalCpuTime = 0;
					numberOfSearch = 0;
					System.out.println("Total CPU time and number of searches have been reset.");	
				case 0: 
					break;
				default: System.out.println("Invalid option");
						break;
				}
				if (option == 0) break;
			}
			
		}
		
		
		
	}
