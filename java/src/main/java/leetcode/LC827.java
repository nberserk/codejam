package leetcode;


import java.util.*;

import static org.junit.Assert.assertEquals;

public class _827 {

    int[][] grid;
    int R,C;
    HashSet<Integer> v= new HashSet<>();
    HashMap<Integer,Set<Integer>> zero = new HashMap<>();

    int dfs(int y, int x, int id){
        if(v.contains(y*100+x)) return 0;
        v.add(y*100+x);
        int ret=1;

        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
        for(int i=0;i<4;i++){
            int nx=x+dir[i][0];
            int ny=y+dir[i][1];
            if(nx<0||ny<0||nx>=C||ny>=R) continue;
            if(grid[ny][nx]==0){
                int hash = ny*100+nx;
                zero.putIfAbsent(hash, new HashSet<>());
                zero.get(hash).add(id);
            }else{
                ret += dfs(ny,nx,id);
            }
        }
        return ret;
    }

    public int largestIsland(int[][] grid) {
        this.grid=grid;

        R=grid.length;
        C=grid[0].length;
        int islandId=0;
        List<Integer> dimen = new ArrayList<>();
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(grid[i][j]==1 &&!v.contains(i*100+j)){
                    int d = dfs(i, j, islandId++);
                    dimen.add(d);
                }
            }
        }
        if(zero.size()==0){
            if(dimen.size()==0) return 1;
            return dimen.get(0);
        }
        int max=0;
        for(int k:zero.keySet()){
            int cur=1;
            for(int id: zero.get(k)){
                cur+=dimen.get(id);
            }
            max=Math.max(max, cur);
        }
        return max;
    }

    @org.junit.Test
    public void test() {
        assertEquals(4, largestIsland(new int[][]{{1,1},{1,0}}));

    }
    @org.junit.Test
    public void test2() {
        assertEquals(4, largestIsland(new int[][]{{1,1},{1,1}}));
    }


}
