import java.util.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.io.*;

class Main {
    static Scanner s;
    static Entry[][][] dp;

    public static void main(String[] args) {
        s = new Scanner(System.in);
        int c = s.nextInt();
        mkDP();
        for (int l = 0; l < c; l++)
            solveCase();
    }

    static void mkDP() {
        dp = new Entry[101][101][101];
        initDP();
        calculateDP();
    }

    static void initDP() {
        for (int i = 1; i < 101; i++) {
            dp[0][0][i] = new Entry(0, 0, 1);
            dp[0][i][0] = new Entry(0, 1, 0);
            dp[i][0][0] = new Entry(1, 0, 0);
            for (int j = 1; j < 101; j++) {
                dp[0][i][j] = new Entry(0, 0, 1);
                dp[i][0][j] = new Entry(1, 0, 0);
                dp[i][j][0] = new Entry(0, 1, 0);
            }
        }
    }

    static void calculateDP() {
        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                for (int k = 1; k < 101; k++) {
                    dp[i][j][k] = calcEntry(i, j, k);
                }
            }
        }
    }

    static Entry calcEntry(int a, int b, int c) {
        //boolean print = a < 3 && b < 3 && c < 3;
        //boolean print = false;
        //if (print) System.out.println("a: " + a + " b: " + b + " c: " + c);
        Entry aWins = dp[a][b][c-1];
        Entry bWins = dp[a-1][b][c];
        Entry cWins = dp[a][b-1][c];
        //if (print) System.out.println("aWins: " + aWins + " bWins: " + bWins + " cWins: " + cWins);
        int allp = a * b + a * c + b * c;
        double pa = (double)(a * c) / allp;
        double pb = (double)(a * b) / allp;
        double pc = (double)(b * c) / allp;
        //if (print) System.out.println("pa: " + pa + " pb: " + pb + " pc: " + pc);
        double fa = aWins.b * pa + bWins.b * pb + cWins.b * pc;
        double fb = aWins.h * pa + bWins.h * pb + cWins.h * pc;
        double fc = aWins.n * pa + bWins.n * pb + cWins.n * pc;
        //if (print) System.out.println("fa: " + fa + " fb: " + fb + " fc: " + fc);
        return new Entry(fa, fb, fc);
    }



    static void solveCase() {
        int b, h, n;
        b = s.nextInt();
        h = s.nextInt();
        n = s.nextInt();
        Entry res = dp[b][h][n];
        DecimalFormat df = new DecimalFormat("0.0######");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        System.out.println(df.format(res.b) + " " + df.format(res.h) + " " + df.format(res.n));
    }
}

class Entry {
    double b, h, n;

    public Entry(double b, double h, double n) {
        this.b = b;
        this.h = h;
        this.n = n;
    }
}
