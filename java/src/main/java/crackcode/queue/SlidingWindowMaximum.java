package crackcode.queue;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by darren on 2016. 11. 8..
 *
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 *
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        int R = N-k+1;
        if(R<=0||k==0) return new int[]{};
        int[] ret = new int[R];
        LinkedList<Integer> deque = new LinkedList<>(); // first:min, last:max

        for (int i = 0; i < N; i++) {
            int start = i-k+1;
            int end = i;

            while(!deque.isEmpty()&& deque.peekLast()<start)
                deque.removeLast();

            while(!deque.isEmpty() && nums[deque.peekFirst()] <= nums[end] )
                deque.removeFirst();
            deque.addFirst(end);

            if(start>=0)
                ret[start] = nums[deque.peekLast()];
        }
        return ret;
    }


    @Test
    public void test(){
        int[] a = {1,3,-1,-3,5,3,6,7};

        assertArrayEquals(new int[]{3,3,5,5,6,7}, maxSlidingWindow(a, 3));

    }
}
