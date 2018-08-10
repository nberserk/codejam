package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class _803 {
    int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    int MOD = 1000;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int R=grid.length;
        int C=grid[0].length;

        int[] ret = new int[hits.length];
        for (int i = 0; i < hits.length; i++) {
            Set<Integer> remove = new HashSet<>();
            int y=hits[i][0];
            int x=hits[i][1];
            if(grid[y][x]==0) continue;
            grid[y][x]=0;

            for(int j=0;j<dir.length;j++){
                int nx= x+dir[j][0];
                int ny= y+dir[j][1];
                if(nx<0||ny<0||nx>=C||ny>=R||grid[ny][nx]==0) continue;
                HashSet<Integer> visited= new HashSet<>();
                boolean hitTop = dfs(grid, nx,ny, visited);
                if(!hitTop){
                    remove.addAll(visited);
                }
            }
            ret[i]=remove.size();
            for(int pos: remove){
                grid[pos/MOD][pos%MOD]=0;
            }
        }
        return ret;
    }


    private boolean dfs(int[][] grid, int ox, int oy, HashSet<Integer> visited) {
        int R=grid.length;
        int C=grid[0].length;

        Stack<Integer> s = new Stack<>();
        s.add(oy*MOD+ox);
        while(!s.isEmpty()){
            int cur = s.pop();
            if(visited.contains(cur)) continue;
            visited.add(cur);
            int x = cur%MOD;
            int y = cur/MOD;
            if(y==0) return true;

            for (int i = 0; i < dir.length; i++) {
                int nx = x+dir[i][0];
                int ny=y+dir[i][1];
                if(nx<0||ny<0||nx>=C||ny>=R||grid[ny][nx]==0) continue;
                s.push(ny*MOD+nx);
            }
        }

        return false;
    }


    @Test
    public void test(){
        assertEquals("[2]", Arrays.toString(hitBricks(new int[][]{{1,0,0,0},{1,1,1,0}}, new int[][]{{1,0}})));
    }
}
