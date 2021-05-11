package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 *
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

 We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

 Example 1:
 Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 Output: [20,24]
 Explanation:
 List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 List 2: [0, 9, 12, 20], 20 is in range [20,24].
 List 3: [5, 18, 22, 30], 22 is in range [20,24].
 Note:
 The given list may contain duplicates, so ascending order means >= here.
 1 <= k <= 3500
 -10^5 <= value of elements <= 10^5.



 *
 */
public class SmallestRange_632 {
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

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            Node node = new Node(i, 0, nums[i][0]);
            minpq.add(node);
            max = Math.max(max, nums[i][0]);
        }

        int min = Integer.MAX_VALUE;
        int[] re = new int[2];
        while(minpq.size()>0){
            Node cur = minpq.poll();
            int v = max - cur.v;
            if(v<min){
                re[0]=cur.v;
                re[1]=max;
                min=v;
            }
            minpq.remove(cur);
            if(cur.k>=nums[cur.row].length-1) break;

            Node next = new Node(cur.row, cur.k+1, nums[cur.row][cur.k+1]);
            minpq.add(next);
            max=Math.max(max, nums[cur.row][cur.k+1]);
        }


        return re;
    }


    @org.junit.Test
    public void test(){
        int[][] n = {{4,10,15,24,26}, {0,9,12,20}, {5,18,22,30}};
        assertEquals("[20, 24]", Arrays.toString(smallestRange(n)));
    }
}
