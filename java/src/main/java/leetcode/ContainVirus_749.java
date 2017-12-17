package leetcode;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ContainVirus_749 {

    int R, C;
    int[][][] walls;
    public int containVirus(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        walls = new int[R][C][2];

        int ret = 0;

        while (true){
            int maxWall=-1;
            int target=-1;
            HashSet<Integer> virus = new HashSet<>();
            HashSet<Integer> visited = new HashSet<>();
            for (int y = 0; y < R; y++) {
                for (int x = 0; x < C; x++) {
                    if(grid[y][x]==1 ){
                        int wall = findWall(grid, x,y, visited);
                        if(wall==0) continue;
                        virus.add(y*100+x);
                        if(wall>maxWall){
                            target=y*100+x;
                            maxWall=wall;
                        }
                    }
                }
            }
            if(maxWall==-1) break;
            ret +=maxWall;
            virus.remove(target);
            killVirus(grid, target);
            visited.clear();
            for(int v: virus){
                expandVirus(grid, v, visited);
            }

            printGrid(grid);
        }

        return ret;
    }

    private void expandVirus(int[][] grid, int v, Set<Integer> visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        while(stack.size()>0){
            int t = stack.pop();
            if(visited.contains(t)) continue;
            visited.add(t);

            int x = t%100;
            int y = t/100;
            //grid[y][x]=0;

            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if(nx<0||ny<0||nx>=C||ny>=R) continue;
                if(grid[ny][nx]==0 && !blocked(x,y,i)) {
                    grid[ny][nx]=1;
                    visited.add(ny*100+nx);
                    continue;
                }
                if(visited.contains(ny*100+nx)) continue;
                stack.push(ny*100+nx);
            }
        }
    }

    void printGrid(int[][] grid){
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
        System.out.println("");
    }

    private void killVirus(int[][] grid, int target) {
        Stack<Integer> stack = new Stack<>();
        stack.push(target);
        while(stack.size()>0){
            int t = stack.pop();
            int x = t%100;
            int y = t/100;
            if(grid[y][x]<0) continue;
            grid[y][x]=-grid[y][x];

            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if(nx<0||ny<0||nx>=C||ny>=R||grid[ny][nx]<0 ) continue;
                if(grid[ny][nx]==0){
                    if(i==0)     walls[ny][nx][0]=1;
                    else if(i==1)walls[y][x][0]=1;
                    else if(i==2)walls[y][x][1]=1;
                    else         walls[ny][nx][1]=1;
                    continue;
                }

                stack.push(ny*100+nx);
            }
        }
    }

    int[][] dirs = new int[][]{{-1,0},{1,0},{0,1}, {0,-1}};
    boolean blocked(int x, int y, int dir){
        if(dir==0&&  walls[y][x-1][0]==1)
            return true;
        else if(dir==1&&walls[y][x][0]==1)
            return true;
        else if(dir==2&&walls[y][x][1]==1)
            return true;
        else  if(dir==3&&walls[y-1][x][1]==1)
            return true;
        return false;
    }

    private int findWall(int[][] grid, int sx, int sy, Set<Integer> visited) {
        if(visited.contains(sy*100+sx)) return 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(sy*100+sx);

        int wall =0;
        while(stack.size()>0){
            int t = stack.pop();
            if(visited.contains(t)) continue;
            visited.add(t);
            int x = t%100;
            int y = t/100;

            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if(nx<0||ny<0||nx>=C||ny>=R ) continue;
                if(grid[ny][nx]==0){
                    if (!blocked(x,y,i))
                        wall++;
                    continue;
                }
                if(visited.contains(ny*100+nx)) continue;
                stack.push(ny*100+nx);
            }
        }
        return wall;
    }

    @org.junit.Test
    public void test(){


        assertEquals(56, containVirus(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}
        ));
        
        assertEquals(13, containVirus(new int[][]{
                {1,1,1,0,0,0,0,0,0},
                {1,0,1,0,1,1,1,1,1},
                {1,1,1,0,0,0,0,0,0}}));

        assertEquals(10, containVirus(new int[][]{{0,1,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,0}}));


//        assertEquals(true, isOneBitCharacter(new int[]{1,0,0}));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
