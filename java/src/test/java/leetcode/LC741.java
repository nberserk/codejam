package leetcode;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LC741 {


    int maxCherry(int[][] grid, int dir, int[][][] dp, int x, int y, int cherry){
        int N = grid.length;

//        if(dp[dir][y][x]!=-1)
//            return dp[dir][y][x];

        if(dir==1 && x==0 && y==0){
            dp[dir][y][x] = Math.max(dp[dir][y][x], cherry);
            return 0;
        }

        boolean consumed=false;
        int ret=0;
        if(grid[y][x]==1){
            consumed=true;
            ret=1;
            grid[y][x]=0;
        }else if (grid[y][x]==-1)
            ret = BLOCKED;

        int org_dir=dir;
        if(dir==0 && x==N-1 && y==N-1){
            dir=1;
        }

        int child=-100;
        if (ret!=BLOCKED){
            int[][] d = new int[][]{{1,0}, {0,1}};
            int multiply = dir==0? 1:-1;
            for (int i = 0; i < 2; i++) {
                int nx = x + d[i][0]*multiply;
                int ny = y + d[i][1]*multiply;
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                child = Math.max(child, maxCherry(grid, dir, dp, nx, ny, cherry+ret));
            }
        }

        if(consumed)
            grid[y][x]=1;

        return 1;
//        dp[org_dir][y][x]=ret+child;
//        return dp[org_dir][y][x];
    }

    int BLOCKED =-100;
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        if(N==1) return grid[0][0]==1?1:0;
        int[][][] dp = new int[2][N][N];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        maxCherry(grid, 0, dp, 0,0, 0);
        int cur = dp[1][0][0];
        if( cur<0) return 0;
        return cur;
    }



    @org.junit.Test
    public void test(){
        assertEquals(16, cherryPickup(new int[][]{{1,1,1,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,0,0,1},{1,0,0,1,0,0,1},{0,0,0,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,1,1,1}}));
        assertEquals(0, cherryPickup(new int[][]{{1,1,-1},{1,-1,1}, {-1,1,1}}));
        assertEquals(5, cherryPickup(new int[][]{{0,1,-1},{1,0,-1}, {1,1,1}}));
        assertEquals(1, cherryPickup(new int[][]{{1}}));
    }
}
