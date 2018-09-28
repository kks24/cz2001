package linearProbing;

public class LinearProbing {
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
		for (int numOfSearch = 0; numOfSearch < SIZE; numOfSearch ++) {
			if (hashTable[index] == null || hashTable[index] == "Deleted") {
				hashTable[index] = key;
				System.out.println("Key added.");
				return;
			}
			else if (hashTable[index].equals(key)) {
				System.out.println("Key already exists.");
				return;
			}
			else {
				index = rehashing(index);
			}
		}
		System.out.println("Hash table is full.");
	}
	
	// Search key
	public int searchKey(String key) {
		// to account for searching the whole table
		int index = hashing(key);
		while(hashTable[index] != null) {
			if(hashTable[index].equals(key))
				return index;
			index = (index + 1) % SIZE;
		}
		return -1;
	}
	
	// Delete key
	public void deleteKey(String key) {
		int index = hashing(key);
		int numOfSearch = 0;
		while (!key.equals(hashTable[index])) {
			index = rehashing(index);
			numOfSearch ++;
			if (numOfSearch < SIZE)
				break;
		}
		
		if (key.equals(hashTable[index])) {
			hashTable[index] = "Deleted";
			System.out.println("Item removed.");
			return;
		}
		else {
			System.out.println("Nothing to delete.");
		}

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
}
