package leetcode;

import static org.junit.Assert.assertEquals;

public class MaximumAverageSubArray2_644 {

    public double findMaxAverage(int[] nums, int k) {
        int N = nums.length;
        int[] dp = new int[N];
        int[] len = new int[N];

        dp[0] = nums[0];
        len[0] = 1;
        double max = -Double.MAX_VALUE;
        if(1==k) {
            max = dp[0];
        }
        for (int i = 1; i < N; i++) {
            if(dp[i-1] + nums[i] >= nums[i]){
                dp[i] = dp[i-1] + nums[i];
                len[i] = len[i-1] + 1;
            }else{
                len[i]=1;
                dp[i] = nums[i];
            }

            if(len[i]>=k){
                double avg = dp[i]/(double)len[i];
                max = Math.max(max, avg);

                // let's see we can remove head
                int count=1;
                int sum = dp[i];
                while(len[i]-count>=k){
                    int idxLeft = i -len[i]+count;
                    if (nums[idxLeft]<avg){
                        sum-=nums[idxLeft];
                        max = Math.max(max, sum/(double)(len[i]-count));
                    }
                    count++;
                }
            }
        }
        return max;
    }


    @org.junit.Test
    public void test(){
        assertEquals(1.8, findMaxAverage(new int[]{0,0,3,2,4}, 5), 0.001);
        assertEquals(-1.0, findMaxAverage(new int[]{-1}, 1), 0.001);
        assertEquals(12.75, findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4), 0.001);
    }
}
