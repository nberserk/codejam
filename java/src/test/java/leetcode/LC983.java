package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LC983 {
    int[] dp;

    int min(int[] days, int[] costs, int start){
        int N= days.length;
        if(start>=N) return 0;

        if(dp[start]!=-1) return dp[start];

        int min=Integer.MAX_VALUE;
        // 1 pass
        int cur=costs[0] + min(days,costs,start+1);
        min=Math.min(cur,min);
        // 7 pass
        int validUntil=days[start]+7-1;
        int next=start+1;
        while(next<N&& days[next]<=validUntil)
            next++;
        int cur2=costs[1]+min(days,costs,next);
        min=Math.min(cur,min);
        // 30 pass
        int validUntil2 =days[start]+30-1;
        int next2=start+1;
        while(next2<N&& days[next2]<=validUntil2)
            next2++;
        int cur3=costs[2]+min(days,costs,next2);
        min= Math.min(cur3, Math.min(cur,cur2));
        System.out.println(String.format("start %d=%d", start,min));
        dp[start] = min;
        return min;
    }
    public int mincostTickets(int[] days, int[] costs) {
        dp = new int[days.length];
        Arrays.fill(dp, -1);
        return min(days,costs, 0);
    }


    @Test
    public void test(){
        Assert.assertEquals(17 , mincostTickets(new int[]{1,2,3,4,5,6,7,8,9,10,30,31}, new int[]{2,7,15}));



    }
}
