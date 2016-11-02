package main.java.crackcode.binarysearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * tags: priority queue, binary search,
 */
public class KthSmallestElementInMatrix {

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

    // NlogN
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

    static class Node2{
        int row, v;
        Node2(int r, int v){
            row=r;
            this.v=v;
        }
    }

    // basically same with kth_pq.
    // but each
    int kthSmallest(int[][]a, int k){
        int row = a.length;
        if(row==0) return -1;
        int col = a[0].length;
        int[] lastCol = new int[row];
        Arrays.fill(lastCol, -1);
        lastCol[0]=0;
        PriorityQueue<Node2> pq = new PriorityQueue<>(100, new Comparator<Node2>() {
            @Override
            public int compare(Node2 o1, Node2 o2) {
                return o1.v-o2.v;
            }
        });
        pq.add(new Node2(0,a[0][0]));
        while(k>1){
            Node2 cur = pq.poll();
            int r = cur.row;
            int c = lastCol[r];
            // to right
            if(lastCol[r]<col-1){
                lastCol[r]++;
                pq.add(new Node2(r, a[r][c+1]));
            }
            // down
            if(r < row-1 && c==0 && lastCol[r+1]==-1){ // each row must start with 0 column.
                lastCol[r+1]++;
                pq.add(new Node2(r+1,a[r+1][c]));
            }
            k--;
        }

        return pq.poll().v;
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

    // NlogN
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

            //System.out.println("m="+m +", count="+count+",lo="+lo+",hi="+hi);
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
        assertEquals(4, upper_bound(a, 3));
        assertEquals(2,upper_bound(a, 1));

        assertEquals(13,kth_binarysearch(m2, 8));
    }

    int[][] createSortedMatrix(){
        int size = 100;
        int[][] a = new int[size][size];
        for(int y=0;y<size;y++){
            for (int x = 0; x < size; x++) {
                a[y][x] = y*1000+x+1;
            }
        }
        return a;
    }

    @Test
    public void test_bs(){
        int[][] m = createSortedMatrix();

        assertEquals(99099, kth_binarysearch(m, 9999));
        assertEquals(99098, kth_binarysearch(m, 9998));
        assertEquals(98099, kth_binarysearch(m, 9899));
    }

    @Test
    public void test_pq(){
        int[][] m = createSortedMatrix();

        assertEquals(99099, kth_pq(m, 9999));
        assertEquals(99098, kth_pq(m, 9998));
        assertEquals(98099, kth_pq(m, 9899));
    }

    @Test
    public void test_pq2(){
        int[][] m = createSortedMatrix();

        assertEquals(99099, kthSmallest(m, 9999));
        assertEquals(99098, kthSmallest(m, 9998));
        assertEquals(98099, kthSmallest(m, 9899));
    }
}
