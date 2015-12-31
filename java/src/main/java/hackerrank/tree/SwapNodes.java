package hackerrank.tree;

import java.util.Scanner;

/**
 * Created by darren on 2015. 12. 16..
 */
public class SwapNodes {

    static class Node{
        int v;
        int left, right;

        public Node(int v, int l, int r){
            this.v = v;
            left =l;
            right=r;
        }
    }

    static final int MAX_NODE = 1025;
    Node nodes[] = new Node[MAX_NODE];

    public void init(int N){
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i, -1,-1);
        }
    }

    public static void main(String[] args) {
   //     test();

        SwapNodes swap = new SwapNodes();

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        swap.init(n);
        for (int i = 1; i <= n; i++) {
            swap.nodes[i].left = s.nextInt();
            swap.nodes[i].right = s.nextInt();
        }

        int t = s.nextInt();
        for (int i = 0; i < t; i++) {
            int K = s.nextInt();
            swap.swap(K);
            swap.inorder(1);
            System.out.println("");
        }
    }

    private static void test() {
        SwapNodes s = new SwapNodes();
        s.init(3);

        s.nodes[1].left = 2;
        s.nodes[1].right = 3;

        s.swap(1);
        s.inorder(1);
        s.swap(1);
        s.inorder(1);

    }

    private void swap(int k) {
        swapInternal(1, k, 1);

    }

    private void swapInternal(int i, int k, int depth) {
        if (depth%k==0){
            int t = nodes[i].left;
            nodes[i].left = nodes[i].right;
            nodes[i].right = t;
        }

        if(nodes[i].left !=-1)
            swapInternal(nodes[i].left, k, depth+1);
        if(nodes[i].right !=-1)
            swapInternal(nodes[i].right, k, depth+1);
    }

    private void inorder(int i) {
        if(nodes[i].left !=-1)
            inorder(nodes[i].left);

        System.out.print(i + " ");

        if(nodes[i].right !=-1)
            inorder(nodes[i].right);

    }


}
