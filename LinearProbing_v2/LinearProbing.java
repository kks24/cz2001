import java.util.Objects;

public class LinearProbing {
	// Variables & Constants
		private static final int SIZE = 97;
		private static final int mod = 11;
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
		public int hashing(String MatricID) {
			long longKey = convertKey(MatricID);
			int result = (int)(longKey % mod);
			return result;
		}
		
		public int rehashing(int j) {
			return ((j + 1) % mod);
		}
		
		// Add key
		public void addKey(String MatricID) {
			int index = hashing(MatricID);
			System.out.println("Storing at index: " + index);
			for (int numOfSearch = 0; numOfSearch < SIZE; numOfSearch ++) {
				if (Objects.equals(hashTable[index], null) == true || Objects.equals(hashTable[index],"Deleted") == true) {
					hashTable[index] = MatricID;
					System.out.println("Key added.");
					return;
				}
				else if (Objects.equals(hashTable[index],MatricID) == true) {
					System.out.println("Key already added.");
					return;
				}
				else {  //Sample data to use this 1)U8083214O and 2)U8235905D
					index = rehashing(index);
					System.out.println("Index Occupied, storing at next index: " + index);
				}
			}
			System.out.println("Hash table is full.");
		}
		
		// Search key
		public int searchKey(String MatricID) {
			numOfSearch ++;
			int index = hashing(MatricID);
			// check until all positions are checked
			for (int numOfSearch = 0; numOfSearch < SIZE; numOfSearch ++) {
				System.out.println("Checking pos " + index);
				// if match is found
				if (Objects.equals(hashTable[index],MatricID) == true) {
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
		public void deleteKey(String MatricID) {
			int index = hashing(MatricID);
			for (int numOfSearch = 0; numOfSearch < SIZE; numOfSearch ++) {
				if (Objects.equals(hashTable[index],MatricID) == true) {
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
		public long convertKey(String MatricID) {
			char firstChar = MatricID.charAt(0);
			char lastChar = MatricID.charAt(MatricID.length()-1);
			String resultKey = (int)firstChar + MatricID.substring(1, 8) + (int)lastChar;
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