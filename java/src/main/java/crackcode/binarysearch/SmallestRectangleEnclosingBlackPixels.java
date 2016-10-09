package main.java.crackcode.binarysearch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/9/16.
 *
 * https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
 *
 * idea naive:
 *      - by flood fill, we can solave this by O(N^2)
 *
 * idea binary search: *
 *      - we can binary search each column, row of starting, ending point. becuase they have only one black region.
 *
 */
public class SmallestRectangleEnclosingBlackPixels {


    public int minArea(char[][] image, int x, int y) {
        int R = image.length;
        if(R==0) return 0;
        int C = image[0].length;

        int left = searchColumn(0, x, 0, R, image);
        int right = searchColumnEnd(x, C-1, 0, R, image);
        int top = searchRow(0, y, 0, C, image);
        int bottom=searchRowEnd(y, R-1, 0, C, image);

        return (right-left+1)*(bottom-top+1);
    }

    private int searchRow(int i, int j, int left, int right, char[][] image) {
        while(i<j){
            int m = i+(j-i)/2;
            int k = left;
            while(k<right&&image[m][k]=='0')k++;
            if(k==right)
                i=m+1;
            else
                j=m;
        }
        return i;
    }

    private int searchRowEnd(int i, int j, int left, int right, char[][] image) {
        while(i<j){
            int m = i+(j-i+1)/2;
            int k = left;
            while(k<right&&image[m][k]=='0')k++;
            if(k==right)
                j=m-1;
            else
                i=m;
        }
        return i;
    }

    private int searchColumn(int i, int j, int top, int bottom, char[][] image) {
        while(i<j){
            int m = i +(j-i)/2;
            int k=top;
            while(k<bottom && image[k][m]=='0') k++;
            if(k==bottom)
                i=m+1;
            else
                j=m;
        }
        return i;
    }

    private int searchColumnEnd(int i, int j, int top, int bottom, char[][] image) {
        while(i<j){
            int m = i +(j-i+1)/2;
            int k=top;
            while(k<bottom && image[k][m]=='0') k++;
            if(k==bottom)
                j=m-1;
            else
                i=m;
        }
        return i;
    }


    @Test
    public void test(){
        char[][] mat = {"0010".toCharArray(),
                        "0110".toCharArray(),
                        "0100".toCharArray()};

        assertEquals(1, searchColumn(0,4,0,3,mat));
        assertEquals(2, searchColumnEnd(0, 4, 0, 3, mat));
        assertEquals(6, minArea(mat, 2,0));


        char[][] m2 = {
                "00110".toCharArray(),
                "01110".toCharArray(),
                "01011".toCharArray(),
                "01010".toCharArray(),
                "01010".toCharArray()};

        assertEquals(20, minArea(m2, 1,4));
    }
}
