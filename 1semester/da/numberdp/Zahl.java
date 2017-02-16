import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int g = s.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = s.nextInt();

        int[][] dp = new int[n+1][g+1];

        solve(g, a, dp, n);
        System.out.println("Sigma: " + g + "\n" + (dp[n][g] == 1));;

        System.out.print("   ");
        for (int i = 0; i <= g; i++)
            System.out.print(i + (i < 10 ? "  " : " "));
        System.out.println();

        for (int i = 0; i < n+1; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print("NTF".toCharArray()[dp[i][j]] + "  ");
            }
            System.out.println();
        }
    }

    static void solve(int goal, int[] nums, int[][] dp, int n) {
        if (n < nums.length && dp[n][goal] > 0)
            return;
        if (n == 0) {
            dp[0][goal] = goal == 0 ? 1 : 2;
            //System.out.println("goal: " + goal + " " + (dp[0][goal] == 1));
        } else {
            //System.out.println("n: " + n + "  " + "goal: " + goal + " to check:");
            int canSolve = 13;
            for (int i = 1; i <= n; i++) {


                int tosub = 0;
                for (int j = i - 1; j < n; j++) {
                    tosub *= 10;
                    tosub += nums[j];
                }


                int newg = goal - tosub;
                //System.out.println("  goal - tosub: " + goal + " - " + tosub + " = " + newg);

                if (newg < 0) {
                    dp[n][goal] = 2;
                }
                else {
                    solve(newg, nums, dp, i - 1);
                }
                if (newg >= 0 && dp[i-1][newg] > 0)
                    canSolve = Math.min(canSolve, dp[i-1][newg]);
            }
            System.out.println("n: " + n + " goal: " + goal + ": " + (canSolve == 1));
            dp[n][goal] = canSolve == 1 ? 1 : 2;
        }

    }
}
