import java.util.Arrays;

class Coins {
    public static void main(String[] args) {
        int goal = Integer.parseInt(args[0]);
        int[] coins = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            coins[i-1] = Integer.parseInt(args[i]);
        }
//        Arrays.sort(coins);

        // left: amount, right: first k coins
        int[][] dp = new int[goal + 1][coins.length + 1];
        for (int i = 1; i < coins.length + 1; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= goal; i++) {
            for (int j = 1; j <= coins.length; j++) {
                dp[i][j] = dp[i][j-1];
                if (i - coins[j-1] < 0) continue;
                dp[i][j] += dp[i - coins[j-1]][j];
            }
        }
        System.out.println("Coins: " + Arrays.toString(coins));
        System.out.println("Number of possiblities for " + goal
                + ": " + dp[goal][coins.length]);
    }
}
