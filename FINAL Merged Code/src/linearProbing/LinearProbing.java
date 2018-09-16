package linearProbing;
import java.lang.management.*;
import java.util.Objects;

import app.LinearProbingThread;

public class LinearProbing {
	// Variables & Constants
		private static final int SIZE = 97;
		private static final int mod = 97;
		private static String[] hashTable;
		
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
		public static int hashing(String MatricID) {
			long longKey = convertKey(MatricID);
			int result = (int)(longKey % mod);
			return result;
		}
		
		public static int rehashing(int j) {
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
		public static int searchKey(String MatricID) {
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
		
		public void deleteKey(String MatricID) {
			int index = hashing(MatricID);
			for (int numOfSearch = 0; numOfSearch < SIZE; numOfSearch ++) {
				if (Objects.equals(hashTable[index],MatricID) == true) {
					hashTable[index] = "Deleted";
					System.out.println("Item removed");
					return;
				}
				else {
					index = rehashing(index);
				}
			}
				System.out.println("Item not found.");
		}
		
		// Other functions
		public static long convertKey(String MatricID) {
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
		
		// Get Num generator
		public void NumGenerator(double LoadFactor) {
			long DigitPart = 0;
			for (int counter = 0; counter < SIZE; counter ++)
				hashTable[counter] = null;
			for(int i = 0; i<LoadFactor*SIZE;i++) {
				do
				{																// ensure is 7 digits
					DigitPart = (long)(Math.random()*10000000);
				}while(DigitPart < 1000000);									// Num part of string
				
				char CharPart = (char)(Math.random()*26 + 65);					// char part of string
				String Final = 'U' + Long.toString(DigitPart) + CharPart;
		
				// Adding element
				int index = hashing(Final);
				System.out.println("Storing at index: " + index);
				for (int numOfSearch = 0; numOfSearch < SIZE; numOfSearch ++) {
					if (Objects.equals(hashTable[index], null) == true || Objects.equals(hashTable[index],"Deleted") == true) {
						hashTable[index] = Final;
						System.out.println("Key added.");
						break;
					}
					else if (Objects.equals(hashTable[index],Final) == true) {
						System.out.println("Key already added.");
						break;
					}
					else {  //Sample data to use this 1)U8083214O and 2)U8235905D
						index = rehashing(index);
						System.out.println("Index Occupied, storing at next index: " + index);
					}
				}
				if (numOfSearch == SIZE)
					System.out.println("Hash table is full.");
			}
		}
		//================================= CPU TIME ==========================
		public static long getCpuTime(LinearProbingThread lt)
		{
			ThreadMXBean bean = ManagementFactory.getThreadMXBean(); //GET THREAD BEAN
			long cpuTime = -1000;
							
			if (bean.isThreadCpuTimeSupported())
			{
				cpuTime = bean.getThreadCpuTime(lt.getId());
			}
					
			return cpuTime;
					
		}
		
}
