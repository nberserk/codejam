package crackcode;

public class CommonAncestor {

    public static class Node {
        int v;
        Node left, right;

        Node(int v) {
            this.v = v;
        }

        void add(Node n) {
            if (n.v > v) {
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

        void print() {
            System.out.println(String.format("%d (%d,%d)", v, left == null ? -1 : left.v, right == null ? -1 : right.v));
        }
    }

    static Node commonAncestor(Node parent, Node p, Node q) {
        if (isChild(parent.left, p) && isChild(parent.left, q)) {
            return commonAncestor(parent.left, p, q);
        }
        if (isChild(parent.right, p) && isChild(parent.right, q)) {
            return commonAncestor(parent.right, p, q);
        }

        return parent;
    }

    static boolean isChild(Node p, Node c){
        if (p == null || c == null) {
            return false;
        }

        if (p==c) {
            return true;
        }
        return isChild(p.left, c) || isChild(p.right, c);
    }

    public static void main(String[] args) {
        Node root = new Node(100);
        Node n1 = new Node(10);
        Node n7 = new Node(70);
        root.add(new Node(50));
        root.add(n7);
        root.add(new Node(20));
        root.add(n1);

        Node ancestor = commonAncestor(root, n1, n7);
        ancestor.print();

    }

}
