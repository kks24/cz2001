public class LinearProbing {
	// Variables & Constants
	private static final int SIZE = 7; 		// Size should be a prime number
	private long[] hashTable;
	
	// Constructor 
	public LinearProbing() {
		this(SIZE);
	}
	
	public LinearProbing(int H) {
		hashTable = new long[H];
		for (int counter = 0; counter < H; counter ++) {
			hashTable[counter] = (long)-1;
			// Let -1 be empty and 0 be tombstone
		}
	}
	
	// Hashing functions
	public int hashing(long key) {
		int result = (int)(key % SIZE);
		return result;
	}
	
	public int rehashing(int j) {
		return ((j + 1) % SIZE);
	}
	
	// Add key
	public void addKey(String key) {
		long longKey = convertKey(key);
		int index = hashing(longKey);
		for (int numOfSearch = 0; numOfSearch < SIZE; numOfSearch ++) {
			if (hashTable[index] == 0 || hashTable[index] == -1) {
				hashTable[index] = longKey;
				System.out.println("Key added.");
				return;
			}
			else if (hashTable[index] == longKey) {
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
		int numOfSearch = 0;
		long longKey = convertKey(key);
		// computer the hash code
		int index = hashing(longKey);
		while (!isEmpty(index)) {
			if (hashTable[index] == longKey)
				return (index);
			else {
				numOfSearch++;
				if (numOfSearch == SIZE)
					return (-1);
				index = rehashing(index);
			}
		}
		return (-1);
	}
	
	// Delete key
	public void deleteKey(String key) {
		long longKey = convertKey(key);
		int index = hashing(longKey);
		int numOfSearch = 0;
		while (!isEmpty(index) && numOfSearch < SIZE) {
			if (longKey == hashTable[index]) {
				hashTable[index] = 0;
				System.out.println("Item removed.");
				return;
			}
			else {
				numOfSearch ++;
				index = rehashing(index);
			}
		}
		System.out.println("Nothing to delete.");
	}
	
	// Other functions
	public long convertKey(String key) {
		// accept error for invalid key?
		char firstChar = key.charAt(0);
		char lastChar = key.charAt(key.length()-1);
		String resultKey = (int)firstChar + key.substring(1, 8) + (int)lastChar;
		long longKey = Long.parseLong(resultKey);
		return longKey;
	}

	public boolean isEmpty(int index) {
		if (hashTable[index] == -1)
			return true;
		else
			return false;
	}

	public void printHashTable() {
		for (int counter=0; counter < SIZE; counter ++) {
			System.out.println(counter + " " + hashTable[counter]);
		}
	}
}
