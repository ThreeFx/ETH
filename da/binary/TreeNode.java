import java.util.Scanner;

class TreeNode {
	public TreeNode left, right, parent;
	public int key, size;
	
	// Simple constructor
	public TreeNode(int newKey, TreeNode newParent) {
		key = newKey;
		left = null;
		right = null;
		parent = newParent;
        size = 1;
	}
	
	// Print to System.out in a bracket-notation like "(1 (2)) 3 (4)" recursively
	// No newline printed. Note that building and returning a String would be cleaner
	// but much slower if not done carefully.
	public void print() {
        //if (!validateSize(this)) throw new RuntimeException("Bad size. see log for details");
		if (left != null) {
			System.out.print("(");
			left.print();
			System.out.print(") ");
		}
		System.out.print(key);
		//System.out.print("k: " + key + " s: " + size);
		if (right != null) {
			System.out.print(" (");
			right.print();
			System.out.print(")");
		}
	}
	
	/*** Methods to implement for 1 point */

	// Insert element "key" into the tree
	// You may assume that it is not there yet
	public void insert(int val) {
        if (val < this.key) {
            if (this.left != null) {
                this.left.insert(val);
            } else {
                this.left = new TreeNode(val, this);
            }
        } else if (val > this.key) {
            if (this.right != null) {
                this.right.insert(val);
            } else {
                this.right = new TreeNode(val, this);
            }
        } else {
            return;
        }
        size++;
	}
	
	// Return if "key" is in the tree, recursively
	public boolean contains(int val) {
        if (val < this.key && left != null) {
            return left.contains(val);
        } else if (val > this.key && right != null) {
            return right.contains(val);
        } else {
            return this.key == val;
        }
	}

	// Delete "val" from the tree
	// You may assume that the tree contains "val"
	public void delete(int val) {
        //System.out.println("val: " + val + " this.key: " + this.key);
        if (this.key == val) {
            if (this == this.parent.left) { // We are left of the parent
                if (this.left == null) { // our left child is null
                    this.parent.left = this.right; // update the parents left subtree to the right child
                    if (this.right != null) { // update the parent of our right child of needed
                        this.right.parent = this.parent;
                    }
                } else if (this.right == null) { // our right child is null
                    this.parent.left = this.left; // update parents left subtree to be our left
                    this.left.parent = this.parent; // left != null => we have to set its parent
                } else { // both are != null
                    // Get the symmetric next
                    TreeNode symmNext = this.right;
                    while (symmNext.left != null) {
                        // update the size as we go
                        symmNext.size--;
                        symmNext = symmNext.left;
                    }
                   
                    // if the symmNext had no left child
                    if (symmNext == this.right) {
                        
                        symmNext.size += this.left.size;

                        this.parent.left = symmNext;
                        symmNext.parent = this.parent;
                        symmNext.left = this.left;
                        this.left.parent = symmNext;
                    } else { // we found the smallest key
                        // put it in our node
                        this.key = symmNext.key;
                        this.size--;
                        symmNext.parent.left = symmNext.right;
                        if (symmNext.right != null) {
                            symmNext.right.parent = symmNext.parent;
                        }
                    }
                }
            } else if (this == this.parent.right) { // we are the right subtree of the parent
                if (this.left == null) { // our left child is null
                    this.parent.right = this.right; // update the parents right subtree to the right child
                    if (this.right != null) { // update the parent of our right child of needed
                        this.right.parent = this.parent;
                    }
                } else if (this.right == null) { // our right child is null
                    this.parent.right = this.left; // update parents right subtree to be our left
                    this.left.parent = this.parent; // left != null => we have to set its parent
                } else {
                    // Get the symmetric next
                    TreeNode symmNext = this.right;
                    while (symmNext.left != null) {
                        // update the size as we go
                        symmNext.size--;
                        symmNext = symmNext.left;
                    }
                   
                    // if the symmNext had no left child
                    if (symmNext == this.right) {
                        
                        symmNext.size += this.left.size;

                        this.parent.right = symmNext;
                        symmNext.parent = this.parent;
                        symmNext.left = this.left;
                        this.left.parent = symmNext;
                    } else { // we found the smallest key
                        // put it in our node
                        this.size--;
                        this.key = symmNext.key;
                        symmNext.parent.left = symmNext.right;
                        if (symmNext.right != null) {
                            symmNext.right.parent = symmNext.parent;
                        }
                    }
                }
            }
        } else if (val < this.key) {
            this.size--;
            this.left.delete(val);
        } else { // val > this.key
            this.size--;
            this.right.delete(val);
        }
	}

    static int computeSize(TreeNode node) {
        if (node == null) return 0;
        return 1 + computeSize(node.left) + computeSize(node.right);
    }

    static boolean validateSize(TreeNode node) {
        if (node != null) {
            boolean b = true;
            if (computeSize(node) != node.size) {
                System.out.println("[WARNING]: Wrong size for subtree of " + node.key + "\nExpected size: " + computeSize(node) + " Actual size: " + node.size);
                b = false;
            }
            return b && validateSize(node.left) && validateSize(node.right);
        }
        return true;
    }

	/*** Methods to implement for another 1 point */
	
	// Find "rank"-th smallest element in the tree, counting from 0
	public int findByRank(int rank) {
        return this.findByRankHelper(rank + 1);
	}

    private int findByRankHelper(int rank) {
        //if (rank > this.size) throw new RuntimeException("Cannot find " + rank + (this.size == 1 ? "st" : this.size == 2 ? "nd" : this.size == 3 ? "rd" : "th") + " element in a tree of size " + size);
        //if (!validateSize(this)) throw new RuntimeException("Sizes not accurate!");
        int left = this.left != null ? this.left.size : 0;
        int middle = left + 1;
        if (middle == rank) {
            return this.key;
        } else if (rank < middle) {
            return this.left.findByRankHelper(rank);
        } else if (rank <= this.size){ // rank > middle && < size
            return this.right.findByRankHelper(rank - middle);
        } else {
            throw new RuntimeException("aaaaaaaa");
        }
    }
}


/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main {
	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);
		boolean run = true;
		
		// We use a "special" smallest value node as a root.
		// Always having at least one node of the tree simplifies a lot of special cases. 
		TreeNode root = new TreeNode(-1, null);

		// A simple loop reading one command at a time
		while (run) {
			String cmd = scanner.next();
			
			if (cmd.equals("I")) {
				int val = scanner.nextInt();
				root.insert(val);
			}
			
			if (cmd.equals("D")) {
				int val = scanner.nextInt();
				root.delete(val);
			}
			
			if (cmd.equals("C")) {
				int val = scanner.nextInt();
				if (root.contains(val)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
			
			if (cmd.equals("P")) {
				// We are not printing the special "-1" node
				if (root.right == null) {
					System.out.println("EMPTY");
				} else {
					root.right.print();
					System.out.println();
				}
			}
			
			if (cmd.equals("R")) {
				int rank = scanner.nextInt();
				// We look for element number "rank+1" to skip the special "-1" value
				System.out.println(root.findByRank(rank + 1));
			}

			if (cmd.equals("X")) {
				run = false;
			}
		}
		scanner.close();
	}
}
