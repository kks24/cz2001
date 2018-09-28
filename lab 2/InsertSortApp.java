
public class InsertSortApp {
		public static void main(String[] args) {
			InsertionSort array = new InsertionSort();
			array.generateData(1);
			array.printArray();
			System.out.println();
			System.out.println("Number of comparisions: " + array.getNumberOfComparison());
			array.resetNumberOfComparison();
		}
}
