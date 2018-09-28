import java.util.*;
public class ddd {
	private static final int mod1 = 11;   					//hash1 mod
	private static final int mod2 = 5;	  				//hash2 mod
	private static final int Array_size = 1000;				//Array size
	private static final String empty = "TombStone";			//Value when slot is empty
	static Scanner sc = new Scanner(System.in);
	public static void main(String[]args) {
		String HashTable[] = new String[Array_size];
		for(int j=0;j<Array_size;j++) {					//ensure all slot is not null
			HashTable[j] = empty;
		}
		NumGenerator(HashTable,0.5);					// if load factor is 0.5
		
		System.out.println("Matriculation Number: ");
		String matri = sc.nextLine();
		int success = insert(HashTable,matri);
		if(success == 1)
			System.out.println("Added Successfully");
		else
			System.out.println("Please try again");
		
	}
	
	public static void NumGenerator(String[] HashTable, double LoadFactor) {
		long DigitPart = 0;
		for(int i = 0; i<LoadFactor*Array_size;i++) {
			do{																// ensure is 7 digits
				DigitPart = (long)(Math.random()*10000000);
			}while(DigitPart < 1000000);					// Num part of string
			char CharPart = (char)(Math.random()*26 + 65);			// char part of string
			String Final = 'U' + Long.toString(DigitPart) + CharPart;
			while (true){							// Ensure that String generated are not placed in the same slot more than once
				int index = (int)(Math.random()*Array_size);
				if(HashTable[index] == empty) {
					HashTable[index] = Final;			// if slot is empty then u insert in
					break;
				}
			}
		}
	}
	public static int hash1(long matri) {
		return (int)(matri%mod1);
	}
	
	public static int hash2(long matri,int i) {
		return (hash1(matri)+ i*(mod2 - (int)matri%mod2))%Array_size;  // hash2 algo is mod2 - key%mod2
	}
	public static long StringtoInt(String str1) {
		StringBuffer Str1 = new StringBuffer(str1);
		char char1 = Str1.charAt(0);					// Assign first letter to a variable
		char char2 = Str1.charAt(8);					// Assign last letter to a variable
		Str1.deleteCharAt(0);						// remove first letter
		Str1.deleteCharAt(7);						// remove last letter
		Str1.insert(0,(int)char1);					//change first letter to int and add to the string
		Str1.append((int)char2);					// change last letter to int and add to the string
		return Long.parseLong(Str1.toString());
	}
	public static int insert(String[] HashTable, String matri) {
		long toLong = StringtoInt(matri);
		int index1 = hash1(toLong);					//result of first hash
		if(HashTable[index1] == empty) {				// if success at first try
			HashTable[index1] = matri;
			return 1;
		}
		else {								 
			int i = 1;						// record number of time of hash2
			while(i< Array_size) {
				int index2 = hash2(toLong,i);			// hash2
				if(HashTable[index2] == empty) {		// if hits
					HashTable[index2] = matri;
					return 1;
				}						//else i increase
				else {
					i++;
				}	
			}
			return 0;
		}
	}
}
