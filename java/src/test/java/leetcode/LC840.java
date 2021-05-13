package leetcode;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class LC840 {

    public int numMagicSquaresInside(int[][] grid) {
        int ret=0;

        int[][] mapping= {{-1,-1},{0,-1},{1,-1},{1,0}};
        int[][] mapping2= {{1,1},{0,1} ,{-1,1},{-1,0}};

        for (int y = 0; y < grid.length - 2; y++) {
            for (int x = 0; x < grid[0].length - 2; x++) {
                int sum=0;
                boolean valid=true;
                for (int i = 0; i < 4; i++) {
                    int cur=0;
                    cur += grid[y+1+mapping[i][1]][x+1+mapping[i][0]];
                    cur += grid[y+1+mapping2[i][1]][x+1+mapping2[i][0]];
                    if(i==0) sum=cur;
                    else{
                        if(cur!=sum) {
                            valid=false;
                            break;
                        }
                    }
                }
                if(valid) ret++;
            }
        }


        return ret;
    }

    @org.junit.Test
    public void test() {
        assertEquals(1, numMagicSquaresInside(new int[][]{
            {4,3,8,4},
            {9,5,1,9},
            {2,7,6,2}
        }));
    }
}
