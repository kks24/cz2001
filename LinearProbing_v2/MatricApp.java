import java.util.InputMismatchException;
import java.util.Scanner;

public class MatricApp {
	public static void main(String args[]) {
		
		LinearProbing data = new LinearProbing();
		
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("##############################\n"
					+ "Insert option : \n"
					+ "1. Insert Matric Number (Eg. UXXXXXXXA)\n"
					+ "2. Search Matric Number (Eg. UXXXXXXXA)\n"
					+ "3. Delete Matric Number (Eg. UXXXXXXXA)\n"
					+ "4. View Hashtable\n"
					+ "5. Get Average key comparisons\n"
					+ "##############################"); 
				int option = sc.nextInt();
				String key = null;
				switch (option) {
				case 1: 
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
				case 2: 
						try {
							System.out.println("Enter Key:");
							key = sc.next();
							int index = data.searchKey(key);
							if (index == -1)
								System.out.println("No Key Found");
							else
								System.out.println("Key found at index: " + index);
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
						float AverageKeyComparison = (float)data.getKeyComparison()/(float)data.getSearch();
						System.out.println("Number of Searches: " + data.getSearch() +
											"\nNumber of Key Comparisons: " + data.getKeyComparison() +
											"\nAverage Key Comparison: " + AverageKeyComparison);
						break;
				default: System.out.println("Invalid option");
						break;
				}
			}
			
		}
		
		
