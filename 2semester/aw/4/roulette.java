import java.util.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.io.*;

class Main {
    static Scanner s;
    static DecimalFormat df;

    public static void main(String[] args) {
        s = new Scanner(System.in);
        int c = s.nextInt();
        df = new DecimalFormat("0.0######");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        for (int l = 0; l < c; l++)
            solveCase();
    }

    static void solveCase() {
        int n = s.nextInt();
        int m = s.nextInt();
        int k = s.nextInt();
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (s.nextInt() == 0) c++;
        }
        double p = (double)c / n;

        double[][] dp = new double[m+1][m+1];

        //double prev = 0;

        //for (int i = 0; i <= m; i++) {V
        //    for (int len = 0; i+len < m; len++) {
        //        dp[i][len] = Math.pow(p, len) * (1 - p);`
        //    }
        //}


        // Magic ;)
        for (int len = k; len < m; len++) {
            dp[0][len] = Math.pow(p, len) * (1-p);
            //System.out.println("From: " + 0 + ", length: " + len + ", prob = " + dp[0][len]);
        }
        dp[0][m] = Math.pow(p, m);
        //System.out.println("From: " + 0 + ", length: " + m + ", prob = " + dp[0][m]);

        for (int i = 1; i < m; i++) {
            double prev = 0;
            for (int h = 0; h + k < i; h++) {
                for (int l = k; h + l < i; l++) {
                    if (h + l == i-1) {
                        prev += dp[h][l] / (1 - p);
                    } else {
                        prev += dp[h][l];
                    }
                }
            }
            for (int len = k; i + len < m; len++) {
                dp[i][len] = (1 - prev) * Math.pow(p, len) * (1-p) * (1 - p);
                //prev += dp[i][len];
                //System.out.println("From: " + i + ", length: " + len + ", prob = " + dp[i][len] + " = (1 - " + prev + ") * " + Math.pow(p, len) + " * " + Math.pow(1-p, 2));
            }
            if (m-i >= k) {
                dp[i][m-i] = (1 - prev) * (1 - p) * Math.pow(p, m-i);
                //System.out.println("From: " + i + ", length: " + (m-i) + ", prob = " + dp[i][m-i] + " = (1 - " + prev + ") * " + Math.pow(p, m-i) + " * " + (1-p));
            }
        }

        double sum = 0;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m; j++) {
                sum += dp[i][j];
            }
        }

        System.out.println(df.format(sum));
    }

}
