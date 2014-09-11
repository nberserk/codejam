package crackcode;

import java.util.HashSet;
import java.util.Set;

public class CheckBalancedTree {

    static class Node {
        int v;
        Node left, right;

        Node(int v) {
            this.v = v;
        }

        void add(Node n) {
            if (v < n.v) {
                if (right == null) {
                    right = n;
                } else {
                    right.add(n);
                }
            } else {
                if (left == null) {
                    left = n;
                } else {
                    left.add(n);
                }
            }
        }

        void print() {
            System.out.println(String.format("%d :(%d, %d)", v, left != null ? left.v : -1, right != null ? right.v : -1));
            if (left != null) {
                left.print();
            }
            if (right != null) {
                right.print();
            }
        }

        boolean isBalanced(Node n){
            HashSet<Integer> set = new HashSet<Integer>();
            traverse(set, n, 1);

            Integer[] set2 = set.toArray(new Integer[set.size()]);
            int min = set2[0];
            int max= min;
            for (int i = 1; i < set2.length; i++) {
                min = Math.min(min, set2[i]);
                max = Math.max(max, set2[i]);
                if(max-min>1){
                    System.out.println("not balanced");
                    return false;
                }
            }

            System.out.println("balanced");
            return true;
        }

        void traverse(Set<Integer> set, Node n, int depth){
            if(n.left==null && n.right==null){
                set.add(depth);
                return;
            }

            if(n.left!=null){
                traverse(set, n.left, depth+1);
            }
            if(n.right!=null){
                traverse(set, n.right, depth+1);
            }
        }

        public static int maxDepth(Node n){
            if(n==null){
                return 0;
            }
            return 1 + Math.max( maxDepth(n.left), maxDepth(n.right));
        }
    }

    public static void main(String[] args) {
        Node root = new Node(100);
        root.add(new Node(5));
        root.add(new Node(220));

        root.print();
        int max = Node.maxDepth(root);
        System.out.println("max depth :"+max);
        root.isBalanced(root);

        root.add(new Node(300));
        root.add(new Node(400));
        root.print();
        max = Node.maxDepth(root);
        System.out.println("max depth :"+max);
        root.isBalanced(root);



    }

}
