import java.util.*;

import java.io.*;

class Main {
    static BufferedReader r;
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
        int m = s.nextInt();
        Integer[][] graph = new Integer[m][3];
        for (int i = 0; i < m; i++) {
            graph[i][0] = s.nextInt();
            graph[i][1] = s.nextInt();
            graph[i][2] = s.nextInt();
        }

        Arrays.sort(graph, new Comparator<Integer[]>() {
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[2].compareTo(o2[2]);
            }
        });

        int[] unionarr = new int[n+1];
        for (int i = 0; i <= n; i++) {
            unionarr[i] = i;
        }

        int cost = 0;
        for (int i = 0; i < m; i++) {
            int a = find(unionarr, graph[i][0]);
            int b = find(unionarr, graph[i][1]);
            if (a != b) {
                union(unionarr, a, b);
                cost += graph[i][2];
            }
        }

        System.out.println(cost);
    }

    static int find(int[] arr, int a) {
        while (arr[a] != a) a = arr[a];
        return a;
    }

    static void union(int[] arr, int a, int b) {
        int aold = a;
        while (arr[a] != a || arr[b] != b) {
            a = arr[a];
            b = arr[b];
        }
        arr[aold] = b;
    }
}

