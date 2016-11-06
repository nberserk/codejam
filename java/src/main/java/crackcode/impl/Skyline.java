package crackcode.impl;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 11. 6..
 */
public class Skyline {

    static class Edge{
        int[] p;
        int org;
        boolean open;
        Edge(int x, int y, int o, boolean open){
            p = new int[2];
            p[0]=x; p[1]=y;
            //this.x=x; this.y=y;
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
            edge[2*i] = new Edge(buildings[i][0], h, i, true);
            edge[2*i+1] = new Edge(buildings[i][1], h,i, false);
        }
        Arrays.sort(edge, new Comparator<Edge>() {
            public int compare(Edge m, Edge o) {
                return m.p[0] - o.p[0];
            }
        });

        PriorityQueue<Edge> open = new PriorityQueue<>(100, new Comparator<Edge>(){
            public int compare(Edge m, Edge o){
                //if(o.p[1] == m.p[1]) return Boolean.compare(m.open,o.open);
                return o.p[1] - m.p[1];
            }
        } );
        open.offer(new Edge(-1,0,-1,true));

        int prevHeight=0;
        for(int i=0;i<E;i++){
            Edge e = edge[i];
            if(e.open)      open.add(e);
            else open.remove(e);

            int cur = open.peek().p[1];
            if(prevHeight!= cur){
                ret.add(new int[]{e.p[0], cur});
                prevHeight  =cur;
            }
        }

        return ret;
    }

    @Test
    public void test(){
        int[][] building = {{0,2,3},{2,5,3}};


        assertEquals("[[0,3],[5,0]]", getSkyline(building).toString());

    }

}
