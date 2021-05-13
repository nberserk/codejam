package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 6/23/2017.
 *
 *
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.

 Example 1:
 Input: [1,2,3]
 Output: 6
 Example 2:
 Input: [1,2,3,4]
 Output: 24
 Note:
 The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].


 */
public class LC628 {

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
