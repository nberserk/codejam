package main.java.crackcode.dp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/14/16.
 *
 * http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
 *
 *
 *
 */
public class Kadane2D {

    // O(N^3)
    int kadane2d(int[][] m){
        int row = m.length;
        if(row==0)return 0;
        int col = m[0].length;

        int ret = Integer.MIN_VALUE;
        for (int left = 0; left < col; left++) {
            int[] sum = new int[row];
            for (int right = left; right < col; right++) {
                for (int i = 0; i < row; i++) {
                    sum[i] += m[i][right];
                }

                int curMax=sum[0];
                int max=sum[0];
                for (int i = 1; i < row; i++) {
                    curMax = Math.max(curMax+sum[i], sum[i]);
                    max = Math.max(curMax, max);
                }
                ret=Math.max(ret, max);
            }
        }

        return ret;
    }

    @Test
    public void test(){
        int[][] m = {{1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };

        assertEquals(29, kadane2d(m));
    }
}
