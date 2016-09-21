package main.java.crackcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darren on 9/21/16.
 *
 * union set,
 *
 * from: https://leetcode.com/problems/number-of-islands-ii/
 */
public class NumberOfIslands2 {

    static class Point{
        int x, y;
        public Point(int x, int y){
            this.x=x;this.y=y;
        }
    }
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ret = new ArrayList<>();

        Point[][] parent = new Point[m][n];
        int count=0;

        for(int i=0;i<positions.length;i++){
            int y = positions[i][0];
            int x = positions[i][1];

            if(parent[y][x]!=null){
                ret.add(count);
                continue;
            }
            int[][] d= {{0,1},{0,-1},{1,0},{-1,0} };

            List<Point> island = new ArrayList<>();
            for(int j=0;j<4;j++){
                int nx = x + d[i][0];
                int ny = y + d[i][1];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                Point p = getParent(nx,ny,parent);
                if(p!=null){
                    // todo duplicate check
                    island.add(p);
                }
            }
            if(island.size()==0) {
                parent[y][x] = new Point(x,y);
                count++;
            }else if (island.size()==1){
                parent[y][x] = island.get(0);
            }else{
                count -= island.size()-1;
                Point newroot = island.get(0);
                for(int j=1;j<island.size();j++ ){
                    Point p = island.get(j);
                    parent[p.y][p.x] = newroot;
                }
                parent[y][x]=newroot;
            }

            //
            ret.add(count);
        }
        return ret;
    }

    Point getParent(int x, int y, Point[][] parent){
        Point cur = parent[y][x];
        if( cur== null) return null;

        if(parent[cur.y][cur.x].x == cur.x && parent[cur.y][cur.x].y == cur.y){
            return parent[y][x];
        }

        while(parent[cur.y][cur.x].x != cur.x && parent[cur.y][cur.x].y != cur.y){
            cur = parent[cur.y][cur.x];
        }
        parent[y][x] = cur;
        return cur;
    }

    @Test
    public void UnionSet(){
        NumberOfIslands2 island = new NumberOfIslands2();
        island.numIslands2(3,3, new int[][]{{0,0}, {0,1}, {1,2}, {2,1}});

        int[][][] d3 = new int[2][2][2];
        d3[1][1][1] = -1;

        int[] ref = d3[0][0];
        System.out.println(ref[0]);
    }
}
