import java.util.Scanner;

class MinHeap {
	
	private int size;
	// Elements are stored from index 1 to size
	private int[] elements; 
	
	// Simple constructor with a fixed capacity
	public MinHeap(int max_capacity) {
		size = 0;
		elements = new int[max_capacity + 1];
	}
	
	// Return the last element in the heap array
	// We may assume that size >= 1
	public int queryLast() {
		return elements[size];
	}

	/*** Methods to implement */

	// Insert element "key" into the heap, restoring the heap property
	// You may assume that the heap array does not overflow
	public void insert(int val) {
		elements[size + 1] = val;
		size += 1;
		
		//System.out.println("insert");
		
		int j = size;
		int toswap = j / 2;
		while (toswap >= 1 && elements[j] < elements[toswap]) {
			//System.out.println("j: " + j + " " + elements[j] + "  toswap: " + toswap + " " + elements[toswap]);
			int t = elements[j];
			elements[j] = elements[toswap];
			elements[toswap] = t;
			j = toswap;
			toswap /= 2;
		}
		
		// TODO: Implement restoring the heap order around the newly-inserted element
	}

	// Delete and return the smallest element in the heap
	// You may assume that the heap is nonempty, so size >= 1
	public int extractMin() {
		// Remember minimum value and remove it from the heap
		int minval = elements[1];
		elements[1] = elements[size];
		size -= 1;
		
		int j = 1;
		while (2*j <= size) {
			int cur = elements[j];
			int candidate = elements[2*j];
			int toswap = 2*j;
			if (2*j + 1 <= size && elements[2*j + 1] < candidate) {
				candidate = elements[2*j+1];
				toswap = 2*j + 1;
			}
			if (cur > candidate) {
				int t = elements[j];
				elements[j] = elements[toswap];
				elements[toswap] = t;
				j = toswap;
			} else {
				break;
			}
		}
				
		// TODO: Implement restoring the heap order around the changed
		// root of the heap (elements[1])
		
		return minval;
	}
}


/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main {

	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);
		
		// Read the number of cases and loop over the cases
		int case_no, cases = scanner.nextInt();
		for (case_no = 0; case_no < cases; case_no++) {
			// Read the input size
			int n = scanner.nextInt();
			// Create a new heap with sufficient capacity
			MinHeap heap = new MinHeap(n);
			
			// Insert n elements into the heap and output the result of every queryLast
			int i;
			for (i = 0; i < n; i++) {
				int val = scanner.nextInt();
				heap.insert(val);
				System.out.print(heap.queryLast());
				if (i < n - 1)
					System.out.print(" ");
			}
			System.out.println();
			
			// Extract the smallest element from the heap n times 
			for (i = 0; i < n; i++) {
				System.out.print(heap.extractMin());
				if (i < n - 1)
					System.out.print(" ");
			}
			System.out.println();
		}
		scanner.close();
	}
}
