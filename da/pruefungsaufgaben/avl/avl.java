import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        AVLTree<Integer> avltree = new AVLTree<>();
        Integer next;
        while ((next = new Integer(s.nextInt())) != -1) {
            avltree.insert(next);
            System.out.println("Inserted: " + next);
            System.out.println("Tree:");
            System.out.println(avltree.toString());
        }
    }
}

class AVLTree<T extends Comparable> {
    AVLNode<T> root;
    int size;

    public AVLTree() {
        this.root = null;
    }

    public void insert(T data) {
        if (this.root == null) {
            this.root = new AVLNode<T>(data);
        }

        boolean inserted = root.insert(data);
        if (inserted) size++;
    }

    public void delete(T data) {
        root.delete(data)

        root.rebalance();
    }

    public String toString() {
        return this.root.toString("");
    }

    class AVLNode<T extends Comparable> {
        int balance;
        T data;
        AVLNode<T> left;
        AVLNode<T> right;

        AVLNode(T data) {
            this.balance = 0;
            this.data = data;
            this.left = null;
            this.right = null;
        }

        private int getBalance() {
            return getHeight(this.right) - getHeight(this.left);
        }

        private int getHeight(AVLNode<T> node) {
            if (node == null) return 0;
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }

        // left cannot be null;
        void rotateRight() {
            System.out.println(this.toString("[AVLNode]: rotateRight (before-data-swap): "));
            AVLNode<T> q = this.left;
            T temp = q.data;
            q.data = this.data;
            this.data = temp;

            System.out.println(this.toString("[AVLNode]: rotateRight (before-rotation): "));
            this.left = q.left;
            q.left = q.right;
            q.right = this.right;
            this.right = q;
            System.out.println(this.toString("[AVLNode]: rotateRight (after): "));
        }

        // right cannot be null
        // From:   p
        //          \
        //           q
        // To:
        //           q
        //          /
        //         p
        void rotateLeft() {
            System.out.println(this.toString("[AVLNode]: rotateLeft (before-data-swap): "));
            AVLNode<T> p = this.right;
            T temp = p.data;
            p.data = this.data;
            this.data = temp;
            System.out.println(this.toString("[AVLNode]: rotateLeft (before-rotation): "));

            this.right = p.right;
            p.right = p.left;
            p.left = this.left;
            this.left = p;
            System.out.println(this.toString("[AVLNode]: rotateLeft (after): "));
        }

        void rebalance() {
            if (this.getBalance() < -1) {
                if (this.left != null && this.left.getBalance() > 0) {
                    this.left.rotateLeft();
                }
                this.rotateRight();
            }
            if (this.getBalance() > 1) {
                if (this.right != null && this.right.getBalance() < 0) {
                    this.right.rotateRight();
                }
                this.rotateLeft();
            }
        }

        // Returns true if item was inserted;
        boolean insert(T data) {
            if (data.compareTo(this.data) < 0) {
                if (this.left == null) {
                    this.left = new AVLNode(data);
                } else {
                    boolean b = this.left.insert(data);
                    this.rebalance();
                    return b;
                }
            } else if (data.compareTo(this.data) > 0) {
                if (this.right == null) {
                    this.right = new AVLNode(data);
                } else {
                    boolean b = this.right.insert(data);
                    this.rebalance();
                    return b;
                }
            }
            return false;
        }

        void delete(T data) {
            if (data.equals(this.data)) {

            } else if (data.compareTo(this.data) < 0) {
                if (this.left.data.equals(data)) {
                    if (this.left.left == null) {
                        this.left = this.left.right;
                        this.rebalance();
                    } else if (this.left.right == null) {
                        this.left = this.left.left;
                        this.rebalance();
                    } else {
                        AVLNode<T> next = getSymmNext(this.left.right);

                    }
                } else {
                    this.left.delete(data);
                    this.rebalance();
                }
            } else {
                if (this.right.data.equals(data) {
                }
            }
        }

        AVLNode<T> getSymmNext(AVLNode<T> start) {
            if (start.left == null) {
                return start;
            } else if (start.left.left == null) {
                AVLNode<T> res = start.left;
                start.left = null;
                start.rebalance();
                return node;
            } else {
                AVLNode<T> res = getSymmNext(start.left);
                start.rebalance();
                return res;
            }
        }


        public String toString(String padding) {
            String res = "";
            String newpad = padding + "        ";
            if (this.right != null) {
                res += this.right.toString(newpad) + "\n";
            }
            res += padding + this.data.toString() + " (" + this.getBalance() + ")";
            if (this.left != null) {
                res += "\n" + this.left.toString(newpad);
            }
            return res;
        }
    }
}

