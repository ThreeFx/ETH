import java.util.*;

import java.util.*;

import java.io.*;

public class Main {
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

    }

    public static int[] readMany() throws IOException {
        String[] s = r.readLine().split(" ");
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++)
            a[i] = Integer.parseInt(s[i]);
        return a;
    }
}

class FibHeap<T extends Gradeable> {
    Node<T> min;
    int topLevelTrees;

    public FibHeap() {
        this.min = null;
        this.topLevelTrees = 0;
    }

    public void insert(T value) {
        Node<T> newNode = new Node(value);
        if (this.min == null) {
            this.min = newNode;
        } else {
            this.min.next.prev = newNode;
            newNode.next = this.min.next;
            this.min.next = newNode;
            newNode.prev = this.min;

            if (newNode.compareTo(this.min) < 0) {
                this.min = newNode;
            }
        }
    }


    public void union(FibHeap<T> second) {
        this.min.concat(second.min);
        if (second.min.compareTo(this.min) < 0) {
            this.min = second.min;
        }
    }

    public T deleteMin() {
        T result = this.min.value;

        if (this.min.degree > 0) {
            this.min.child.setParent(this.min.parent);
            this.min.concat(this.min.child);
        }

        Node<T> current = this.min.next;

        Node<T> nextMin = current;
        while (current != this.min) {
            if (current.compareTo(nextMin) < 0) {
                nextMin = current;
            }
            current = current.next;
        }
        current = nextMin;

        this.min.delete();
        this.min = current;

        if (this.min != null) {
            ArrayList<Node<T>> numberChildren = new ArrayList<Node<T>>();
            current = this.min;
            // TODO
            while (someCond) {
                Node<T> candidate = numberChildren.get(current.degree);
                if (candidate != null) {
                    numberChildren.set(current.degree, null);
                    Node<T> smaller = (current.compareTo(candidate) <= 0) ? current : candidate;
                    Node<T> larger = smaller == current ? candidate : current;
                    larger.delete();
                    larger.setParent(smaller);
                    current = smaller.merge(larger);
                    continue;
                } else {
                    numberChildren.add(current.degree, current);
                }
                current = current.next;
            }
        }
    }

    public void decreaseKey(Node<T> toDecrease, Integer newValue) {
        toDecrease.value.decreaseKey(newValue);
        if (toDecrease.parent == null || toDecrease.parent.value.getValue() < newValue) {
            // Do nothing
        } else {
            if (!toDecrease.parent.marked) {
                toDecrease.parent.marked = true;
                toDecrease.delete();
                this.min.concat(toDecrease);
            } else {
                Node<T> parent = toDecrease.parent;
                toDecrease.delete();
                this.min.concat(toDecrease);

                Node<T> pparent = parent.parent;
                parent.delete();
                this.min.concat(parent);

                while (pparent != null && pparent.marked) {
                    Node<T> nextP = pparent.parent;
                    pparent.marked = false;
                    pparent.delete();
                    this.min.concat(pparent);
                    pparent = nextP;
                }

                if (pparent != null) {
                    pparent.marked = true;
                }
            }
        }

        if (toDecrease.compareTo(min) < 0) {
            this.min = toDecrease;
        }
    }
}

interface Gradeable {
    Integer getValue();
    void decreaseKey(Integer newValue);
}

class Node<T extends Gradeable> extends Comparable<Node> {
    Node next;
    Node prev;
    Node child;
    Node parent;
    boolean marked;
    int degree;

    T value;

    public Node(T value) {
        this.value = value;
        this.next = this;
        this.prev = this;
        this.child = null;
        this.parent = null;
        this.marked = false;
        this.degree = 0;
    }

    public Node<T> concat(Node<T> other) {
        this.prev.next = other;
        other.prev.next = this;
        Node<T> minPrev = this.prev;
        this.prev = other.prev;
        other.prev = minPrev;
        return this;
    }

    public Node<T> merge(Node<T> other) {
        Node<T> smaller = (this.compareTo(other) <= 0) ? this : other;
        Node<T> larger = (this == smaller) ? other : this;
        if (smaller != null) {
            smaller.concat(larger);
        } else {
            smaller.child = larger;
        }
        smaller.degree++;
        return smaller;
    }

    public void setParent(Node<T> parent) {
        Node<T> cur = this;
        while (cur.parent != parent) {
            cur.parent = parent;
            cur = cur.next;
        }
    }

    public Node<T> delete() {
        if (this.next == this) {
            return null;
        }

        if (this.parent != null) {
            this.parent.degree--;
        }

        this.next.prev = this.prev;
        this.prev.next = this.next;
        this.next = this;
        this.prev = this;
        this.parent = null;
        return this;
    }

    public int compareTo(Node<T> other) {
        return this.value.getValue().compareTo(other.value.getValue());
    }
}
