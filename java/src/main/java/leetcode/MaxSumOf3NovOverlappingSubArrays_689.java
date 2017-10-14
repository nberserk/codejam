package leetcode;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class MaxSumOf3NovOverlappingSubArrays_689 {

    int getSum(int[] sum, int start, int end){
        if(start==0) return sum[end];
        return sum[end]-sum[start-1];
    }

    // dp[n][i] = max sum of n contiguous subarray from ith in the array
    // our solution is find dp[3][0]
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int N = nums.length;
        int[][] dp = new int[4][N+2];
        int [][] pos = new int[4][N+2];

        int[] sum = new int[N+1];
        int s=0;
        for (int i = 0; i < N; i++) {
            s+=nums[i];
            sum[i]=s;
        }

        for (int n = 1; n <= 3; n++) {
            int max =0;
            for (int i = N-k+1; i >=0; i--) {
                int cur = getSum(sum,i,i+k-1) + dp[n-1][i+k];
                if(cur >=max){
                    dp[n][i] = cur;
                    pos[n][i] = i;
                    max=cur;
                }else{
                    dp[n][i] = dp[n][i+1];
                    pos[n][i] = pos[n][i+1];
                }
            }
        }

        int[] r = new int[3];
        int ri=0;
        int n=3;
        int i=0;
        while(ri<3){
            r[ri++] = pos[n][i];
            i=pos[n][i] +k;
            n--;
        }

        Arrays.sort(r);
        return r;
    }



    @org.junit.Test
    public void test(){
        assertArrayEquals(new int[]{0, 3, 5}, maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2));
        assertArrayEquals(new int[]{4, 5, 6}, maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 1));
    }
}
