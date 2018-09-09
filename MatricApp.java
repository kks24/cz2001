import java.util.Scanner;

public class MatricApp {
	public static void main(String args[]) {
		
		Matric data = new Matric();
		
		while (true) {
			data.printHashTable();
			Scanner sc = new Scanner(System.in);
			System.out.println("Insert option");
			int option = sc.nextInt();
			System.out.println("Insert key");
			String key = sc.next();
			switch (option) {
			case 1: data.insertKey(key);
					break;
			case 2: int index = data.searchKey(key);
					System.out.println("Key found at index: " + index);
					break;
			case 3: data.deleteKey(key);
					break;
			default: System.out.println("Invalid option");
					break;
			}
		}
	}
}
