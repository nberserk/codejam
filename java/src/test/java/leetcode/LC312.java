package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC312 {

    public int maxCoins(int[] nums) {
        int N = nums.length;

        int[] n2 = new int[N+2];
        for (int i = 0; i < N; i++) {
            n2[i+1] = nums[i];
        }
        N+=2;
        n2[0]=n2[N-1]=1;

        int[][] dp = new int [N][N];


        // B(s,e) = n[last]*n[s]*n[e] + B(s,last) + B(last,e),   where s<last<e
        // think about which one will be last element?
        // but if you think with first element to pick, recurrence equation is difficult.

        for (int len = 2; len < N; len++) {
            for (int start = 0; start < N-len ; start++) {
                int end = start+len;
                if(end>=N) break;

                int max = 0;
                for (int last = start+1; last <= end-1; last++) {
                    int cur = n2[start]*n2[end]*n2[last];
                    cur += dp[start][last] + dp[last][end];
                    max = Math.max(max, cur);
                }
                dp[start][end] = max;
            }
        }

        return dp[0][N-1];
    }


    @Test
    public void test(){

        assertEquals(167, maxCoins(new int[]{3, 1, 5, 8}));
    }
}
