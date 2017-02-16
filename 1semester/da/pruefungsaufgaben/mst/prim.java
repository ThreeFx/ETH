import java.util.*;

import java.util.stream.*;

import java.io.*;

class Prim {
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
        int n = arr.get(0);
        int m = arr.get(1);
        List<List<Integer[]>> graph = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }

        System.out.println("graph");
        for (int i = 0; i < m; i++) {
            int from = arr.get(2 + 3 * i) - 1;
            int to   = arr.get(2 + 3 * i + 1) - 1;
            int cost = arr.get(2 + 3 * i + 2);

            graph.get(from).add(new Integer[] { to, cost });
            graph.get(to).add(new Integer[] { from, cost });

            System.out.println("Edge from " + from + " to " + to + " cost: " + cost);
        }
        System.out.println();

        pq.add(new Node(0, 0, 0));
        dist[0] = 0;

        int[] edges = new int[n];

        Set<Integer> inTree = new HashSet<>();

        while (!(inTree.size() == n)) {
            Node no = pq.poll();
            int id = no.id;
            int dis = no.dist;
            if (inTree.contains(id)) continue;
            dist[id] = dis;
            System.out.println("Inspecting node: " + id + " with best distance " + dist[id] + " from " + no.from);
            edges[id] = no.from;
            inTree.add(id);

            for (Integer[] outgoing : graph.get(id)) {
                //System.out.println("Inspecting edge (" + id + "," + outgoing[0]);
                if (!inTree.contains(outgoing[0])) {
                    System.out.println("Adding edge: (" + id + "," + outgoing[0] + ") with cost: " + outgoing[1]);
                    pq.add(new Node(outgoing[0], id, dist[id] + outgoing[1]));
                }
            }
        }

        int sum = 0;
        for (int i = 1; i < n; i++) {
            int to = edges[i];
            for (Integer[] edge : graph.get(i)) {
                if (edge[0] == to) {
                    sum += edge[1];
                    break;
                }
            }
        }

        System.out.println(sum);
    }
}

class Node implements Comparable<Node> {
    int id;
    int from;
    int dist;

    public Node(int id, int from, int dist) {
        this.id = id;
        this.from = from;
        this.dist = dist;
    }

    //public int equals(Object obj) {
    //    if (obj instanceof Node) {
    //        return ((Node)obj).id == this.id;
    //    }
    //    return false;
    //}

    //public int hashCode() {
    //    return id;
    //}

    public int compareTo(Node other) {
        return this.dist - other.dist;
    }
}
