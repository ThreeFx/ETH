import java.util.*;

import java.util.stream.*;

import java.io.*;

class MST {
    static BufferedReader r;

    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(r.readLine());
        for (int c = 0; c < cases; c++) { // lol
            solveCase();
        }
    }

    public static void solveCase() throws IOException {
        List<Integer> arr = Arrays.stream(r.readLine().split(" "))
            .map(Integer::new).collect(Collectors.toList());
        int n = arr.get(1);
        int m = arr.get(0);
        Integer[][] graph = new Integer[m][3];
        for (int i = 0; i < m; i++) {
            graph[i][0] = arr.get(3*i+2);
            graph[i][1] = arr.get(3*i+4);
            graph[i][2] = arr.get(3*i+3);
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

