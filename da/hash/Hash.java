import java.util.*;
import java.io.*;

class Main {
    static Scanner s;

    public static void main(String[] args) {
        s = new Scanner(System.in);
        int c = s.nextInt();
        for (int l = 0; l < c; l++)
            solveCase();
    }

    static void solveCase() {
        int la = s.nextInt();
        int lb = s.nextInt();
        int[] a = readMany(la);
//        la = 9;
//        a = new int[] {2, 3, 1, 2, 3, 1, 2, 3, 4};
        int[] b = readMany(lb);
        int c = 1021;
        int m = 32768;

        int bHash = hash(b, lb, c, m);
        //System.out.println("bHash: " + bHash);

        ArrayList<Integer> candidates = new ArrayList<Integer>();

        int hash = hash(a, lb, c, m);
        if (hash == bHash) candidates.add(0);
        System.out.println("hash: " + hash);

        for (int i = 0; i < la - lb; i++) {
            //System.out.println("i: " + i + "  i + lb: " + (i + lb));
            //System.out.println("a[i]: " + a[i] + "  a[i + lb]: " + a[i + lb]);

            hash = nextHash(hash, a[i], a[i+lb], lb, c, m);

            // //System.out.println("hash: " + hash);
            if (hash==bHash) candidates.add(i+1);
        }

        for (int i : candidates) {
            boolean match = true;
            for (int j = i; j < i + lb; j++) {
                if (b[j-i] != a[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                System.out.print(i + " ");
            }
        }

//        for (int i : new int[] {1, 2, 3, 4, 5, 6, 7, 129381, 32423, 123124, 523}) {
//            //System.out.print("3^" + i + ": " + modPow(3, i, m) + "   ");
//        }

        System.out.println("DONE");

    }

    static int hash(int[] a, int r, int c, int m) {
        int res = 0;
        for (int i = 0; i < r; i++) {
            //                         correct this
            res += modMult(a[i], modPow(c, r - i - 1, m), m);
            res = res & (m-1);
        }
        return res;
    }

    static int nextHash(int current, int toDelete, int nextval, int k, int c, int m) {
        return (modMult(c, current, m) + nextval + ((m - modPow(c, k, m)) * toDelete)) & (m-1);
    }

    static int modMult(int a, int b, int m) {
        return ((a & (m-1)) * (b & (m-1))) & (m-1);
    }

    static int modPow(int b, int e, int m) {
        int r = 1;
        for (int exp = 1; exp <= e; exp*=2) {
            if ((e & exp) != 0) {
                r = modMult(r, b, m);
            }
            b = modMult(b, b, m);
        }
        return r;
    }

    public static int[] readMany(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = s.nextInt();
        return a;
    }
}
