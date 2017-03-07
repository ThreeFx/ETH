import java.util.*;

import java.io.*;

class Main {
    static Scanner s;
    static int nextDFSNumber = 0;
    static int[] dfs, min;
    static ArrayList<ArrayList<Integer>> graph, dfstree;

    public static void main(String[] args) throws IOException {
        s = new Scanner(System.in);
        int cases = s.nextInt();
        for (int c = 0; c < cases; c++) { // lol
            solveCase();
        }
    }

    public static void dfs(int currentNode, int prev) {
        if (dfs[currentNode] >= 0) { return; }

        dfs[currentNode] = nextDFSNumber;
        nextDFSNumber++;
        min[currentNode] = dfs[currentNode];

        if (prev != -1) {
            //dfstree.get(currentNode).add(prev);
            dfstree.get(prev).add(currentNode);
        }


        for (Integer neighbor : graph.get(currentNode)) {
            dfs(neighbor, currentNode);
        }

        for (Integer neighbor : graph.get(currentNode)) {
            if (dfstree.get(currentNode).contains(neighbor)) {
                // It's a tree edge
                min[currentNode] = Math.min(min[currentNode], min[neighbor]);
            } else { // It's a backedge
                min[currentNode] = Math.min(min[currentNode], dfs[neighbor]);
            }
        }
    }

    public static void solveCase() throws IOException {
        int n = s.nextInt();
        int m = s.nextInt();
        graph = new ArrayList<ArrayList<Integer>>();
        dfstree = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
            dfstree.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            int from = s.nextInt();
            int to = s.nextInt();
            graph.get(to).add(from);
            graph.get(from).add(to);
        }

        //prev = graph.get(0).get(0);
        prev = -1;

        dfs = new int[n];
        min = new int[n];
        for (int i = 0; i < n; i++) {
            dfs[i] = -1;
            min[i] = n + 1000000;
        }



        dfs(0, -1);

        ArrayList<Integer> res = new ArrayList<Integer>();
        if (dfstree.get(0).size() > 1) {
            res.add(0);
        }

        for (int i = 1; i < n; i++) {
            if (dfs[i] < 0) {
                System.out.println(-1);
                return;
            }
            for (Integer neighbor : dfstree.get(i)) {
                if (min[neighbor] >= dfs[i]) {
                    res.add(i);
                }
            }
        }


        if (res.size() == 0) {
            System.out.print(-1);
        } else {
            System.out.print(res.get(0));
        }

        for (int i = 1; i < res.size(); i++) {
            System.out.print(" " + res.get(i));
        }
        System.out.println();
    }
}
