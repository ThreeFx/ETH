import java.util.*;

class A extends B {
    public void m2() {
        System.out.println("a 2");
    }
}

class D extends B {
    public void m1() {
        System.out.println("d 1");
    }
}

class C {
    public String toString() {
        return "c";
    }
    public void m1() {
        System.out.println("c 1");
    }
    public void m2() {
        System.out.println("c 2");
    }
}

class B extends C {
    public String toString() {
        return "b";
    }
    public void m2() {
        System.out.println("b 2");
    }
}

class Main {
    public static void main(String[] args) {
        //Test t = new Test();
        //System.out.println(t.foo(3.0f, 4.0f));
        //System.out.println("j = " + (3 + 12 % 7 / 5 + 4 % 2));
        //System.out.println("i = " + (3 / 4 * 7 % 4 + 1 * 2 / 5));
        //try {
        //    System.out.println("k = " + (48 + 12 / (8 % 4) * 2));
        //} catch (ArithmeticException e) {
        //    System.out.println("k: div by 0");
        //}
        //System.out.println("l = " + ((7 + 14 / 5) % 11));
        //System.out.println("e = " + (5 / (30 / 15.0) * 6 / 10));
        //System.out.println("f = " + (108 / 20 * 3 / 4 / 2.0 + 1.0 / 2));
        //System.out.println("g = " + (3.0 / (2 / 3) + 14 % 10));
        //System.out.println("last = " + (3 * 2 + 4 + "+" + 2 + 3 * 4));
        //System.out.println(nuz(9593117));
        //System.out.println(nuz(39105389));
        //System.out.println(ez(9320));
        //System.out.println(ez(-318));
        //System.out.println(ez(0));
        //System.out.println(gt(5, 7, 3));
        //System.out.println(gt(4, 6, 10));
        //s(2);
        //s(5);
        //s(24);
        //s(28);
        //am(new int [] { 42, 42 });
        //am(new int [] { 6, 2, 4 });
        //am(new int [] { 7, 7, 3, 8, 2 });
        //am(new int [] { 4, 2, 3, 1, 2, 5 });
        //am(new int [] { 6, 0, -1, 3, 5, 0, -3 });
        //bpt();
        //C[] cs = new C[] { new A(), new B(), new C(), new D() };
        //for (C c : cs) {
        //    System.out.println(c);
        //    c.m1();
        //    c.m2();
        //}
        for (String s : new String[] { "a", "e", "i", "o", "u", "ae", "io", "b", "g", "h", "test", "aa" }) {
            System.out.println("isVowel(" + s + "): " + iv(s));
        }
    }

    static void bpt() {
        BP p = new BP(11, 22);
        int[] a = new int[] { 33, 44 };
        int n = 55;
        t(p, a, n);
        m(p, a, n);
        t(p, a, n);
        a[0] = a[1];
        p.x = p.y;
        m(p, a, n);
        t(p, a, n);
    }

    static int m(BP p, int[] a, int n) {
        n = 0;
        a[0] = a[0] + 11;
        a[1] = 77;
        p.x = p.x + 33;
        t(p, a, n);
        return n;
    }

    static void t(BP p, int[] a, int n) {
        System.out.println(p.x + "," + p.y + " " + Arrays.toString(a) + " " + n);
    }

    static void am(int[] a) {
        for (int i = 1; i < a.length - 1; i++) {
            a[i] = a[i - 1] - a[i] + a[i + 1];
        }
        System.out.println(Arrays.toString(a));
    }

    static void s(int n) {
        int x = 1, y = 2;
        while (y < n) {
            if (n % y == 0) {
                n /= y;
                x++;
            } else {
                y++;
            }
        }
        System.out.println(n + " " + x);
    }

    static boolean iv(String s) {
        return s.length() == 1 && "aeiou".contains(s.toLowerCase());
    }

    static boolean gt(int a, int b, int c) {
        int smallest = Math.min(Math.min(a, b), c);
        int largest = Math.max(Math.max(a, b), c);
        int middle = a == smallest || a == largest ?
                b == smallest || b == largest ? c : b : a;

        return smallest + 2 * (middle - smallest) == largest;
    }

    static boolean nuz(int x) {
        while (x > 0) {
            if ((x % 10) % 2 == 0) {
                return false;
            }
            x /= 10;
        }
        return true;
    }

    static int ez(int x) {
        x = x < 0 ? -x : x;
        while (x >= 10) {
            x /= 10;
        }
        return x;
    }
}

class BP {
    int x, y;
    public BP(int ix, int iy) {
        x = ix; y = iy;
    }
}

class Test {
    public void foo(int x, int y) { }
    //private int foo(int x, int y) { return 0; }
    //private void foo(int x, int y) { }
    public Test Test() {return null;}
}
