package leetcode;

import static org.junit.Assert.assertEquals;

public class LC198 {

    public int rob(int[] nums) {
        int N = nums.length;
        if(N==0) return 0;

        int[][] dp = new int[N][2];//0:prev used, 1:not used
        for(int i=N-1;i>=0;i--){
            dp[i][0]= i+1<N ? dp[i+1][1]:0;
            dp[i][1]= Math.max(nums[i] + (i+1<N? dp[i+1][0]:0), i+1<N ? dp[i+1][1]:0 );
        }
        return dp[0][1];
    }

    @org.junit.Test
    public void test(){
        assertEquals(6, rob(new int[]{2,3,4}));
    }
}
