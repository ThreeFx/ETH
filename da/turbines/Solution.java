import java.util.*;

class Main {
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int c = s.nextInt();
        for (int l = 0; l < c; l++)
            solveCase();
    }

    static void solveCase() {
        int n = s.nextInt();
        int d = s.nextInt();
        int[] p = readMany(n);
        int[] t = readMany(n);

        int[] nearestBefore = new int[n];
        int cur = n - 1;
        // Check which position is the nearest valid one before cur
        for (int i = n - 1; i >= 0; i--)
        {
            while (p[cur] - p[i] >= d) {
                nearestBefore[cur] = i;
                cur--;
            }
        }

        while (cur >= 0) {
            nearestBefore[cur] = -1;
            cur--;
        }

        int[] dp = new int[n];
        dp[0] = t[0];

        for (int i = 1; i < n; i++) {
            int alt = nearestBefore[i];
            if (nearestBefore[i] == -1) {
                dp[i] = Math.max(dp[i-1], t[i]);
            } else {
                dp[i] = Math.max(dp[i-1], t[i] + dp[nearestBefore[i]]);
            }
        }

        System.out.println(dp[n-1]);
    }

    public static int[] readMany(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = s.nextInt();
        return a;
    }
}
