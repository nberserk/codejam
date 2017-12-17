package leetcode;

import static org.junit.Assert.assertEquals;

public class NumberOfCornerRectangles_750 {

    public int countCornerRectangles(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;

        int[][][] dp = new int[R][C][C];

        int ret =0;
        for (int y = R-1; y >=0; y--) {
            for (int sx = 0; sx < C; sx++) {
                for (int ex = sx+1; ex < C; ex++) {
                    if (grid[y][ex]==1 && grid[y][sx]==1){
                        dp[y][sx][ex] = 1;
                        if(y!=R-1 && dp[y+1][sx][ex]>0){
                            ret += dp[y+1][sx][ex];
                            dp[y][sx][ex]+=dp[y+1][sx][ex];
                        }
                    }else{
                        if(y!=R-1){
                            dp[y][sx][ex]=dp[y+1][sx][ex];
                        }
                    }
                }
            }
        }

        return ret;
    }



    @org.junit.Test
    public void test(){
        assertEquals(4, countCornerRectangles(new int[][]{
                {1,0,1},
                {0,1,1},
                {1,1,1},
                {1,0,1}
        }));

        assertEquals(9, countCornerRectangles(new int[][]{
                {1,1,1},
                {1,1,1},
                {1,1,1}
        }));


//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
