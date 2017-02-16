class Test {
        public static void main(String[] z) {
                char[] s = z[0].toCharArray();
                int[][] a = new int[s.length][s.length];

                f(s, a, s.length - 1, 0);

                System.out.print("    ");
                for (int i = 0; i < a.length; i++)
                        System.out.print(i + " ");
                System.out.println();

                System.out.print("    ");
                for (int i = 0; i < a.length; i++)
                        System.out.print(s[i] + " ");
                System.out.println();
                for (int i = 0; i < a.length; i++) {
                        System.out.print(i + " " + s[i]);
                        for (int j = 0; j <= i; j++) {
                                System.out.print(" " + (a[i][j] == 1 ? "T" : "F"));
                        }
                        System.out.println();
                }
        }

        public static boolean f(char[] s, int[][] a, int i, int j) {
                if (i <= j) {
                        if (i >= 0 && j < s.length)
                                a[i][j] = 1;
                        return true;
                }

                if (a[i][j] != 0) return a[i][j] == 1;

                int oben = i - 1;
                int rechts = j + 1;

                f(s, a, oben, j);
                f(s, a, i, rechts);

                boolean b = f(s, a, oben, rechts) && s[i] == s[j];
                System.out.println(i + " and " + j + ": " + s[i] + " == " + s[j] + ": " + s[i] == s[j] + " => " + b);
                a[i][j] = b ? 1 : 2;
                return b;
        }
}
