//InsertionSort Function

public class InsertionSort {
	// Main method 
    public static void main(String args[]) 
    {         
        int arr[] = {12, 15, 13, 5, 6}; //numGenerator
        
        InsertionSort ob = new InsertionSort();         
        ob.sort(arr, arr.length); 
          
        printArray(arr); 
    } 
	
	
	/*Sorting Array with insertion sort*/
    public void sort(int slot[], int n) 
    { 
        for(int i = 1; i<n; i++) {
        	for(int j=i; j>0; j--) {
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
}
