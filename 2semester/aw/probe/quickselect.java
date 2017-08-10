import java.util.*;
import java.text.*;
import java.math.*;

class Main {
    static DecimalFormat df = new DecimalFormat("0.0####");
    static Random rng = new Random();
    public static void main(String[] args) throws Exception {
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        int c = fastNextInt();
        for (int l = 0; l < c; l++)
            solveCase();
    }

    static int n, k;
    static int[] arr;

    static void solveCase() throws Exception {
        n = fastNextInt();
        k = fastNextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = fastNextInt();
        }

        System.out.println(quickselect(0, n - 1, k));
    }

    static int quickselect(int left, int right, int k) {
        if (right - left < 1) return arr[left];

        int i = left - 1, j = right + 1;
        int pivot = arr[left];

        while (i < j) {
            do { i++; } while (arr[i] < pivot);
            do { j--; } while (arr[j] > pivot);

            if (i < j)
                swap(i, j);
        }

        //System.out.println("j: " + j + ", pivot: " + pivot + " arr: " + Arrays.toString(arr));

        if (k <= j) return quickselect(left, j, k);
        else return quickselect(j + 1, right, k);
    }

    static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    static int fastNextInt() throws Exception {
        int res = 0;
        int read = System.in.read();
        while (read < '0') {
            read = System.in.read();
        }
        do {
            res *= 10;
            res += read - 0x30;
            read = System.in.read();
        } while (read >= '0');
        return res;
    }

    static long fastNextLong() throws Exception {
        long res = 0;
        int read = System.in.read();
        while (read < '0') {
            read = System.in.read();
        }
        do {
            res *= 10L;
            res += (read - 0x30L);
            read = System.in.read();
        } while (read >= '0');
        return res;
    }
}
