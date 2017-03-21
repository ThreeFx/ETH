import java.util.*;
import java.io.*;

class Main {
    static Scanner s;
    static TreeSet<Integer> left, right;

    public static void main(String[] args) {
        s = new Scanner(System.in);
        int c = s.nextInt();
        for (int l = 0; l < c; l++)
            solveCase();
    }

    static void solveCase() {
        int n = s.nextInt();
        //ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(n);
        int m = s.nextInt();
        int friend = s.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            int from = s.nextInt();
            int to = s.nextInt();

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        left = new TreeSet<Integer>();
        right = new TreeSet<Integer>();

        Queue<Integer> q = new LinkedList<Integer>();
        q.add(0);
        boolean leftH = true;
        boolean possible = true;
        while (!q.isEmpty()) {
            Queue nq = new LinkedList<Integer>();
            while (!q.isEmpty()) {
                int current = q.poll();
                TreeSet<Integer> ts, os;
                if (leftH) {
                    ts = left;
                    os = right;
                } else {
                    ts = right;
                    os = left;
                }
                if (ts.contains(current)) {
                    continue;
                } else if (os.contains(current)) {
                    possible = false;
                    break;
                }
                ts.add(current);
                for (int neigh : graph.get(current)) {
                    nq.add(neigh);
                }
            }
            q = nq;
            leftH = !leftH;
        }

        if (!possible) {
            System.out.println("no");
        } else {
            TreeSet t;
            if (left.contains(friend)) {
                t = left;
            } else {
                t = right;
            }
            System.out.print(t.pollFirst());
            while (!t.isEmpty()) {
                System.out.print(" " + t.pollFirst());
            }
            System.out.println();
        }
    }
}
