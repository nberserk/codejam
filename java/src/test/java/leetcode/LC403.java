package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC403 {
    int[][] dp;
    boolean cross(int[] s, int i, int jump){
        if(i==s.length-1) return true; // jump=1
        if(dp[i][jump]!=-1) return dp[i][jump]==1;
        int pos = s[i]; //i=2, pos=2
        int min = pos+jump-1;//
        min=Math.max(min, pos+1);//3
        int max = pos+jump+1;//4
        for(int j=i+1;j<s.length;j++){
            if(s[j]>max) break;
            if(s[j]>=min && s[j]<=max){
                if(cross(s, j, s[j]-pos)){
                    dp[i][jump]=1;
                    return true;
                }

            }
        }
        dp[i][jump]=0;
        return false;
    }
    public boolean canCross(int[] stones) {
        dp= new int[stones.length][2000];
        for(int[] s:dp)
            Arrays.fill(s,-1);
        if(stones[1]!=1) return false;
        return cross(stones, 1,1);
    }


    @Test
    public void test(){
        assertEquals(false, canCross(new int[]{0,1,2,3,4,8,9,11}));
        assertEquals(false, canCross(new int[]{0,1,3,5,6,8,12,17}));
    }
}
