package leetcode;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class LC862 {

    public int shortestSubarray(int[] A, int K) {
        long[] sum = new long[A.length+1];
        for (int i = 0; i < A.length; i++) {
            sum[i+1]=sum[i]+(long)A[i];
        }

        int ret = Integer.MAX_VALUE;
        Deque<Integer> queue = new LinkedList<>();// maintain sorted state

        for (int i = 0; i < sum.length; i++) {

            while(queue.isEmpty()==false && sum[i]<=sum[queue.peekLast()]){
                queue.pollLast(); // can't be min
            }

            while(queue.isEmpty()==false && sum[i]-sum[queue.peekFirst()]>=K){
                ret = Math.min(ret, i-queue.peekFirst());
                queue.pollFirst(); // poll because this is min len
            }
            queue.addLast(i);
        }

        if (ret==Integer.MAX_VALUE) ret =-1;
        return ret;
    }

    @org.junit.Test
    public void test(){
        assertEquals(9, shortestSubarray(new int[]{31,63,-38,43,65,74,90,-23,45,22}, 341));
        assertEquals(1, shortestSubarray(new int[]{45,95,97,-34,-42}, 21));

        assertEquals(3, shortestSubarray(new int[]{84,-37,32,40,95}, 167));
        assertEquals(1, shortestSubarray(new int[]{77,19,35,10,-14}, 19));
        assertEquals(3, shortestSubarray(new int[]{2,-1,2}, 3));
        assertEquals(-1, shortestSubarray(new int[]{1,2}, 4));
        assertEquals(1, shortestSubarray(new int[]{1}, 1));
    }
}
