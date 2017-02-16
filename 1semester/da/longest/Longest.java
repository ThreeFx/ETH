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
        int n = s.nextInt();
        //ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(n);
        int m = s.nextInt();
        ArrayList<ArrayList<Integer>> g = readGraph(n, m);
        ArrayList<Integer> candidates = new ArrayList<Integer>();

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            boolean b = true;
            for (int j = 0; j < n; j++) {
                if (g.get(j).contains(i)) {
                    b = false;
                    break;
                }
            }
            if (b) candidates.add(i);
        }

        int max = 0;

        for (int c : candidates) {
            max = Math.max(longest(c, g, dp), max);
        }

        System.out.println(max + 1);
    }

    static int longest(int c, ArrayList<ArrayList<Integer>> g, int[] dp) {
        if (dp[c] > 0) return dp[c];

        for (int i = 0; i < g.get(c).size(); i++) {
            dp[c] = Math.max(dp[c], 1 + longest(g.get(c).get(i), g, dp));
        }

        return dp[c];
    }

    public static ArrayList<ArrayList<Integer>> readGraph(int n, int m) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(m);

        //int[][] longest = new int[n][n];

        for (int i = 0; i < n; i++) {
            graph.add(i, new ArrayList<Integer>());
        }
        
        //ArrayList<ArrayList<Integer>> longestFrom = new ArrayList<ArratList<Integer>>(n);
        //ArrayList<ArrayList<Integer>> longestTo =   new ArrayList<ArratList<Integer>>(n);

        //for (int i = 0; i < n; i++) {
        //    longestFrom.add(i, new ArrayList<Integer>());
        //    longestTo.add(i, new ArrayList<Integer>());
        //}
        
        //int max = 0;

        for (int i = 0; i < m; i++) {
            int from = s.nextInt();
            int to = s.nextInt();

            //longest[from][to] = 
            //max = Math.max(longest[from][to]
            graph.get(from).add(to);
        }

        //System.out.println(max);
            
        return graph;
    }
}
