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
        double res = 0;

        //for (int i = k; i <= m; i++) {
        //    // genau i mal 0 und m - i mal was anderes
        //    double q = Math.pow(p, i) * Math.pow(1-p, m-i);
        //    //for (int j = k; j <= i; j++) {
        //    //    res += q * (m - j + 1);
        //    //}
        //}

        // beginnt an der i-ten stelle die erste streak?
        double[] prob = new double[m];
        prob[0] = Math.pow(p, k);

        for (int i = 1; i < m - k; i++) {
            double q = (1 - prob[i-1]) * Math.pow(p, k);
            prob[i] = q;
        }

        for (int i = 0; i < m; i++) {
            res += prob[i];
        }

        System.out.println(df.format(res));
    }
}
