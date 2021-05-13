package leetcode;

import java.util.Stack;

import static org.junit.Assert.assertArrayEquals;


public class LC503 {

    public int[] nextGreaterElements(int[] nums) {
        int N = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] r = new int[N];
        for (int i = 2*N-1; i >=0; i--) {
            int ai = i%N;
            while(stack.size()>0 && nums[ai]>=stack.peek())
                stack.pop();
            r[ai]= stack.size()>0 ? stack.peek():-1;
            stack.push(nums[ai]);
        }
        return r;
    }

    @org.junit.Test
    public void test(){
        assertArrayEquals(new int[]{2,-1,2}, nextGreaterElements(new int[]{1,2,1}));
    }
}
