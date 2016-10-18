package main.java.crackcode.impl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/18/16.
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<Integer>();
        int row = matrix.length;
        if(row==0) return ret;
        int col = matrix[0].length;

        int sx=0;
        int ex = col-1;
        int sy =0;
        int ey = row-1;

        while(sx<=ex && sy<=ey){
            // right
            for(int x=sx;x<=ex;x++)
                ret.add(matrix[sy][x]);

            //down
            for(int y=sy+1;y<=ey;y++)
                ret.add(matrix[y][ex]);

            // left
            if(sy<row/2) // if sy>=row/2, we've already traversed all path. each loop move 2 times for each x,y direction.
                for(int x=ex-1;x>=sx;x--)
                    ret.add(matrix[ey][x]);

            if(sx<col/2)
                for(int y=ey-1;y>sy;y--)
                    ret.add(matrix[y][sx]);
            sy++;
            sx++;
            ex--;
            ey--;
        }

        return ret;
    }

    @Test
    public void test(){
        int[][] m = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        assertEquals("[1, 2, 3, 6, 9, 8, 7, 4, 5]", spiralOrder(m).toString());

        int [][] m2 =  {{2,3,4}};
        assertEquals("[2, 3, 4]", spiralOrder(m2).toString());

        int [][] m3 =  {{2}, {3},{4}};
        assertEquals("[2, 3, 4]", spiralOrder(m2).toString());
    }

}
