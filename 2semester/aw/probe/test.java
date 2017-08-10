import java.util.*;
import java.io.*;

class Test {
    public static void main(String[] args) throws Exception {
        Random r = new Random();
        int tests = 100;
        int length_upper = 10 * 1000;
        int length_lower = 2;
        int upper = 1000 * 1000;
        String output = "test";

        PrintWriter in = new PrintWriter(output + ".in");
        in.println(tests);

        PrintWriter sol = new PrintWriter(output + ".out");

        for (int i = 0; i < tests; i++) {
            int len = r.nextInt(length_upper - length_lower) + length_lower;
            int[] arr = new int[len];
            int index = r.nextInt(len);

            in.println(len + " " + index);

            int start = r.nextInt(upper);
            arr[0] = start;
            in.print(start);
            for (int j = 1; j < len; j++) {
                int num = r.nextInt(upper);
                arr[j] = num;
                in.print(" " + num);
            }
            in.println();

            Arrays.sort(arr);
            sol.println(arr[index]);
        }

        in.close();
        sol.close();
    }
}
