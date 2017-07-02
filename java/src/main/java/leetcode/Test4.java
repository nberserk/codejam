package leetcode;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**

 *
 */
public class Test4 {
    static class Node{
        int row,k;
        int v;
        Node(int row, int k, int v){
            this.row=row;
            this.k=k;
            this.v=v;
        }
    }
    public int[] smallestRange(int[][] nums) {
        int N = nums.length;
        PriorityQueue<Node> minpq = new PriorityQueue<>(100, (a,b) -> a.v-b.v);
        PriorityQueue<Node> maxpq = new PriorityQueue<>(100, (a,b) -> b.v-a.v);

        for (int i = 0; i < N; i++) {
            Node node = new Node(i, 0, nums[i][0]);
            minpq.add(node);
            maxpq.add(node);
        }

        int min = Integer.MAX_VALUE;
        int[] re = new int[2];
        while(minpq.size()>0){
            Node cur = minpq.poll();
            int v = maxpq.peek().v - cur.v;
            if(v<min){
                re[0]=cur.v;
                re[1]=maxpq.peek().v;
            }
            //Node next = new Node();
        }


        return re;
    }


    @org.junit.Test
    public void test(){
        int[][] n = {{4,10,15,24,26}, {0,9,12,20}, {5,18,22,30}};
        assertEquals("", smallestRange(n).toString());

//        assertEquals(true, judgeSquareSum(5));
//        assertEquals(false, judgeSquareSum(3));


    }
}
