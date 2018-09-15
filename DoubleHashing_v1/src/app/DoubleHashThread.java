package app;

import doubleHash.DoubleHash;
public class DoubleHashThread extends Thread
{
	
	public String[] hashTable= new String[DoubleHash.TABLE_SIZE];
	public String id;
	
	public Object lock = this;
	public boolean pause = false;
	
	public DoubleHashThread(String s) 
	{
		this.setName(s);
	}
	

	
	//OVERRIDE RUN TO RUN SEARCH FUNCTION
	@Override
	public void run()
	{
		int a = 0;
		long cpuTime = 0;
		
		DoubleHash.searchTable(hashTable, id);
			
		//WHILE THE CPU TIME IS NOT YET UPDATED (CUS THE ALGORITHM FINISHES TOO FAST)
		while (cpuTime == 0)
			cpuTime = DoubleHash.getCpuTime(this);
		
		System.out.println("CPU time taken (microseconds): " + cpuTime/100000);
		
		return;
	}
}



