package main.java.crackcode.tree;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by darren on 9/17/16.
 */
public class Test {
    //static const int dd=1111;
    final int INF = 2147483647;

    static class Node{
        int v;
        Node left, right;
        public Node(int v){
            this.v =v;
        }
    }

    Node parse(int[] a, Integer i){
        int v = a[i++];
        if(v==-1)            return null;

        Node n = new Node(v);
        i++;
//        n.left = parse(a, i);
//        System.out.println(i);
//        n.right = parse(a, i);
        return n;
    }

    public static void main(String[] args) {
        int[]a = {5,2,1,-1,-1,-1,-1};

        double t = 10.0/3;



        System.out.println(t);

        int[][] b = {{1,1}, {-1,1}};
        Arrays.sort(b, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return 0;
            }
        });


    }
}
