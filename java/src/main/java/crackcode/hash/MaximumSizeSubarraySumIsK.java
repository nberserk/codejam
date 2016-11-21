package main.java.crackcode.hash;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/21/16.
 * from: https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 *
 *
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

 Note:
 The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

 Example 1:
 Given nums = [1, -1, 5, -2, 3], k = 3,
 return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

 Example 2:
 Given nums = [-2, -1, 2, 1], k = 1,
 return 2. (because the subarray [-1, 2] sums to 1 and is the longest)


 */
public class MaximumSizeSubarraySumIsK {

    public int maxSubArrayLen(int[] nums, int k) {
        int N = nums.length;
        if(N==0) return 0;
        int[] s = new int[N];
        s[0]=nums[0];
        for(int i=1;i<N;i++)
            s[i]=s[i-1]+nums[i];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ret=0;
        for(int i=0;i<N;i++){
            int target = s[i]-k;
            Integer pos = map.get(target);
            if(pos!=null){
                ret=Math.max(ret, i-pos);
            }
            if(!map.containsKey(s[i])) map.put(s[i], i);
        }
        return ret;
    }

    @Test
    public void test(){
        int[]a ={1, -1, 5, -2, 3};
        assertEquals( 4, maxSubArrayLen(a, 3));

        int[]b = {-2, -1, 2, 1};
        assertEquals(2, maxSubArrayLen(b, 1));
    }
}
