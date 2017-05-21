import java.util.*;
import java.io.*;

class Main {
    static Scanner s;
    static Random r;
    static int n, d;
    static double[][] eqn;

    public static void main(String[] args) {
        s = new Scanner(System.in);
        r = new Random();
        int c = s.nextInt();
        for (int l = 0; l < c; l++)
            solveCase();
    }

    static void solveCase() {
        d = s.nextInt();
        n = s.nextInt();

        eqn = new double[n][d+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= d; j++) {
                eqn[i][j] = s.nextDouble();
            }
        }

        double v = s.nextDouble();

        int smallit = 1000;
        int hits = 0;
        int total = 0;

        for (int i = 0; i < smallit; i++) {
            double[] rand = newrand();

            //System.out.println(Arrays.toString(rand));

            if (ishit(rand)) {
                hits++;
            }
            total++;
        }

        double ratio = (double) hits / total;
        int iters = -1;

        if (Math.abs(0.5 - ratio) > 0.25) {
            iters = 400000;
        } else {
            iters = 200000;
        }

        for (int i = 0; i < iters; i++) {
            double[] rand = newrand();
            if (ishit(rand)) {
                hits++;
            }
            total++;
        }

        ratio = (double) hits / total;

        System.out.println(v * 0.99 <= ratio && ratio <= v * 1.01 ? "yes" : "no");
    }

    static double[] newrand() {
        double[] res = new double[d];
        for (int i = 0; i < d; i++) {
            res[i] = r.nextDouble();
        }
        return res;
    }

    static boolean ishit(double[] rand) {
        for (int i = 0; i < n; i++) {
            double x = 0;
            for (int j = 0; j < d; j++) {
                x += rand[j] * eqn[i][j];
            }

            if (x > eqn[i][d]) {
                return false;
            }
        }
        return true;
    }

}
