package main.java.crackcode.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/12/16.
 */
public class Search2DMatrix2 {
    int bsearch(int[] a, int s, int e, int target){
        int l=s;
        int h=e;
        while(l<h){
            int m = l+(h-l+1)/2;
            if(a[m]>target) h=m-1;
            else l=m;
        }
        return l;
    }
    // O(Rlog(C))
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row==0) return false;
        int col = matrix[0].length;

        int r = 0;
        int c =0;
        while(r<row){
            c = bsearch(matrix[r], 0, col-1, target);
            if(matrix[r][c]==target ) return true;
            r++;
        }

        return false;
    }

    public boolean searchMatrix_linear(int[][] matrix, int target) {
        int row = matrix.length;
        if(row==0) return false;
        int col = matrix[0].length;

        int r = 0;
        int c =col-1;
        while(r<row&&c>=0){
            if(matrix[r][c]==target ) return true;
            else if (matrix[r][c]>target) c--;
            else if (matrix[r][c]<target) r++;
        }
        return false;
    }

    @Test
    public void test(){
        int[][] a = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};

        assertEquals(true, searchMatrix(a, 5));
        assertEquals(false, searchMatrix(a, 20));

        assertEquals(true, searchMatrix_linear(a, 5));
        assertEquals(false, searchMatrix_linear(a, 20));
    }
}
