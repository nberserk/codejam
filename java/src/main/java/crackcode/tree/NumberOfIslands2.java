package main.java.crackcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 9/21/16.
 *
 * tags: union find, disjoint set
 *
 * from: https://leetcode.com/problems/number-of-islands-ii/
 */
public class NumberOfIslands2 {

    static class Point{
        int x, y;
        public Point(int x, int y){
            this.x=x;this.y=y;
        }
        Point parent;

        @Override
        public int hashCode() {
            return Arrays.hashCode( new Object[]{x,y});
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point){
                Point p = (Point)obj;
                if (x == p.x && y == p.y){
                    return true;
                }
            }
            return false;
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
                int nx = x + d[j][0];
                int ny = y + d[j][1];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                Point p = getRoot(nx, ny, parent);
                if(p!=null){
                    boolean duplicate =false;
                    for (int k = 0; k < island.size(); k++) {
                        Point cur = island.get(k);
                        if(cur.x == p.x && cur.y==p.y){
                            duplicate=true;
                            break;
                        }
                    }
                    if(!duplicate)
                        island.add(p);
                }
            }
            parent[y][x] = new Point(x,y);
            if(island.size()==0) {
                count++;
            }else if (island.size()==1){
                parent[y][x].parent = island.get(0);
            }else{
                count -= island.size()-1;
                Point newroot = island.get(0);
                for(int j=1;j<island.size();j++ ){
                    Point p = island.get(j);
                    p.parent = newroot;
                }
                parent[y][x].parent = newroot;
            }

            //
            ret.add(count);
        }
        return ret;
    }

    Point getRoot(int x, int y, Point[][] parent){
        Point cur = parent[y][x];
        if( cur== null) return null;

        if(cur.parent==null)
            return cur;

        while(cur.parent!=null){
            cur = cur.parent;
        }
        parent[y][x].parent = cur;
        return cur;
    }

    @Test
    public void test(){
        NumberOfIslands2 island = new NumberOfIslands2();
        List<Integer> ret =  island.numIslands2(3,3, new int[][]{{0,0}, {0,1}, {1,2}, {2,1}});

        int[] answers = {1,1,2,3};
        for (int i = 0; i < answers.length; i++) {
            assertEquals(answers[i], (int)ret.get(i));
        }

        ret = island.numIslands2(5,5, new int[][]{
                {1,2}, {2,1}, {1,1},
                {3,3}, {3,2}, {3,1}
            });

        answers = new int[]{1, 2, 1, 2, 2, 1};
        for (int i = 0; i < answers.length; i++){
            assertEquals(answers[i], (int)ret.get(i));
        }
    }
}
