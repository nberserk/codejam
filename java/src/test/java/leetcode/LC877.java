package leetcode;


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LC877 {

    int isAlexWin(int[]p, int start, int end){
        if(start==end)
            return 0;
        if(dp[start][end]!=Integer.MIN_VALUE)
            return dp[start][end];

        int ret = Math.max(p[start]-isAlexWin(p, start+1,end), p[end]-isAlexWin(p, start, end-1));
        dp[start][end]=ret;
        return ret;
    }
    int[][] dp;
    public boolean stoneGame(int[] piles) {
        dp=new int[piles.length][piles.length];
        for(int i=0;i<piles.length;i++)
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        int r= isAlexWin(piles, 0,piles.length-1);
        //System.out.println(r);
        return r>0 ? true:false;
    }

    @Test
    public void test(){
        assertEquals(true, stoneGame(new int[]{5,3,4,5}));
    }


}
