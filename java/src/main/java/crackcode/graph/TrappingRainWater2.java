package crackcode.graph;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 11. 6..
 *
 * https://leetcode.com/problems/trapping-rain-water-ii/
 *
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

 Note:
 Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

 Example:

 Given the following 3x6 height map:
 [
 [1,4,3,1,3,2],
 [3,2,1,3,2,4],
 [2,3,3,2,3,1]
 ]

 Return 4.

 */
public class TrappingRainWater2 {
    static class Cell{
        int x,y,height;
        Cell(int x, int y, int h){
            this.x=x;
            this.y=y;
            height=h;
        }
    }

    /**
     * idea : make boundary and process lowest boundary first,
     */
    public int trapRainWater(int[][] heightMap) {
        int R = heightMap.length;
        if(R<=1) return 0;
        int C = heightMap[0].length;

        PriorityQueue<Cell> pq = new PriorityQueue<>(100, new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return o1.height-o2.height;
            }
        });

        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < C; i++) {
            pq.add(new Cell(i, 0, heightMap[0][i]));
            pq.add(new Cell(i, R-1, heightMap[R-1][i]));
            visited[0][i]=true;
            visited[R-1][i]=true;
        }

        for (int i = 1; i < R-1; i++) {
            pq.add(new Cell(0, i, heightMap[i][0]));
            pq.add(new Cell(C-1, i, heightMap[i][C-1]));
            visited[i][0]=true;
            visited[i][C-1]=true;
        }

        int[][] dir={{1,0}, {-1,0},{0,-1}, {0,1}};
        int ret = 0;
        while(pq.size()>0){
            Cell cell = pq.poll();
            int x=cell.x;
            int y =cell.y;


            for(int[] d : dir){
                int nx = x+d[0];
                int ny = y+d[1];
                if (nx < 0 || ny < 0 || nx >= C || ny >= R || visited[ny][nx]) continue;
                int nh = Math.max(cell.height, heightMap[ny][nx]);
                int fill = nh-heightMap[ny][nx];
                ret += fill;
                pq.add( new Cell(nx,ny,nh));
                visited[ny][nx]=true;
            }
        }

        return ret;
    }

    @Test
    public void test(){
        int[][] r = {
                {12,13,1,12},
                {13,4,13,12},
                {13,8,10,12},
                {12,13,12,12},
                {13,13,13,13}        };

        assertEquals(14, trapRainWater(r));

    }
}
