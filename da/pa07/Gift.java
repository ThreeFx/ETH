import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int nl = s.nextInt();
		int[] l = new int[nl];
		for (int i = 0; i < nl; i++)
			l[i] = s.nextInt();

		int na = s.nextInt();
		int[] a = new int[na];
		for (int i = 0; i < na; i++)
			a[i] = s.nextInt();

		int c = 0, i = 0, j = 0;

		while (i < na && j < nl) {
			int b = a[i];
			int d = l[j] / 4 * l[j] / 4;

//			System.out.println("i = " + i + " a[i] = " + a[i] + " b = " + b +
//					"\nj = " + j + " l[j] = " + l[j] + " d = " + d + "\n------");

			if (b == d) {
				if (l[j] % 4 == 0) {
					c++;
					i++;
					j++;
				} else {
					j++;
				}
			} else if (b < d) {
				i++;
			} else {
				j++;
			}
		}

		System.out.println(c);
	}
}
