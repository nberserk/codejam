package main.java.crackcode.tree;

/**
 * Created by darren on 12/27/15.
 */
public class BSTNode {
    public int key;    
    public BSTNode left, right;

    public BSTNode(int v) {
        this.key = v;
    }

    public void add(BSTNode n) {
        if (n.key > key) {
            if (right != null) {
                right.add(n);
            } else
                right = n;
        } else {
            if (left != null) {
                left.add(n);
            } else
                left = n;
        }
    }

    @Override
    public String toString() {
        return String.format("%d (%d,%d)", key, left == null ? -1
                : left.key, right == null ? -1 : right.key);
    }

    void print() {
        System.out.println(String.format("%d (%d,%d)", key,
                left == null ? -1 : left.key, right == null ? -1
                        : right.key));
    }
}
