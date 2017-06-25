package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 6/23/2017.
 */
public class Test_564 {

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        int p = nums[N-1];

        int next = Math.max(nums[N-2]*nums[N-3], nums[0]*nums[1]);
        return next*p;
    }

    @Test
    public void test2(){
        assertEquals(6, maximumProduct(new int[]{1, 2, 3}));
        assertEquals(24, maximumProduct(new int[]{1, 2, 3,4}));
        assertEquals(8, maximumProduct(new int[]{-1, -2,0, 4}));
        assertEquals(0, maximumProduct(new int[]{-1, 0, 4}));


    }
}
