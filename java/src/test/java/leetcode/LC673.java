package leetcode;

import static org.junit.Assert.assertEquals;

public class LC673 {

    public int findNumberOfLIS(int[] nums) {
        int r =1;
        int longest=1;

        int N = nums.length;
        if(N==0) return 0;
        int dp[] = new int[N];
        int c[] = new int[N];

        dp[N-1]=1;
        c[N-1]=1;
        for (int i = N-2; i >=0 ; i--) {
            dp[i]=1;
            c[i]=1;

//            if(longest==1)
//                r++;
            for (int j = i+1; j < N; j++) {
                if (nums[i]<nums[j]) {
                    int cur = 1+dp[j];
                    if(cur>dp[i]){
                        c[i]=c[j];
                        dp[i]=cur;
                    }else if (cur==dp[i])
                        c[i]+=c[j];
                }
            }
            if(dp[i]>longest){
                longest=dp[i];
                r=c[i];
            }else if (dp[i]==longest)
                r+=c[i];
        }

        return r;
    }



    @org.junit.Test
    public void test(){
        assertEquals(3, findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2}));
        assertEquals(2, findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        assertEquals(5, findNumberOfLIS(new int[]{2,2,2,2,2}));
        assertEquals(1, findNumberOfLIS(new int[]{2}));
        assertEquals(0, findNumberOfLIS(new int[]{}));
    }
}
