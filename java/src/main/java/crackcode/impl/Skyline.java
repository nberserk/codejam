package crackcode.impl;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 11. 6..
 */
public class Skyline {

    static class Edge{
        int x,y, open;//1:open, 0:close
        int org;
        Edge(int x, int y, int o, int open){
            // p = new int[2];            p[0]=x; p[1]=y;
            this.x=x; this.y=y;
            org=o; this.open=open;
        }
        public int hashCode(){
            return org;
        }
        public boolean equals(Object o){
            if(o instanceof Edge){
                Edge eo = (Edge)o;
                if(eo.org==this.org) return true;
                else return false;
            }
            return false;
        }
    }
    public List<int[]> getSkyline(int[][] buildings) {
        int N = buildings.length;
        int E = 2*N;
        List<int[]> ret = new LinkedList<>();
        if(N==0) return ret;

        Edge[] edge = new Edge[E];

        for(int i=0;i<N;i++){
            int h = buildings[i][2];
            edge[2*i] = new Edge(buildings[i][0], h, i, 1);
            edge[2*i+1] = new Edge(buildings[i][1], h,i, 0);
        }
        Arrays.sort(edge, new Comparator<Edge>(){
            public int compare(Edge m, Edge o){
                return m.x-o.x;
            }
        });

        PriorityQueue<Edge> open = new PriorityQueue<>(100, new Comparator<Edge>(){
            public int compare(Edge m, Edge o){
                return o.y - m.y;
            }
        } );
        open.offer(new Edge(-1,0,-1,1));

        int prevHeight=0;
        int prevX = 0;
        for(int i=0;i<E;i++){
            Edge e = edge[i];
            if(e.open==1)      open.add(e);
            else open.remove(e);

            if(i<E-1&&e.x==edge[i+1].x) continue;

            int cur = open.peek().y;
            if(prevHeight!= cur){
                ret.add(new int[]{e.x, cur});
                prevHeight  =cur;
            }
        }

        return ret;
    }

    @Test
    public void test(){
        int[][] building = {{0,2,3},{2,5,3}};


        List<int[]> pt = getSkyline(building);
        assertArrayEquals(new int[]{0, 3}, pt.get(0));
        assertArrayEquals(new int[]{5, 0}, pt.get(1));

    }

}
