package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LC887 {
    public int projectionArea(int[][] grid) {
        int N= grid.length;

        int ret = 0;
        for(int y=0;y<N;y++){
            int max=0;
            int max2=0;
            for(int x=0;x<N;x++){
                ret += grid[y][x]>0?1:0;
                max=Math.max(max, grid[y][x]);
                max2=Math.max(max2, grid[x][y]);
            }
            ret+=max;
            ret+=max2;
        }

        return ret;
    }

    @Test
    public void test(){
        assertEquals(14, projectionArea(new int[][]{
                {1,1,1},
                {1,0,1},
                {1,1,1}
        }));
    }
}
