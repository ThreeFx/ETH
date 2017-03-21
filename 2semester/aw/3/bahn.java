import java.util.*;

import java.io.*;

class Main {
    static Scanner s;

    public static void main(String[] args) throws IOException {
        s = new Scanner(System.in);
        int cases = s.nextInt();
        for (int c = 0; c < cases; c++) { // lol
            solveCase();
        }
    }

    public static void solveCase() throws IOException {
        int n = s.nextInt();
        s.nextLine();
        HashMap<String, Pair> map = new HashMap<String, Pair>();
        for (int i = 0; i < n; i++) {
            String zettel = s.nextLine();
            System.out.println(zettel);
            String anfang = zettel.substring(0, 2);
            String ende = zettel.substring(1, 3);

            Pair a = map.get(anfang);
            a = a == null ? new Pair() : a;
            a.from++;
            map.put(anfang, a);

            Pair e = map.get(ende);
            e = e == null ? new Pair() : e;
            e.to++;
            map.put(ende, e);
        }

        boolean possible = true;
        boolean canStart = false, canEnd = false;
        for (Map.Entry<String, Pair> sp : map.entrySet()) {
            Pair p = sp.getValue();
            String ss = sp.getKey();
            System.out.println("analyzing: " + ss + " (" + p.to + ", " + p.from + ")");

            int diff = p.from - p.to;
            switch (diff) {
                case -1:
                    if (!canStart) canStart = true;
                    break;
                case 1:
                    if (!canEnd) canEnd = true;
                    else possible = false;
                    break;
                case 0:
                    break;
                default:
                    possible = false;
                    break;
            }
            if (!possible) break;
        }

        if (map.size() == 1 || (possible && canStart && canEnd)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}

class Pair {
    int to, from;
}
