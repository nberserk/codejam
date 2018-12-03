package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC935 {

    public int knightDialer(int N) {
        long[][] dp = new long[N][5];
        dp[0][0]= dp[0][1]= dp[0][2]= dp[0][3]=dp[0][4]=1;
        int mod = 1000000007;

        for (int i = 1; i < N; i++) {
            dp[i][0] = (dp[i-1][2] + dp[i-1][1])%mod;
            dp[i][1] = (2*dp[i-1][0]+dp[i-1][4])%mod;
            dp[i][2] = (2*dp[i-1][0])%mod;
            dp[i][3] = 0;
            dp[i][4] = (2*dp[i-1][1])%mod;
        }

        long ret = (4*dp[N-1][0]+2*dp[N-1][1]+2*dp[N-1][2]+dp[N-1][3]+dp[N-1][4])%mod;
        return (int)ret;
    }

    @Test
    public void test(){
        assertEquals(10, knightDialer(1));
        assertEquals(20, knightDialer(2));
        assertEquals(46, knightDialer(3));
        assertEquals(                719179703                , knightDialer(40));
    }
}
