package main.java.crackcode.binarysearch;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * keyword: priority queue, binary search
 */
public class KthSmallestElementInMatrix extends TestCase {

    static class Node{
        int v,x,y;
        public Node(int x, int y, int v){
            this.x=x;
            this.y=y;
            this.v=v;
        }

        @Override
        public String toString() {
            return v + " ";
        }
    }


    int kth_pq(int[][] a, int k){
        PriorityQueue<Node> pq = new PriorityQueue<>(100, new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2) {
                return o1.v-o2.v;
            }
        });

        int row = a.length;
        int col = a[0].length;
        for(int i=0;i<col;i++){
            pq.add(new Node(i, 0, a[0][i]));
        }

        int count =1;
        while(pq.size()>0){
            Node node = pq.poll();
            if(count==k)
                return node.v;
            count++;
            if(node.y<row-1)
                pq.add(new Node(node.x, node.y+1, a[node.y+1][node.x]));
        }

        return 0;
    }

    int lower_bound(int[] a, int key){
        int lo=0;
        int hi=a.length-1;
        while(lo<hi){
            int m = (lo+hi)/2;
            if(a[m]>=key)
                hi=m;
            else
                lo=m+1;
        }
        return lo;
    }

    int upper_bound(int[] a, int key){
        int N = a.length;
        int lo = 0;
        int hi = N;
        while(lo<hi){
            int m = (lo+hi) >>1;
            if(a[m] >key)
                hi=m;
            else
                lo=m+1;
        }
        return lo;
    }

    int kth_binarysearch(int[][] a, int k){
        int N = a.length;
        int lo = a[0][0];
        int hi = a[N-1][N-1];

        while(lo<hi){
            int m = (lo+hi)>>1;
            int count =0;
            for(int i=0;i<N;i++){
                count += upper_bound(a[i], m);
            }

            System.out.println("m="+m +", count="+count+",lo="+lo+",hi="+hi);
            if (count < k)
                lo=m+1;
            else
                hi=m;// 같은 번호가 여러개 있을수 있으니까 m-1을 하지 못함..

        }
        return lo;
    }


    @Test
    public void test(){
        int[][] m = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {25, 29, 37, 48},
            {32, 33, 39, 50},
        };

        int[][] m2 = {
                {1,5,9},
                {10,11,13},
                {12,13,15}};

        assertEquals(30, kth_pq(m, 7));
        assertEquals(10, kth_pq(m, 1));
        assertEquals(33, kth_pq(m, 9));

        int[] a = {1,1,3,3,5,5};
        assertEquals(2, lower_bound(a, 3));
        assertEquals(4,lower_bound(a, 5));
        assertEquals(6,upper_bound(a, 5));
        assertEquals(4,upper_bound(a, 3));
        assertEquals(2,upper_bound(a, 1));


        assertEquals(13,kth_binarysearch(m2, 8));

        assertEquals(30, kth_binarysearch(m, 7));
        assertEquals(10, kth_binarysearch(m, 1));
        assertEquals(33, kth_binarysearch(m, 9));


    }
}
