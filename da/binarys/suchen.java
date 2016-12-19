import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		for (int i = 0; i < num; i++) {
			int res = BinarySearch(s.nextInt());
			System.out.println(res < 0 ? "NO" : res);
		}
	}

	public static int f(int x) {
			int r = 0, z = x;
			while (z > 0) {
					r = r + x;
					z = z / 2;
			}
			return r;
	}

	public static int BinarySearch(int val) {
		int l = 0;
		int r = 20000000;

		while (l <= r) {
			int m = (l + r) / 2;
			int u = f(m);
			if (val == u) return m;
			if (val < u) r = m - 1;
			else l = m + 1;
		}
		return -1;
	}
}
