package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 6/23/2017.
 */
public class KInversePairsArray_629 {

    int[][] dp = new int[1001][1001];

    public int kInversePairs(int n, int k) {
        if(n<=k) return 0;
        if(k==0) return 1;
        if(k==1) return n-1;

        if(dp[n][k]!=0) return dp[n][k];

        int r = 0;
        for (int i = 1; i < n; i++) {
            r+= kInversePairs(n-i,k);
        }
        for (int i = k-1; i >= 0; i--) {
            r+=kInversePairs(n-1, i);
            r%=1000000007;
        }

        dp[n][k] = r;
        return r;
    }



    @Test
    public void test2(){

        assertEquals(1, kInversePairs(3,0));
        assertEquals(2, kInversePairs(3,1));
        assertEquals(5, kInversePairs(4, 2));
        assertEquals(9, kInversePairs(5, 2));
        assertEquals(49, kInversePairs(7, 3));
        assertEquals(1068, kInversePairs(10, 5));
//        assertEquals(5, kInversePairs(10, 2));
//        assertEquals(1068, kInversePairs(1000, 100));
    }
}
