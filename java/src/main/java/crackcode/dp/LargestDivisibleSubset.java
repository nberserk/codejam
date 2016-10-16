package main.java.crackcode.dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/7/16.
 *
 * https://leetcode.com/problems/largest-divisible-subset/
 *
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        int[] dp = new int[N];
        int[] prev = new int[N];

        int max=0;
        int maxidx = -1;

        for(int i=N-1;i>=0;i--){
            for(int j=i+1;j<N;j++){
                if(nums[j]%nums[i]==0){
                    if(dp[j]+1>dp[i]){
                        dp[i] = dp[j] +1;
                        prev[i] = j;
                        if(dp[i]>max){
                            max = dp[i];
                            maxidx = i;
                        }
                    }
                }
            }
        }


        List<Integer> ret = new ArrayList<>();
        int i=maxidx;
        for(int j=0;j<=max;j++){
            ret.add(nums[i]);
            i=prev[i];
        }
        return ret;
    }

    @Test
    public void test(){
        int [] a = {1,3,2};
        int[] b = {1,2,4,8,9,72};
        assertEquals("[1, 2]", largestDivisibleSubset(a).toString());
        assertEquals("[1, 2, 4, 8, 72]", largestDivisibleSubset(b).toString());
    }

}
