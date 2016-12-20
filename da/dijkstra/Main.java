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

//        FibHeap<Trivial> heap = new FibHeap<Trivial>();
//        ArrayList<Node<Trivial>> values = new ArrayList<Node<Trivial>>();
//
//        Scanner s = new Scanner(System.in);
//        while (true) {
//            String l = s.nextLine();
//            if (l.equals("q")) {
//                break;
//            }
//
//            String[] stmt = l.split(" ");
//
//            if (stmt[0].equalsIgnoreCase("i")) {
//                Trivial trivial = new Trivial(Integer.parseInt(stmt[1]));
//                Node<Trivial> n = heap.insert(trivial);
//                values.add(n);
//            }
//
//            if (stmt[0].equalsIgnoreCase("d")) {
//                System.out.println(heap.deleteMin());
//            }
//
//            if (stmt[0].equalsIgnoreCase("dk")) {
//                heap.decreaseKey(values.get(Integer.parseInt(stmt[1])), Integer.parseInt(stmt[2]));
//            }
//
//            heap.print();
//        }
//    }

    public static void solveCase() throws IOException {
        int n = Integer.parseInt(r.readLine());
        FibHeap<Data> heap = new FibHeap<Data>();

        ArrayList<ArrayList<Node<Data>>> field = new ArrayList<ArrayList<Node<Data>>>(n);

        for (int i = 0; i < n; i++) {
            String[] nums = r.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                Data d = new Data(Integer.parseInt(nums[j]), i, j);
                Node<Data> n = heap.insert(d);
                field.get(i).add(n);
            }
            // For efficiency purposes.
            Data d = heap.deleteMin();
        }

    }

    public static int[] readMany() throws IOException {
        String[] s = r.readLine().split(" ");
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++)
            a[i] = Integer.parseInt(s[i]);
        return a;
    }
}

//class Trivial implements Gradeable {
//    public int value;
//
//    public Trivial(int value) {
//        this.value = value;
//    }
//
//    public Integer getValue() {
//        return this.value;
//    }
//
//    public void decreaseKey(Integer value) {
//        this.value = value;
//    }
//}

class FibHeap<T extends Gradeable> {
    Node<T> min;
    int topLevelTrees;

    public FibHeap() {
        this.min = null;
        this.topLevelTrees = 0;
    }

    public void print() {
        //System.out.println("FibHeap: TLT: " + topLevelTrees);
        min.print();
    }

    public Node<T> insert(T value) {
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
        this.topLevelTrees++;
        return newNode;
    }


    public void union(FibHeap<T> second) {
        this.min.concat(second.min);
        if (second.min.compareTo(this.min) < 0) {
            this.min = second.min;
        }
        this.topLevelTrees += second.topLevelTrees;
    }

    public T deleteMin() {
        T result = this.min.value;
        this.topLevelTrees += this.min.degree - 1;

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

        if (this.min.next == this.min && this.min.degree == 0) {
            this.min = null;
            return result;
        }

        // Now topLevelTrees holds the correct value

        HashMap<Integer, Node<T>> numberChildren = new HashMap<Integer, Node<T>>();
        current = this.min;

        //System.out.println("  deleteMin: TLT: " + topLevelTrees);
        int tlt = topLevelTrees;
        // TODO This has to be smaller than that.
        for (int i = 0; i < tlt * tlt; i++) {
            Node<T> candidate = numberChildren.get(current.degree);
            //System.out.println("\n\ncandidate:");
            //if (candidate != null) candidate.print();
            //System.out.println("\n");
            if (candidate != null && candidate != current) {
                while (candidate != null) {
                    numberChildren.put(current.degree, null);
                    //System.out.println("Found 2 nodes with same degree (" + current.degree + "): " + current + " and " + candidate);

                    Node<T> smaller = (current.compareTo(candidate) <= 0) ? current : candidate;
                    Node<T> larger = smaller == current ? candidate : current;

                    current = smaller.merge(larger);
                    topLevelTrees--;

                    candidate = numberChildren.put(current.degree, current);
                }
            } else {
                numberChildren.put(current.degree, current);
            }
            current = current.next;
        }

        return result;
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
                topLevelTrees++;
            } else {
                Node<T> parent = toDecrease.parent;
                toDecrease.delete();
                this.min.concat(toDecrease);
                topLevelTrees++;

                Node<T> pparent = parent.parent;
                parent.delete();
                this.min.concat(parent);
                topLevelTrees++;

                while (pparent != null && pparent.marked) {
                    Node<T> nextP = pparent.parent;
                    pparent.marked = false;
                    pparent.delete();
                    this.min.concat(pparent);
                    pparent = nextP;
                    topLevelTrees++;
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

class Node<T extends Gradeable> implements Comparable<Node<T>> {
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

    public Node<T> merge(Node<T> newChild) {
        newChild.delete();
        newChild.setParent(this);
        if (this.child == null) {
            this.child = newChild;
        } else {
            this.child.concat(newChild);
        }
        this.degree++;
        return this;
    }

    public void setParent(Node<T> parent) {
        Node<T> cur = this;
        while (cur.parent != parent) {
            cur.parent = parent;
            cur = cur.next;
        }
    }

    public Node<T> delete() {
        if (this.parent != null) {
            this.parent.degree--;
            if (this.next != this) {
                this.parent.child = this.next;
            } else {
                this.parent.child = null;
            }
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

    public void print() {
        System.out.println("node:     " + this);
        System.out.println("value:    " + this.value.getValue());
        System.out.println("siblings: " + this.getSiblings());
        System.out.println("parent:   " + this.parent);
        System.out.println("degree:   " + this.degree);
        System.out.println("children: " + this.getChildren());
        System.out.println("marked:   " + this.marked);
        System.out.println();

        if (child != null) {
            child.print();
        }

        Node<T> sibl = this.next;

        while (sibl != this) {
            sibl.printOne();
            sibl = sibl.next;
        }
    }

    public void printOne() {
        System.out.println("node:     " + this);
        System.out.println("value:    " + this.value.getValue());
        System.out.println("siblings: " + this.getSiblings());
        System.out.println("parent:   " + this.parent);
        System.out.println("degree:   " + this.degree);
        System.out.println("children: " + this.getChildren());
        System.out.println("marked:   " + this.marked);
        System.out.println();


        if (child != null) {
            child.print();
        }
    }

    private String getSiblings() {
        Node<T> cur = this.next;
        String res = "";
        while (this != cur) {
            res += cur + " ";
            cur = cur.next;
        }
        return res;
    }

    private String getChildren() {
        if (this.child != null) {
            return this.child + " " + this.child.getSiblings();
        }
        return "null";
    }
}
