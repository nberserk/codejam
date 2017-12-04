package leetcode;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CherryPick_741 {


    int maxCherry(int[][] grid, Set<String> used, int dir, int[][][] dp, int x, int y){
        int N = grid.length;

        if(dp[dir][y][x]!=-1)
            return dp[dir][y][x];

        if(y==N-1&&x==N-1)
            return maxCherry(grid, used)


        int ret = 0;


        dp[dir][y][x]=ret;
    }

    int BLOCKED =-100;
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        int[][][] dp = new int[2][N][N];
        int cur = maxCherry(grid, true, );
        if(cur<0) return 0;
        return cur;
    }



    @org.junit.Test
    public void test(){
        assertEquals(15, cherryPickup(new int[][]{{1,1,1,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,0,0,1},{1,0,0,1,0,0,1},{0,0,0,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,1,1,1}}));

        assertEquals(0, cherryPickup(new int[][]{{1,1,-1},{1,-1,1}, {-1,1,1}}));
        assertEquals(5, cherryPickup(new int[][]{{0,1,-1},{1,0,-1}, {1,1,1}}));
                assertEquals(1, cherryPickup(new int[][]{{1}}));


    }
}
