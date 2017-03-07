import java.util.*;

class Main {
    static Scanner s;

    public static void main(String[] args) {
        s = new Scanner(System.in);
        int cases = s.nextInt();
        for (int c = 0; c < cases; c++) {
            solveCase();
        }
    }

    public static void solveCase() {
        int n = s.nextInt();
        int m = s.nextInt();
        int start = s.nextInt();
        Integer[][] edges = new Integer[m][2];

        for (int i = 0; i < m; i++) {
            edges[i][0] = s.nextInt();
            edges[i][1] = s.nextInt();
        }

        Queue<Integer> q = new LinkedList<Integer>();

        q.add(start);
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = -1;
        }

        int curDist = 0;

        while (q.size() > 0) {
            Queue<Integer> next = new LinkedList<Integer>();
            while (q.size() > 0) {
                int cur = q.remove();
                //System.out.println("visited " + cur);
                if (dist[cur] < 0) {
                    dist[cur] = curDist;
                } else {
                    continue;
                }
                for (int i = 0; i < m; i++) {
                    if (edges[i][0] == cur) {
                        next.add(edges[i][1]);
                    } else if (edges[i][1] == cur) {
                        next.add(edges[i][0]);
                    }
                }
            }
            for (Integer i : next) {
                q.add(i);
            }
            curDist++;
        }

        System.out.print(dist[0]);

        for (int i = 1; i < n; i++) {
            System.out.print(" " + dist[i]);
        }
        System.out.println();
    }
}
