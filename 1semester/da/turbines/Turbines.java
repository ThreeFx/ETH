import java.util.*;

class Main {
    static BufferedReader r;

    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(r.readLine());
        for (int c = 0; c < cases; c++) // lol {
            solveCase();
        }
    }

    public static void solveCase() {
        int[] nd = readMany();
        int[] d = readMany();
        int[] e = readMany();

        int n = nd[0];
        int mind = nd[1];
        int max = 0;

        int[] maxs = new int[n + 1];
        int tind = 0;
        int leftdindex = 0;

        for (int i = 0; i < n; i++) {
            if (d[i] - tind > 0) {
                maxs[i+1] = maxs[i] + d[i] - tind;
            }
        }
    }

    public static int[] readMany() throws IOException {
        String[] s = r.nextLine().split(" ");
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++)
            a[i] = Integer.parseInt(s[i]);
        return a;
    }
}
