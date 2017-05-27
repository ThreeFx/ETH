import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

class Main {

    static int numVer; // the number of vertices in the graph
    static ArrayList<Integer> graph[]; // flow is computed on this graph
    static int[][] capacity, flow;

    // Compute a path with positive residual capacity using BFS
    // Return true if such a path exists and false otherwise
    public static boolean augmentingPathExists(int previousVertexOnPath[]) {
        // Allocate space for auxilary data structures
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[numVer];

        // Initialization of auxilary data structures
        for(int i = 0; i < numVer; i++) visited[i] = false;
        queue.add(0);
        visited[0] = true;

        // BFS
        while(!queue.isEmpty()) {
            int v = queue.poll();
            for(int w : graph[v])
                if(!visited[w] && capacity[v][w] > flow[v][w]) {
                    visited[w] = true;
                    previousVertexOnPath[w] = v;
                    queue.add(w);
                    if(w == numVer-1) return true;
                }
        }

        return false;
    }

    // Computes the value of a maximum flow
    public static int computeMaximumFlow() {
        int i, flowOnPath;
        // Find paths with BFS and return path in previousVertexOnPath array
        int[] previousVertexOnPath = new int[numVer];
        // Start with empty flow
        int maxFlow = 0;
        // Use augmenting path P as long as possible
        while(augmentingPathExists(previousVertexOnPath)) {
            // Compute smallest remaining capacity on P
            flowOnPath = Integer.MAX_VALUE;
            for(i = numVer-1; i != 0; i = previousVertexOnPath[i]) {
                int p = previousVertexOnPath[i];
                flowOnPath = Math.min(flowOnPath, capacity[p][i] - flow[p][i]);
            }
            // Add the smallest remaining capacity to each edge of P
            for(i = numVer-1; i != 0; i = previousVertexOnPath[i]) {
                int p = previousVertexOnPath[i];
                flow[p][i] += flowOnPath;
                flow[i][p] -= flowOnPath;
            }
            maxFlow += flowOnPath;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        // Create a new Scanner object for reading the input
        Scanner sc = new Scanner(System.in);

        // Read the number of testcases to follow
        int t = sc.nextInt();

        // Iterate over the testcases and solve the problem
        for (int i = 0; i < t; i++) {
            testCase(sc);
        }
    }

    public static void testCase(Scanner sc) {
        // Read the input
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        boolean[][] full = new boolean[n][m];

        for (int i = 0; i < k; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            full[x][y] = true;
        }

        numVer = (2 * n * m) + 2;

        // Don't forget to initialize numVer
        // Initialize the graph
        capacity = new int[numVer][numVer];
        flow = new int[numVer][numVer];

        graph = (ArrayList<Integer>[])new ArrayList[numVer];
        for(int i = 0; i < numVer; i++)
            graph[i] = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!full[i][j]) {
                    int s = 0;
                    int dom = i * m + j;
                    int t = 2 * n * m + 1;
                    graph[s].add(dom + 1);
                    graph[dom + 1].add(s);
                    capacity[s][dom + 1] = 1;

                    graph[t].add(n * m + dom + 1);
                    graph[n * m + dom + 1].add(t);
                    capacity[n * m + dom + 1][t] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!full[i][j]) {
                    int f = i * m + j + 1;
                    //System.out.print("(" + i + ", " + j + ")[" + from + "] ");
                    if (i + 1 < n && !full[i+1][j]) {
                        int g = (i + 1) * m + j + 1;

                        graph[f].add(g + n * m);
                        graph[g + n * m].add(f);
                        graph[g].add(f + n * m);
                        graph[f + n * m].add(g);
                        capacity[f][g + n * m] = 1;
                        capacity[g][f + n * m] = 1;
                        //System.out.print("(" + (i + 1) + ", " + j + ")[" + to + "] ");
                    }

                    if (j + 1 < m && !full[i][j+1]) {
                        int g = i * m + (j + 1) + 1;

                        graph[f].add(g + n * m);
                        graph[g + n * m].add(f);
                        graph[g].add(f + n * m);
                        graph[f + n * m].add(g);
                        capacity[f][g + n * m] = 1;
                        capacity[g][f + n * m] = 1;
                        //System.out.print("(" + i + ", " + (j + 1) + ")[" + to + "]");
                    }
                    //System.out.println();
                }
            }
        }

        // Here add the edges to the graph and initialize capacity and flow!
        // Example:
        // graph[0].add(1); <-- adding an edge from vertex 0 to vertex 1
        // graph[1].add(0); <-- add the reverse edge!
        // flow[0][1] = 0;
        // capacity[0][1] = 1;  <- replace 1 with your desired capacity
        // capacity[1][0] = 0;  <- the reverse edge should have capacity 0

        // IMPORTANT: Vertex 0 is always considered as the source
        //            and vertex numVer - 1 as the sink

        // Compute maximum flow
        int maxflow;
        maxflow = computeMaximumFlow();

        // Output result
        System.out.println(maxflow / 2);
    }

}
