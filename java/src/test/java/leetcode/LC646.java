package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class LC646 {

    int find(int[][] pairs, int cur, int[] dp){
        int N = pairs.length;
        if(cur==N) return 0;
        if(dp[cur]!=-1) return dp[cur];

        int r = 1;

        int after = pairs[cur][1];
        for (int i = cur+1; i < N; i++) {
            if(pairs[i][0]>after){
                r = Math.max(r, 1+find(pairs, i, dp));
            }
        }

        dp[cur] =r;
        return r;

    }

    public int findLongestChain(int[][] pairs) {
        int N = pairs.length;
        if(N==0) return 0;

        Arrays.sort(pairs, (a,b) -> a[1]-b[1] );
        int[] dp = new int[N];
        Arrays.fill(dp, -1);

        return find(pairs, 0, dp);
    }


    @Test
    public void test(){
        assertEquals(2, findLongestChain(new int[][] {{1,2}, {2,3}, {3,4}}));
    }
}
