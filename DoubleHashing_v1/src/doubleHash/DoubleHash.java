package doubleHash;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

import app.DoubleHashThread;

public class DoubleHash 
{
	public static final int TABLE_SIZE =97;
	
	public static void DoubleHashApp()
	{
		String[] hashTable= new String[TABLE_SIZE];
		Scanner sc = new Scanner(System.in);
		int choice=0;
		int key1;
		int key2;
		String data = null;
		
		hashTable[90]="LOL"; //SET A DEFAULT ENTRY INSIDE FIRST
		
		System.out.println(hashTable[90]);
		
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
					
					threadList.get(0).start(); //START THREAD EXECUTION
				
					//TERMINATE THREAD AND REMOVE FROM LIST OF ACTIVE THREADS
					try 
					{
						threadList.get(0).join();
						threadList.remove(0);

						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					break;
					
				case 2:
					if(checkTableFull(hashTable)==true)
					{
						break;
					}
					sc.nextLine();
					
					
					System.out.println("Please Enter the Student ID:");
					data=sc.nextLine();
					
					key1=11;
					
					if(addIntoTable(hashTable,key1,data)==false) 
					{
						System.out.println("Hash Collision occur, Second Hashing is needed");
						
						key2=7;
						
						if(addIntoTable(hashTable,key2,data)==false) 
						{
							System.out.println("Unable to Second Hash");
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
					
					
				case 0:
					break;
					
				default:

					System.out.println("Input Error, Try Again");
			}
				
		} while(choice!=0);
		
		sc.close();
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
		boolean check=false;
		
		for(int i=0;i<hashTable.length;i++) 
		{
			if(Objects.equals(data, hashTable[i])==true&&check==false)
			{
				System.out.println("The data is located at Index :"+ i );
				check=true;
			}
			else if(Objects.equals(data, hashTable[i])==true&&check==true) {
				System.out.println("The data is also located at Index :"+i);
			}
		}
		
		if(check==false) 
		{
			System.out.println("We are unable to find "+data+" in the hashTable");
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