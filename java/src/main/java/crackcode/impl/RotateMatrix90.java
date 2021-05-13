package crackcode.impl;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by darren on 11/6/16.
 *
 * https://leetcode.com/problems/rotate-image/
 *
 * You are given an n x n 2D matrix representing an image.

 Rotate the image by 90 degrees (clockwise).

 Follow up:
 Could you do this in-place?

 */
public class RotateMatrix90 {
    void swap(int[][]m, int x, int y, int dx, int dy){
        int temp=m[y][x];
        m[y][x]=m[dy][dx];
        m[dy][dx]=temp;
    }
    public void rotate(int[][] matrix) {
        int N=matrix.length;
        if(N==0) return;

        int half=N/2;
        for(int sy=0;sy<half;sy++){
            int sx=sy;
            int ey=N-1-sy;
            int ex=ey;

            for(int x=sx;x<ex;x++){
                int d=x-sx;
                swap(matrix, x,sy,ex,sy+d);
                swap(matrix, x,sy,ex-d,ey);
                swap(matrix, x,sy,sx,ey-d);
            }
        }
    }

    @Test
    public void test(){
        int[][] a= {{1,2,3}, {4,5,6}, {7,8,9}};

        int[][] sol={{7,4,1}, {8,5,2}, {9,6,3}};

        rotate(a);
        assertArrayEquals(sol[0], a[0]);
        assertArrayEquals(sol[1], a[1]);
        assertArrayEquals(sol[2], a[2]);
    }
}
