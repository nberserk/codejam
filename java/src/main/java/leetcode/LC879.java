package leetcode;


import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class _879 {

    int[] group;
    int[] profit;
    int[][][] dp;
    int mod=1000000007;
    int ways(int G, int P, int i){
        if(P<0)P=0;
        if(i==group.length){
            if(P<=0) return 1;
            else return 0;
        }
        if(dp[G][P][i]!=-1){
            //System.out.println("hit");
            return dp[G][P][i];
        }

        int ret=0;
        if(G>=group[i]){
            ret+=ways(G-group[i], P-profit[i], i+1);
            ret%=mod;
        }
        ret+=ways(G,P,i+1);
        ret%=mod;
        dp[G][P][i]=ret;
        return ret;
    }
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        this.group=group;
        this.profit=profit;
        dp=new int[G+1][P+1][this.group.length+1];
        for(int i=0;i<G+1;i++)
            for(int j=0;j<P+1;j++)
                Arrays.fill(dp[i][j], -1);
        return ways(G, P, 0);

    }

    @org.junit.Test
    public void test() {
        assertEquals(2, profitableSchemes(5,3, new int[]{2,2}, new int[]{2,3}));
        assertEquals(7, profitableSchemes(10,5, new int[]{2,3,5}, new int[]{6,7,8}));

    }
}
