package leetcode;


import org.junit.Assert;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class _581 {

    public int findUnsortedSubarray(int[] nums) {
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        Stack<Integer> inc = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while(!inc.isEmpty() && nums[i]<nums[inc.peek()]){
                min=Math.min(min, inc.pop());
            }
            inc.push(i);
        }

        Stack<Integer> dec = new Stack<>();
        for (int i = nums.length-1; i >=0; i--) { // corner case : 1,3,2,2,2
            while(!dec.isEmpty() && nums[i]>nums[dec.peek()]){
                max=Math.max(max, dec.pop());
            }
            dec.push(i);
        }

        if(min==Integer.MAX_VALUE&&max==Integer.MIN_VALUE)
            return 0;
        return max-min +1;
    }


    @org.junit.Test
    public void test() {
        Assert.assertEquals(5, findUnsortedSubarray(new int[]{2, 6,4,8,10,9,15}));
        Assert.assertEquals(4, findUnsortedSubarray(new int[]{ 7,5,3,1}));
        Assert.assertEquals(4, findUnsortedSubarray(new int[]{ 1,3,2,2,2}));
    }
}
