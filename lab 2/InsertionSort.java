//InsertionSort Function

public class InsertionSort {
	private final static int SIZE = 1000;
	private long numberOfComparison = 0;
	private int[] array;
	
	public InsertionSort() {
		array = new int[SIZE];
	}
    
 // Data generator
 	public void generateData(int generateInput) {
 		switch (generateInput) {
 			case 1:
 				for (int i = 0; i < SIZE; i ++) {
 					int value = (int)(Math.random()*((SIZE-1)+1))+1;
 					array[i] = value;
 				}
 				break;
 			case 2:
 				for (int i =0; i < SIZE; i ++) {
 					array[i] = i + 1;
 				}
 				break;
 			case 3: 
 				for (int i=0; i < SIZE; i ++) {
 					array[i] = SIZE-i; 
 				}
 				break;
 		}
 	}
	
	
	/*Sorting Array with insertion sort*/
    public void sort(int slot[], int n) 
    { 
        for(int i = 1; i<n; i++) {
        	for(int j=i; j>0; j--) {
        		numberOfComparison++;
        		if(slot[j] < slot[j-1]) {
        			//Swap function
        			int temp = slot[j];
        			slot[j] = slot[j-1];
        			slot[j-1] = temp;
        		}
        		else break;
        			
        	}
        }
    } 
    
        
    /* Print array of size n*/
    public static void printArray(int arr[]) 
    { 
    	int n = arr.length; 
    	for (int i=0; i<n; ++i) 
    		System.out.print(arr[i] + " "); 
    	System.out.println(); 
    } 
    
    public void printArray() {
    	System.out.println("Unsorted List");
    	int count = 0;
    	for (int i = 0; i < SIZE; i ++) {
    		count++;
    		if(count==30) {
    			System.out.println(array[i] + " ");
    			count = 0;
    		}
    		else {
    			System.out.print(array[i] + " ");
    		}
		}
    	System.out.println("");
		System.out.println("Sorted List");
		sort(array, array.length);
		count = 0;
		for (int i = 0; i < SIZE; i ++) {
			count++;
    		if(count==30) {
    			System.out.println(array[i] + " ");
    			count = 0;
    		}
    		else {
    			System.out.print(array[i] + " ");
    		}
		}
		
	}
    
    public long getNumberOfComparison() {
		long count;
		count = numberOfComparison;
		numberOfComparison = 0;
		return count;
	}
	
	public void resetNumberOfComparison() {
		numberOfComparison = 0;
	}
}
