package leetcode;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class ShortedUnsortedConstinuousSubArray_581 {

    public int findUnsortedSubarray(int[] nums) {
        int N = nums.length;
        int l=Integer.MAX_VALUE, r=Integer.MIN_VALUE;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while(!stack.isEmpty() && nums[stack.peek()]>nums[i] )
                l=Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = N-1; i >=0 ; i--) {
            while(!stack.isEmpty() && nums[stack.peek()]<nums[i] )
                r=Math.max(r, stack.pop());
            stack.push(i);
        }

        if (l==Integer.MAX_VALUE || r==Integer.MIN_VALUE)
            return 0;
        else return r-l+1;
    }

    @org.junit.Test
    public void test(){
        assertEquals(5, findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        assertEquals(4, findUnsortedSubarray(new int[]{1,3,2,2,2}));



        /*
        // break label test
        int[] n = new int[100];
        n[90]=100;
        outer: for (int i = 0; i < 100; i++) {
            for (int j = i+1; j < 100; j++) {
                if(n[j]==100){
                    break outer;
                }
            }
        }
        */
    }
}
