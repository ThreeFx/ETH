class Main {
    static long MAX_LONG = 0x7FFFFFFFFFFFFFFFL;
    static long[] totest = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 };


    public static void main(String[] args) throws Exception {
        int c = fastNextInt();
        for (int l = 0; l < c; l++)
            solveCase();
    }

    static void solveCase() throws Exception {
        long input = fastNextLong();
        //System.out.println(addl(input, 5, MAX_LONG));
        //System.out.println(mull(input, 5, MAX_LONG));
        //System.out.println(expl(input, 2, MAX_LONG));
        if (isprime(input)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
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

    static boolean isprime(long n) {
        if (n == 2 || n == 3) return true;
        if (n == 1 || n % 2 == 0) return false;

        long d = n - 1;
        long r = 0;

        while (d % 2 == 0) {
            d /= 2;
            r++;
        }

        for (int i = 0; i < totest.length; i++) {
            boolean cont = false;
            long a = totest[i];
            long x = expl(a, d, n);

            if (x == 1 || x == n - 1)
                continue;

            for (int j = 0; j < r - 1; j++) {
                x = mull(x, x, n);
                if (x == 1)
                    return false;
                else if (x == n - 1) {
                    cont = true;
                    break;
                }
            }
            if (!cont) return false;
        }
        return true;
    }

    static long addl(long a, long b, long mod) {
        //return ((a + b) & MAX_LONG) % mod;
        a %= mod; b %= mod;
        if (b == 0) return a;
        b = mod - b;
        if (a >= b)
            return a - b;
        else
            return mod - b + a;
    }

    static long mull(long a, long b, long mod) {
        long res = 0;
        for (int i = 0; i < 63; i++) {
            if ((b & (1L << i)) > 0) {
                res = addl(res, a, mod);
            }
            a = addl(a, a, mod);
            //System.out.println(i + ". a: " + a + " b: " + b + " res: " + res);
        }
        return res;
    }

    static long expl(long a, long b, long mod) {
        long res = 1;
        for (int i = 0; i < 63; i++) {
            if ((b & (1L << i)) > 0) {
                res = mull(res, a, mod);
            }
            a = mull(a, a, mod);
        }
        return res;
    }
}
