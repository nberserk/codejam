package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LC1706 {
    public int[] findBall(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;

        int[] cur = new int[column];
        for(int c=0;c<column;c++){
            cur[c]=c;  // [0,1,2,3,4]
        }

        for(int r=0;r<row;r++){
            int[] next = new int[column];
            Arrays.fill(next, -1);
            for(int c=0;c<column;c++){ // c=4 grid[0] = [1,1,1,-1,-1]
                if(cur[c]==-1) continue; //
                int nextCol = 0;
                if(grid[r][c]==1){
                    if(c!=column-1 && grid[r][c+1]==-1)
                        nextCol = -1;
                    else
                        nextCol = c+1; // 3
                }
                else{
                    if(c!=0 && grid[r][c-1]==1)
                        nextCol=-1;
                    else
                        nextCol = c-1; // nextCol=3
                }

                if(nextCol<0||nextCol>=column){
                    continue;
                }

                next[nextCol] = cur[c]; // next[1] = 0, next[2]=-1 next[3]=-1
            }

            cur = next;
            //System.out.println(Arrays.toString(cur));
        }
        int[] r = new int[column];
        Arrays.fill(r, -1);
        for(int c=0;c<column;c++){ // [-1,0,-1,-1,-1]
            if( cur[c] != -1){
                r[cur[c]]=c;
            }
        }
        return r;
    }

    @Test
    public void test(){

        Assert.assertArrayEquals(new int[]{1,-1,-1,-1,-1}, findBall(new int[][]{
                {1,1,1,-1,-1},
                {1,1,1,-1,-1},
                {-1,-1,-1,1,1},
                {1,1,1,1,-1},
                {-1,-1,-1,-1,-1}}));
        Assert.assertArrayEquals(new int[]{-1}, findBall(new int[][]{
                {-1},
                }));
        Assert.assertArrayEquals(new int[]{0,1,2,3,4,-1}, findBall(new int[][]{
                {1,1,1,1,1,1},
                {-1,-1,-1,-1,-1,-1},
                {1,1,1,1,1,1},
                {-1,-1,-1,-1,-1,-1}
        }));



    }
}
