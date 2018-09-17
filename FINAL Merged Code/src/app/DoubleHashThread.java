package app;

import doubleHash.DoubleHash;
public class DoubleHashThread extends Thread
{
	
	public String[] hashTable= new String[DoubleHash.TABLE_SIZE];
	public String id;
	
	
	public DoubleHashThread(String s) 
	{
		this.setName(s);
	}
	

	
	//OVERRIDE RUN TO RUN SEARCH FUNCTION
	@Override
	public void run()
	{
		long cpuTime = 0;
		
		DoubleHash.searchTable(hashTable, id);
			
		//WHILE THE CPU TIME IS NOT YET UPDATED (CUS THE ALGORITHM FINISHES TOO FAST)
		while (cpuTime == 0)
			cpuTime = DoubleHash.getCpuTime(this);
		
		System.out.println("CPU time taken " + cpuTime/100000+" ms");
		//DoubleHash.totalCpuTime += cpuTime;
		//DoubleHash.numberOfSearch=DoubleHash.numberOfSearch+1;
		return;
	}
}



