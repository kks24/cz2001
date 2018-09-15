import java.util.Objects;

public class LinearProbing {
	// Variables & Constants
		private static final int SIZE = 7; 		// Size should be a prime number
		private String[] hashTable;
		
		// Constructor 
		public LinearProbing() {
			this(SIZE);
		}
		
		public LinearProbing(int H) {
			hashTable = new String[H];
			for (int counter = 0; counter < H; counter ++) {
				hashTable[counter] = null;
				// Let null be empty and Deleted be tombstone
			}
		}
		
		// Hashing functions
		public int hashing(String key) {
			long longKey = convertKey(key);
			int result = (int)(longKey % SIZE);
			return result;
		}
		
		public int rehashing(int j) {
			return ((j + 1) % SIZE);
		}
		
		// Add key
		public void addKey(String key) {
			int index = hashing(key);
			System.out.println("Storing at index: " + index);
			for (int numOfSearch = 0; numOfSearch < SIZE; numOfSearch ++) {
				if (Objects.equals(hashTable[index], null) == true || Objects.equals(hashTable[index],"Deleted") == true) {
					hashTable[index] = key;
					System.out.println("Key added.");
					return;
				}
				else if (Objects.equals(hashTable[index],key) == true) {
					System.out.println("Key already added.");
					return;
				}
				else {  //Sample data to use this 1)U1720777D and 2)U1720777K
					index = rehashing(index);
					System.out.println("Index Occupied, storing at next index: " + index);
				}
			}
			System.out.println("Hash table is full.");
		}
		
		// Search key
		public int searchKey(String key) {
			numOfSearch ++;
			int index = hashing(key);
			// check until all positions are checked
			for (int numOfSearch = 0; numOfSearch < SIZE; numOfSearch ++) {
				System.out.println("Checking pos " + index);
				// if match is found
				if (Objects.equals(hashTable[index],key) == true) {
					numOfKeyComparison ++;
					return index;
				}
				else if (Objects.equals(hashTable[index],null) == true) {
					// Checking if link list is NULL is not counted as a key comparison
					return -1;
				}
				// If different matric or "deleted"
				else {
					numOfKeyComparison ++;
					index = rehashing(index);
				}
			}
			return -1;
		}
		
		// Delete key
		public void deleteKey(String key) {
			int index = hashing(key);
			for (int numOfSearch = 0; numOfSearch < SIZE; numOfSearch ++) {
				if (Objects.equals(hashTable[index],key) == true) {
					hashTable[index] = "Deleted";
					System.out.println("Item removed");
					return;
				}
				else if (Objects.equals(hashTable[index],"Deleted") == true) {
					index = rehashing(index);
				}
				else {
					System.out.println("Item not found.");
					return;
				}
			}
				System.out.println("Item not found.");
		}
		
		// Other functions
		public long convertKey(String key) {
			char firstChar = key.charAt(0);
			char lastChar = key.charAt(key.length()-1);
			String resultKey = (int)firstChar + key.substring(1, 8) + (int)lastChar;
			long longKey = Long.parseLong(resultKey);
			return longKey;
		}
		

		public void printHashTable() {
			System.out.println("Hash Table List");
			for (int counter=0; counter < SIZE; counter ++) {
				System.out.println(counter + " " + hashTable[counter]);
			}
		}
		
		// To track key comparisons
		private static int numOfSearch = 0;
		private static int numOfKeyComparison = 0;

		public int getSearch() {
			return numOfSearch;
		}
		public int getKeyComparison() {
			return numOfKeyComparison;
		}
}
