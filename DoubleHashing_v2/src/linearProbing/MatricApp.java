package linearProbing;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import app.DoubleHashThread;

public class MatricApp {
	public static void LinearProbingApp() {
		
		LinearProbing data = new LinearProbing();
		while (true) {
			data.printHashTable();
			Scanner sc = new Scanner(System.in);
			System.out.println("Insert option : \n"
					+ "1. Insert Matric Number (Eg. UXXXXXXXA)\n"
					+ "2. Search Matric Number (Eg. UXXXXXXXA)\n"
					+ "3. Delete Matric Number (Eg. UXXXXXXXA)");
			try { 
				int option = sc.nextInt();
				String key = null;
				switch (option) {
				case 1: 
						System.out.println("Enter Key:");
						key = sc.next();
						data.addKey(key);
						break;
				case 2: 
						System.out.println("Enter Key:");
						key = sc.next();
						int index = data.searchKey(key);
						if (index == -1)
							System.out.println("No Key Found");
						else
							System.out.println("Key found at index: " + index);
						break;
				case 3: 
						System.out.println("Enter Key:");
						key = sc.next();
						data.deleteKey(key);
						break;
				default: System.out.println("Invalid option");
						break;
				}
			}
			catch (StringIndexOutOfBoundsException e) {
				System.out.println("Invaild Input! Program Exiting.");
	        	break;
			}
			catch (InputMismatchException e) {
				System.out.println("Invaild Input! Program Exiting.");
	        	break;
			}
		}
		
		
		
	}

}
