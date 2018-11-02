package structs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp
{
	//public static Graph g = new Graph(8);  //number of nodes in the graph is 8
	
	static Scanner sc = new Scanner(System.in);
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
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int numOfVertices;
		String cities = "";
		String edges = "";
		String [] citiesArray = null;
		String [] edgesArray=null;
		
		do {
			
			numOfVertices = noOfCitiesInput();
			switch (numOfVertices) {
				case 8:
					cities = read("cities8.txt",0);
					citiesArray = cities.split("\\|");
					edges = read("cities8.txt",1); 
					edgesArray = edges.split("\\|");
					break;
				case 13:
					cities = read("cities13.txt",0);
					citiesArray = cities.split("\\|");
					edges = read("cities13.txt",1); 
					edgesArray = edges.split("\\|");
					break;
				case 18:
					cities = read("cities18.txt",0);
					citiesArray = cities.split("\\|");
					edges = read("cities18.txt",1); 
					edgesArray = edges.split("\\|");
					break;
					
				default:
					System.out.println("Invalid input. Please try again!");
					sc.nextLine();
					numOfVertices = noOfCitiesInput();
					break;
				}
			sc.nextLine();
			for(int i=0;i<citiesArray.length;i++) {
				System.out.println(citiesArray[i]);
			}
			for(int i=0;i<edgesArray.length;i++) {
				System.out.println(edgesArray[i]);
			}
		
			} while ( numOfVertices <= 0  || (numOfVertices != 8 && numOfVertices != 13 && numOfVertices != 18));
	}
	
	public static int noOfCitiesInput() {
		try {
			System.out.println("Choose the number of cities");
			System.out.println("8");
			System.out.println("13");
			System.out.println("18");
			return sc.nextInt();
			
		}catch (InputMismatchException e) {
			return 0;
		}
		catch (NullPointerException ex) {
			return 0;
		}
	
	}


}