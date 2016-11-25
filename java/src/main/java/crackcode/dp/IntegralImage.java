package main.java.crackcode.dp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/25/16.
 *
 *
 * https://discuss.leetcode.com/topic/23361/given-a-matrix-a-write-a-matrix-m-for-which-every-element-i-j-is-the-sum-of-all-elements-of-a-left-and-above-a-i-j
 *
 */
public class IntegralImage {

    void integralImage(int[][] m){
        int row = m.length;
        int col = m[0].length;

        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                if(x==0 && y==0) continue;
                else if(x==0){
                    m[y][x] += m[y-1][x];
                }else if(y==0)
                    m[y][x] += m[y][x-1];
                else{
                    m[y][x] += m[y-1][x] + m[y][x-1] - m[y-1][x-1];
                }
            }
        }
    }

    @Test
    public void test(){
        int[][] a = {{3, 7, 1},
                {2, 4, 0},
                {9, 4, 2}};
        integralImage(a);
        assertEquals(32, a[2][2]);
        assertEquals(17, a[1][2] );
        assertEquals(29, a[2][1] );
    }
}
