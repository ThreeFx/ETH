import java.util.*;
import java.io.*;

class Main {
    //Scanner s = new Scanner(new File("test1.input"));
    static Scanner s;
    static int i = 0;

    public static void main(String[] args) throws FileNotFoundException {
        s = new Scanner(new File(args[0] + ".input"));
        int c = s.nextInt();
        for (int l = 0; l < c; l++)
            solveCase();
    }

    static void solveCase() {
        int n = s.nextInt();
        int w = s.nextInt();
        s.nextLine();
        int[] ww = readMany(s, n);
        int[][] dp = new int[n+1][w+1];
        for (int i = 0; i < n+1; i++)
            for (int j = 0; j < w; j++)
                dp[i][j] = -1;

        for (int j = 0; j < w + 1; j++) {
            dp[n][j] = 0;
        }

//        for (int i = n; i >= 0; i++) {
//            for (int j = 0; j < n; j++) {
                makeCosts(ww, dp, 0, 0, 0, n, w);
//            }
//        }

//        for (int i = 0; i < n+1; i++) {
//            for (int j = 0; j < w+1; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println(dp[0][0]);
//        System.out.println("res: " + res);

        System.out.println(dp[0][0]);

    }

    //static int solve(int[][] dp, ) {
    //}

    static int makeCosts(int[] words, int[][] dp, int word, int row, int used, int rows, int maxWidth) {
        if (row > rows || used > maxWidth) return Integer.MAX_VALUE;
        if (dp[row][used] > -1 && word >= rows - 1) return dp[row][used];
        if (word == rows - 1) System.out.println(++i);

        if (canPlace(words[word], used, maxWidth)) {
            if (word == rows - 1) {
                dp[row][used] = 0;
                dp[row][place(words[word], used)] = 0;
            } else {
                int total = Math.min(makeCosts(words, dp, word + 1, row, place(words[word], used), rows, maxWidth)
                                    ,cost(used, maxWidth) + makeCosts(words, dp, word + 1, row + 1, place(words[word], 0), rows, maxWidth)
                                    );
                dp[row][used] = total;
            }
        } else {
            if (word == rows - 1) {
                dp[row+1][words[word]] = 0;
            }
            dp[row][used] = cost(used, maxWidth) + makeCosts(words, dp, word + 1, row + 1, place(words[word], 0), rows, maxWidth);
        }
        return dp[row][used];
    }

    static int cost(int j, int width) {
        return (width - j) * (width - j);
    }

    static int place(int wordlen, int used) {
        if (used == 0) return wordlen;
        return used + wordlen + 1;
    }

    static boolean canPlace(int len, int used, int maxWidth) {
        return place(len, used) <= maxWidth;
    }

    public static int[] readMany(Scanner s, int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = s.nextLine().length();
        return a;
    }
}
