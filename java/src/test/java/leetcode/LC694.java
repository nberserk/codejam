package leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC694 {

    String traverse(int[][] g, int x, int y){
        int R= g.length;
        int C=g[0].length;

        g[y][x]=0;
        int[][] d =  new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
        String ret="";
        for(int i=0;i<4;i++){
            int nx = x + d[i][0];
            int ny = y + d[i][1];
            if(nx<0||ny<0||ny>=R||nx>=C || g[ny][nx]==0) continue;
            ret=ret+i;
            //System.out.println(ret);
            ret+=traverse(g, nx,ny);
            ret+="|";
        }
        return ret;
    }
    public int numDistinctIslands(int[][] grid) {
        int R=grid.length;
        int C=grid[0].length;

        Set<String> sig = new HashSet<>();
        for(int y=0;y<R;y++){
            for(int x=0;x<C;x++){
                if(grid[y][x]==1){
                    String cur=traverse(grid, x,y);
                    //System.out.println(cur);
                    sig.add(cur);
                }

            }
        }
        return sig.size();
    }

    @Test
    public void test(){
        assertEquals(1, numDistinctIslands(new int[][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}}));
        assertEquals(15, numDistinctIslands(new int[][]{{0,0,1,0,1,0,1,1,1,0,0,0,0,1,0,0,1,0,0,1,1,1,0,1,1,1,0,0,0,1,1,0,1,1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,1,0},{0,0,1,0,0,1,1,1,0,0,1,0,1,0,0,1,1,0,0,1,0,0,0,1,0,1,1,1,0,0,0,0,0,0,0,1,1,1,0,0,0,1,0,1,1,0,1,0,0,0},{0,1,0,1,0,1,1,1,0,0,1,1,0,0,0,0,1,0,1,0,1,1,1,0,1,1,1,0,0,0,1,0,1,0,1,0,0,0,1,1,1,1,1,0,0,1,0,0,1,0},{1,0,1,0,0,1,0,1,0,0,1,0,0,1,1,1,0,1,0,0,0,0,1,0,1,0,0,1,0,1,1,1,0,1,0,0,0,1,1,1,0,0,0,0,1,1,1,1,1,1}}));
    }
}
