public class MergeSortApp {
	public static void main(String[] args) {
		MergeSort array = new MergeSort();
		array.generateData(1);
		array.printArray();
		System.out.println();
		array.mergeMethod(0, array.SIZE-1);
		array.printArray();
	}
}
