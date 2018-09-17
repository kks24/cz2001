package doubleHash;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

import app.DoubleHashThread;

public class DoubleHash 
{
	public static final int TABLE_SIZE =97;
	private static final int mod1 = TABLE_SIZE;   //hash1 
	private static final int mod2 = 5;	  //hash2 	
	public static long totalCpuTime = 0;
	public static int numberOfSearch =0;
	public static int totalKey=0;
	public static void DoubleHashApp()
	{
		String[] hashTable= new String[TABLE_SIZE];
		Scanner sc = new Scanner(System.in);
		int choice=0;
		int key1;
		int key2;
		String data = null;
		
		
		//hashTable[90]="LOL"; //SET A DEFAULT ENTRY INSIDE FIRST
		
		//System.out.println(hashTable[90]);
		
		//LIST OF THREADS
		List <DoubleHashThread> threadList = new ArrayList<DoubleHashThread>();
		int count = 0;
		
		try 
		{
			Thread.sleep(100);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		do 
		{
			System.out.println("==========================MENU==========================");
			System.out.println("Option 1: Search");
			System.out.println("Option 2: Add");
			System.out.println("Option 3: Delete");
			System.out.println("Option 4: Display Hashtable");
			System.out.println("Option 5: Display Average CPU time");
			System.out.println("Option 6: Display Average Key Comparsion");
			System.out.println("Option 7: Demo Load Factor");
			System.out.println("Option 8: Reset Average CPU time and Key Comparisons");
			System.out.println("Option 0: Back to main menu");
			System.out.print("Choice: ");
			choice=sc.nextInt();
			
			switch(choice) 
			{
				case 1: //SEARCH
					//CREATE A THREAD AND ADD TO LIST
					count++;
					threadList.add(0, new DoubleHashThread("Double Hash Thread"));
					threadList.get(0).setDaemon(true);
					
					//UPDATE THREAD HASH TABLE
					threadList.get(0).hashTable = hashTable;
					
					//GET USER INPUT
					sc.nextLine(); //clear buffer
					System.out.println("Please Enter the Student ID ");
					data=sc.nextLine();
					threadList.get(0).id = data; //UPDATE WANTED STUDENT ID
					
					long startTime = System.nanoTime();
					
					threadList.get(0).start(); //START THREAD EXECUTION
				
					//TERMINATE THREAD AND REMOVE FROM LIST OF ACTIVE THREADS
					try 
					{
						threadList.get(0).join();
						threadList.remove(0);

						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					long elapsedTime = System.nanoTime()-startTime;
					System.out.println("NANO TIME (microseconds): " + elapsedTime/100000);
					totalCpuTime += elapsedTime;
					break;
					
				case 2:
					if(checkTableFull(hashTable)==true)
					{
						break;
					}
					sc.nextLine();
					
					
					System.out.println("Please Enter the Student ID:");
					data=sc.nextLine();
					Long cData = StringtoInt(data); // new convert int to long 
					key1=hash1(cData);
					
					if(addIntoTable(hashTable,key1,data)==false) 
					{
						System.out.println(cData+" is unable to hash");
						System.out.println("Hash Collision occur, Second Hashing is needed");
						int countInsert=0;
						int a =0;
						while(true) 
						{
							if(countInsert==TABLE_SIZE)
							{
								System.out.println("Failed to insert Hashtable, all possible slots have been hit");
								break;
							}
							key2=hash2(cData,a);
							if(addIntoTable(hashTable,key2,data)==false) 
							{
								System.out.println(cData+" is unable to hash");
								System.out.println("Unable to Second Hash in + "+key2);
							}
							
							else
							{
								System.out.println("Successful Second Hash in "+key2);
								break;
							}
							a++;
							countInsert++;
						}
					}
					
					break;
					
				case 3:
					sc.nextLine(); 
					
					System.out.println("Please Enter the Student ID:");
					data=sc.nextLine();
					
					deleteTable(hashTable,data);
					
					break;
					
				case 4: 
					displayTable(hashTable);
					break;
					
				case 5:
					if(numberOfSearch==0)
					{
						System.out.println("Please Search for at least 1");
						break;
					}
					System.out.println("======Printing Average CPU time======");
					System.out.println("Total number of Search: "+numberOfSearch);
					System.out.println("Total CPU time: "+ totalCpuTime/100000+" ms");
					System.out.println("Average CPU time = "+(totalCpuTime/(numberOfSearch*100000))+" ms");
					break;
				case 6:
					if(numberOfSearch==0)
					{
						System.out.println("Please Search for at least 1");
						break;
					}
					System.out.println("======Printing Average Key Comparison======");
					System.out.println("Total number of Search: "+numberOfSearch);
					System.out.println("Total number of Key Comparison: "+totalKey);
					System.out.println("Average Key Comparison = "+(totalKey/numberOfSearch));
				case 7:
					System.out.println("Enter the Load Factor");
					double LoadFactor=sc.nextDouble();
					NumGenerator(hashTable,LoadFactor);
					break;
				case 8:
					totalCpuTime = 0;
					numberOfSearch = 0;
					System.out.println("Total CPU time and number of searches have been reset.");
					break;
				case 0:
					break;
					
				default:

					System.out.println("Input Error, Try Again");
			}
				
		} while(choice!=0);
		
	}
	public static void displayTable(String[] hashTable) 
	{
		for(int i=0;i<hashTable.length;i++) 
		{
			System.out.println("Index: "+ i +" = "+hashTable[i]);
		}
	}
	
	public static boolean addIntoTable(String[] hashTable,int key,String data) 
	{
		if(hashTable[key]!=null) 
		{
			return false;
		}
		
		else 
		{
			hashTable[key]=data;
			return true;
		}
	}
	
	public static boolean checkTableFull(String[] hashTable) 
	{
		boolean result=true;
		for(int i=0;i<hashTable.length;i++) {
			if(hashTable[i]==null) {
				result=false;
			}
		}
		System.out.println("Is table full? " +result);
		return result;
	}
	
	public static void searchTable(String[] hashTable,String data) 
	{
		int currentNoOfKeyComparsion=0;
		Long cData = StringtoInt(data);  // string to Long
		int key1=hash1(cData);
		System.out.println("key1= "+key1);
		int i=0;
		int key2=0;
		while(true) 
		{
			if(currentNoOfKeyComparsion==0)// 1st compare and is array[key1]==null
			{
				if(hashTable[key1]==null) 
				{
					System.out.println("The 1st key: "+key1+" in the array is null");
					numberOfSearch++; //1st search NULL
					break;
				}
				if(Objects.equals(hashTable[key1],data)==true)
				{
					System.out.println("The 1st key: "+key1+" in the array have the data "+data);
					numberOfSearch++; //1st search DATA
					totalKey++;
					break;
				}
			}
			else
			{
				key2=hash2(cData,i); // HASH 2
				if(hashTable[key2]==null)
				{
					System.out.println("The "+(i+1)+" key in the array is null"); // [key2]== null
					numberOfSearch=numberOfSearch++;
					totalKey=currentNoOfKeyComparsion+totalKey; // null = did not compare , hence currentKey will not +1
					break;
				}
				if(Objects.equals(hashTable[key1],data)==true) //[key1] == data
				{
					System.out.println("The "+data+" is searched and found in index: "+key1+" (key1)");
					currentNoOfKeyComparsion++;
					numberOfSearch++; //1st Search GOT DATA
					totalKey=currentNoOfKeyComparsion+totalKey; // total key +1
					break;
				}
				if(hashTable[key2]!=null) //[key2] != null
				{
					if(Objects.equals(hashTable[key2],data)==true)// [key2] == data
					{
						System.out.println("The "+data+" is searched and found in index: "+key2+" (key2)");
						currentNoOfKeyComparsion++; // current key +1
						numberOfSearch++; //add to the total key comparison
						totalKey=currentNoOfKeyComparsion+totalKey; // add to total key comparison
						break;
					}
				}
			}
			currentNoOfKeyComparsion++;
			//currentNoOfSearch++;
			i++;
			if(currentNoOfKeyComparsion==TABLE_SIZE) // TABLE_SIZE number of search and there is no data 
			{
				System.out.println("We have search the whole table with size of "+TABLE_SIZE);
				System.out.println("The data "+data+" do not exist in the table");
				numberOfSearch=numberOfSearch++;
				totalKey=currentNoOfKeyComparsion+totalKey;
				break;
			}
		}
		
		
	}
	
	public static void deleteTable(String[] hashTable,String data) 
	{
		for(int i=0;i<hashTable.length;i++) 
		{
			if(Objects.equals(data, hashTable[i])==true)
			{
				System.out.println("The data is located at Index :"+ i );
				hashTable[i]="-1";
				System.out.println("The "+data+" in index"+i+" is deleted");
				return;
			}
		}
		System.out.println("We are unable to find the data");
		return; 
	}
	
	public static long StringtoInt(String str1) { //  from here new to 
		StringBuffer Str1 = new StringBuffer(str1);
		char char1 = Str1.charAt(0);
		char char2 = Str1.charAt(8);
		Str1.deleteCharAt(0);
		Str1.deleteCharAt(7);
		Str1.insert(0,(int)char1);
		Str1.append((int)char2);
		return Long.parseLong(Str1.toString());
	}
	
	public static int hash1(long matri) {
		return (int)(matri%mod1);
	}
	
	public static int hash2(long matri,int i) {
		return (hash1(matri)+ i*(mod2 - (int)matri%mod2))%TABLE_SIZE;  // this one i think need to change , he told me to change this 
	}															//  here is all new function from DDD
	
	public static void NumGenerator(String[] HashTable, double LoadFactor) {
		long DigitPart = 0;
		for (int i = 0; i < TABLE_SIZE; i++)
			HashTable[i] = null;	
		for(int i = 0; i<LoadFactor*TABLE_SIZE;i++) {
			do
			{																// ensure is 7 digits
				DigitPart = (long)(Math.random()*10000000);
			}while(DigitPart < 1000000);									// Num part of string
			
			char CharPart = (char)(Math.random()*26 + 65);					// char part of string
			String Final = 'U' + Long.toString(DigitPart) + CharPart;
	
			Long cData = StringtoInt(Final); // new convert int to long 
			int key1=hash1(cData);
			
			if(addIntoTable(HashTable,key1,Final)==false) 
			{
				System.out.println(Final+" is unable to hash");
				System.out.println("Hash Collision occur, Second Hashing is needed");
				int count=0;
				int a =0;
				int key2=0;
				while(true) 
				{
					if(count==TABLE_SIZE)
					{
						System.out.println("Failed to insert Hashtable, all possible slots have been hit");
						break;
					}
					key2=hash2(cData,a);
					if(addIntoTable(HashTable,key2,Final)==false) 
					{
						System.out.println(Final+" is unable to hash");
						System.out.println("Unable to Second Hash in + "+key2);
					}
					
					else
					{
						System.out.println("Successful Second Hash in "+key2);
						break;
					}
					a++;
					count++;
				}
			}
		}
	}
	
	//================================= CPU TIME ==========================
	public static long getCpuTime(DoubleHashThread dt)
	{
		ThreadMXBean bean = ManagementFactory.getThreadMXBean(); //GET THREAD BEAN
		long cpuTime = -1000;
				
		if (bean.isThreadCpuTimeSupported())
		{
			cpuTime = bean.getThreadCpuTime(dt.getId());
			
//			System.out.println("in get cpu method, thread state: " + dt.getState());
//			System.out.println("CPU time for thread number " + dt.getId() + 
//					". " + dt.getName() + " is " + bean.getThreadCpuTime(dt.getId())/100000 + " microseconds");
		}
		
		return cpuTime;
		
	}
}
