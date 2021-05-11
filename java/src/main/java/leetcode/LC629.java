package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 6/23/2017.
 *
 * https://discuss.leetcode.com/topic/93815/java-dp-o-nk-solution/7
 *
 *
 *
 * n/k  0   1   2   3   4
 * 3    1   2   2   1   0
 * 4    1   3   5   6   5   3   1   : symmetric
 */
public class KInversePairsArray_629 {
    public int kInversePairs(int n, int k) {

        int[][] dp = new int[1000+1][1000+1];
        int mod = (int)Math.pow(10,9)+7;

        dp[2][0] = dp[2][1]=1;

        for (int i = 3; i <= n; i++) {
            dp[i][0]=1;
            int to = Math.max(k, i*(i-1)/2);
            for (int j = 1; j <= to; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1])%mod;
                if (j>=i) {
                    dp[i][j] = (dp[i][j] + -dp[i - 1][j - i] + mod) % mod;// symmetric, see upper
                }
            }
        }

        return dp[n][k];
    }



    @Test
    public void test2(){

        assertEquals(1, kInversePairs(3,0));
        assertEquals(2, kInversePairs(3,1));
        assertEquals(1, kInversePairs(3,3));
        assertEquals(5, kInversePairs(4, 2));
        assertEquals(9, kInversePairs(5, 2));
        assertEquals(49, kInversePairs(7, 3));
        assertEquals(1068, kInversePairs(10, 5));
//        assertEquals(5, kInversePairs(10, 2));
//        assertEquals(1068, kInversePairs(1000, 100));
    }
}
