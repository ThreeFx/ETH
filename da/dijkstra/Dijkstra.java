import java.util.*;

import java.io.*;

class Main {
    static BufferedReader r;

    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(r.readLine());
        for (int c = 0; c < cases; c++) { // lol
            solveCase();
        }
    }

    public static void solveCase() throws IOException {
        int n = Integer.parseInt(r.readLine());
        //FibHeap<Data> heap = new FibHeap<Data>();
        PriorityQueue<Data> pq = new PriorityQueue<Data>();
        int[][] field = new int[n][n];
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] line = r.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int data = Integer.parseInt(line[j]);
                field[i][j] = data;
                dist[i][j] = 100000;
                if (data == 0) {
                    pq.offer(new Data(i, j, data));
                    dist[i][j] = 0;
                }
            }
        }

        Data data;
        data = pq.poll();
        while (!done(data, n)) {
            for (Integer[] neighbors : getNeighbors(data, n)) {
                int newX = neighbors[0];
                int newY = neighbors[1];
                int oldX = data.x;
                int oldY = data.y;

                int newDist = dist[oldX][oldY] + field[newX][newY];
                if (newDist < dist[newX][newY]) {
                    dist[newX][newY] = newDist;
                    pq.offer(new Data(newX, newY, dist[newX][newY]));
                }
            }
            data = pq.poll();
        }

        System.out.println(data.data);
    }

    private static ArrayList<Integer[]> getNeighbors(Data data, int n) {
        ArrayList<Integer[]> res = new ArrayList<Integer[]>();
        int x = data.x;
        int y = data.y;
        if (x > 0) {
            res.add(new Integer[] { x - 1, y });
        }
        if (y > 0) {
            res.add(new Integer[] { x, y - 1 });
        }
        if (x < n - 1) {
            res.add(new Integer[] { x + 1, y });
        }
        if (y < n - 1) {
            res.add(new Integer[] { x, y + 1 });
        }
        return res;
    }

    private static boolean done(Data data, int n) {
        return data.x == 0 || data.x == n - 1
            || data.y == 0 || data.y == n - 1;
    }
}

class Data implements Comparable<Data> {
    int x, y;
    int data;

    public Data(int x, int y, int data) {
        this.x = x;
        this.y = y;
        this.data = data;
    }

    public int compareTo(Data other) {
        return this.data - other.data;
    }

    public boolean equals(Object other) {
        if (other instanceof Data) {
            Data d = (Data)other;
            return this.x == d.x && this.y == d.y && this.data == d.data;
        }
        return false;
    }
}
