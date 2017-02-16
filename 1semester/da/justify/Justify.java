import java.util.*;
import java.io.*;

class Main {
    //Scanner s = new Scanner(new File("test1.input"));
    static Scanner s;
    //static int i = 0;

    public static void main(String[] args) {
        //s = new Scanner(new File(args[0] + ".input"));
        s = new Scanner(System.in);
        int c = s.nextInt();
        for (int l = 0; l < c; l++)
            solveCase();
    }

    static void solveCase() {
        int n = s.nextInt();
        int w = s.nextInt();
        s.nextLine();
        int[] ww = readMany(s, n);
        int[][] dp = new int[n][w+1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < w+1; j++)
                dp[i][j] = -1;

        // i j = abstand rechts wenn du 

        for (int i = 0; i < n; i++) {
            int cursum = 0;
            int j = 0;
            while (i+j < n && j < w+1 && (j == 0 ? 0 : 1) + cursum + ww[i+j] <= w) {
                if (j != 0) cursum++;
                cursum += ww[i+j];
                dp[i][j] = (w - cursum) * (w - cursum);
                j++;
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j <= w; j++) {
//                System.out.print(dp[i][j] + "   ");
//            }
//            System.out.println();
//        }

        int[] r = new int[n];
        for (int i = 0; i < n; i++) r[i] = Integer.MAX_VALUE;

        int k = n-1;
        for (; k >= n - 1 - w && k >= 0; k--) {
            for (int j = (n - 1 - k); j <= w; j++)
            if (dp[k][j] >= 0) {
                r[k] = 0;
                break;
            }
            if (r[k] != 0) break;
        }

//        for (int i = 0; i < n; i++) System.out.println(r[i]);

        for (int i = k; i >= 0; i--) {
            int m = Integer.MAX_VALUE;
            // start at i
            for (int j = 0; i + j < n && j <= w && dp[i][j] >= 0; j++) {
                // go to j
//                System.out.println("i: " + i + "  j: " + j + "  dp: " + dp[i][j]);
                int diff = j + 1;

                m = Math.min(m, dp[i][j] + r[i+diff]);
            }
            r[i] = m;
        }

        System.out.println(/*"--- result: " +*/ r[0]);
    }

    public static int[] readMany(Scanner s, int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = s.nextLine().length();
        return a;
    }
}
