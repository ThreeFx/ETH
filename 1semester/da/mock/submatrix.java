import java.util.Scanner;

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

			// Read the matrix size
			int n = scanner.nextInt();

			// Create a new 2D array
			int[][] A = new int[n][n];

			// Read the matrix
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					A[i][j] = scanner.nextInt();
				}
			}

			int[][] B = new int[n+1][n+1];

			// TODO: Implement your solution
			B[0][0] = 0;

			for (int i = 1; i <= n; i++) {
				B[i][0] = 0;
				B[0][i] = 0;
			}

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					B[i][j] = B[i][j-1] + B[i-1][j] - B[i-1][j-1] + A[i-1][j-1];
				}
			}

			//for (int i = 0; i < n; i++) {
			//	for (int j = 0; j < n; j++) {
			//		System.out.print(B[i][j] + "  ");
			//	}
			//	System.out.println();
			//}

			int max = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = i+1; k <= n; k++) {
						for (int l = j+1; l <= n; l++) {
							//System.out.println("cand: " + (B[k][l] - B[k][j] - B[i][l] + B[i][j]));
							max = Math.max(max, B[k][l] - B[k][j] - B[i][l] + B[i][j]);
						}
					}
				}
			}

			// TODO: Replace with the right answer - the maximum obtainable sum
			System.out.println(max);
		}
		scanner.close();
	}
}
