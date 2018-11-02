package structs;

import java.util.LinkedList;

public class Node 
{
	public String name;
	public int nodeIndex;
	public LinkedList<Integer> edges = new LinkedList();
	public int nodeLength=0;
	Node(String name, int nodeIndex)
	{
		this.name = name;
		this.nodeIndex = nodeIndex;
	}
}