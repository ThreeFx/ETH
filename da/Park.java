import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] a = new int[n];
		int c = 0;
		for (int i = 0; i < n; i++)
			a[i] = s.nextInt();

		for (int i = 0; i < n; i++) {
			if (a[i] < 0 || a[i] == i) continue;
			
//			int swaps = 0;
//			int b = a[a[i]];
//			System.out.println("Current spot: " + i + "; occupant: " + a[i]);
//
//			while (b != a[i]) {
//				System.out.println("  nextOccupant: " + b + " nextOfNextOccupant: " + a[b]);
//				int oldb = b;
//				b = a[b];
//				a[oldb] = -1;
//				swaps++;
//
//				System.out.println("After " + swaps + " swaps:");
//				for (int j = 0; j < n; j++) {
//					System.out.print(a[j] + " ");
//				}
//				System.out.println("_: " + oldb);
//			}

			int swaps = 1;
			int next = a[i];
			int old = i;

			while (next != -1) {
				swaps++;
				a[old] = -1;
				old = next;
				next = a[next];
			}

			c += swaps;
		}
		System.out.println(c);
			
	}
}
