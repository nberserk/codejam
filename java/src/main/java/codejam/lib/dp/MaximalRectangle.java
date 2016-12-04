package main.java.codejam.lib.dp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 12/4/16.
 *
 * https://leetcode.com/problems/maximal-square/
 *
 *
 */
public class MaximalRectangle {

    public int maximalSquare(char[][] matrix) {
        int row=matrix.length;
        if(row==0) return 0;
        int col = matrix[0].length;

        int ret=0;
        int[][] dp = new int[row][col];
        for(int y=0;y<row;y++){
            if(matrix[y][0]=='1'){
                dp[y][0]=1;
                ret=1;
            }
        }

        for(int x=0;x<col;x++){
            if (matrix[0][x]=='1'){
                dp[0][x]=1;
                ret=1;
            }
        }

        for (int y = 1; y < row; y++) {
            for (int x = 1; x < col; x++) {
                if(matrix[y][x]=='0') continue;
                dp[y][x] = Math.min(dp[y-1][x], Math.min(dp[y-1][x-1], dp[y][x-1]) ) +1;
                if(dp[y][x]>ret)
                    ret = dp[y][x];
            }
        }

        return ret*ret; // return area
    }

    @Test
    public void test(){

        char[][] m = {
                "10100".toCharArray(),
                "10111".toCharArray(),
                "11111".toCharArray(),
                "10010".toCharArray()};
        assertEquals(4, maximalSquare(m) );

    }
}
