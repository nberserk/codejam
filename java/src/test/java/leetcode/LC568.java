package leetcode;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LC568 {

    int N, K;
    int[][] dp;
    public int maxVacationDays(int[][] flights, int[][] days) {
        N = flights.length;
        K = days[0].length;
        dp = new int[N][K];
        for(int[] d:dp)
            Arrays.fill(d, -1);

        return flight(flights, days, 0, 0);
    }

    private int flight(int[][] flights, int[][] days, int cur, int week) {
        if(week==K) return 0;

        if(dp[cur][week]!=-1) return dp[cur][week];

        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if(cur==i || flights[cur][i]==1){
                int c = days[i][week] + flight(flights,days, i, week+1);
                ret = Math.max(ret, c);
            }
        }
        dp[cur][week] = ret;
        return ret;
    }


    @org.junit.Test
    public void test(){
        assertEquals(12, maxVacationDays(new int[][]{{0,1,1},{1,0,1},{1,1,0}}, new int[][]{{1,3,1},{6,0,3},{3,3,3}}));
        assertEquals(3, maxVacationDays(new int[][]{{0,0,0},{0,0,0},{0,0,0}}, new int[][]{{1,1,1},{7,7,7},{7,7,7}}));
        assertEquals(21, maxVacationDays(new int[][]{{0,1,1},{1,0,1},{1,1,0}}, new int[][]{{7,0,0},{0,7,0},{0,0,7}}));
    }
}
