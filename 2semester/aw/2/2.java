import java.util.*;

import java.io.*;

class Main {
    static Scanner s;
    static int nextDFSNumber = 0;
    static int[] dfs, min;
    static ArrayList<ArrayList<Integer>> graph, dfstree;
    static boolean[][] intree;

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
            intree[prev][currentNode] = true;
        }


        for (Integer neighbor : graph.get(currentNode)) {
            dfs(neighbor, currentNode);
        }

        for (Integer neighbor : graph.get(currentNode)) {
            if (intree[currentNode][neighbor]) {
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
        intree = new boolean[n][n];
        nextDFSNumber = 0;
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

        dfs = new int[n];
        min = new int[n];
        for (int i = 0; i < n; i++) {
            dfs[i] = -1;
            min[i] = n + 1000000;
        }

        ArrayList<Integer> sta = new ArrayList<Integer>();

        for (int start = 0; start < n; start++) {
            sta.add(start);
            dfs(start, -1);
            while (start < n && dfs[start] >= 0) start++;
            start--;
        }

        // Too lazy to fix duplicates, no idea how they got in here...
        TreeSet<Integer> res = new TreeSet<Integer>();

        for (int i = 0; i < sta.size(); i++) {
            if (dfstree.get(sta.get(i)).size() > 1) {
                res.add(sta.get(i));
            }
        }

        for (int i = 1; i < n; i++) {
            if (sta.contains(i)) continue;
            for (Integer neighbor : dfstree.get(i)) {
                if (min[neighbor] >= dfs[i]) {
                    res.add(i);
                }
            }
        }

        //System.out.println("res: " + res);

        if (res.size() == 0) {
            System.out.print(-1);
        } else {
            System.out.print(res.pollFirst());
        }

        while (!res.isEmpty()) {
            System.out.print(" " + res.pollFirst());
        }
        System.out.println();
    }
}
