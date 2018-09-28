package app;

import linearProbing.LinearProbing;
public class LinearProbingThread extends Thread
{
	
	public String id;
	
	public LinearProbingThread(String s) 
	{
		this.setName(s);
	}
	
	@Override
	public void run()
	{
		long cpuTime = 0;
		
		int index = LinearProbing.searchKey(id); //change searchkey (and several other methods) to static for access
		if (index == -1)
			System.out.println("No Key Found");
		else
			System.out.println("Key found at index: " + index);
		
		
//		while (cpuTime == 0)
//			cpuTime = LinearProbing.getCpuTime(this);
//		
//		System.out.println("CPU time taken (microseconds): " + cpuTime/100000);
		
		return;
	}
}



