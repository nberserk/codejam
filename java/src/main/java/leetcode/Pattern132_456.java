package leetcode;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class Pattern132_456 {


    // s1 < s3 < s2
    public boolean find132pattern(int[] nums) {
        int s3 = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i=nums.length-1 ; i>=0;i--){
            int n = nums[i];
            if(n < s3) return true;

            while( stack.size()>0 && n > stack.peek() ){
                s3 = stack.pop();
            }
            stack.push(n);
        }

        return false;
    }

    @Test
    public void test(){
        assertEquals(true, find132pattern(new int[]{3, 1, 4, 2}));
    }
}
