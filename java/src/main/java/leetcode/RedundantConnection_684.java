package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;

public class RedundantConnection_684 {


    boolean detectCycle(HashMap<Integer, HashSet<Integer>> g, int cur, int prev, Set<Integer> v){
        if(v.contains(cur)){
            return true;
        }

        v.add(cur);
        for(int next: g.get(cur)){
            if(next==prev) continue;
            if(detectCycle(g,next, cur, v)) return true;
        }

        return false;
    }

    public int[] findRedundantConnection_graph(int[][] edges) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        int[] r = null;
        Set<Integer> visited = new HashSet<>();
        for (int[]e : edges){
            HashSet<Integer> set = graph.get(e[0]);
            if(set==null){
                set =  new HashSet<>();
                graph.put(e[0], set);
            }
            set.add(e[1]);

            HashSet<Integer> set2 = graph.get(e[1]);
            if(set2==null){
                set2 =  new HashSet<>();
                graph.put(e[1], set2);
            }
            set2.add(e[0]);

            visited.clear();
            if (detectCycle(graph, e[0],-1, visited)){
                set.remove(e[1]);
                set2.remove(e[0]);
                r=e;
            }
        }

        return r;
    }

    public int[] findRedundantConnection_union(int[][] edges) {
        int[] parent = new int[2001];
        for (int i = 1; i < 2001; i++) {
            parent[i]=i;
        }

        for (int[]e:edges){
            int p1 = find(parent, e[0]);
            int p2 = find(parent, e[1]);
            if(p1==p2) return e;
            parent[p1] = p2;
        }

        return new int[]{1,2};
    }

    int find(int[] parent, int src){
        while(parent[src]!= src){
            src=parent[src];
        }
        return src;
    }



    @org.junit.Test
    public void test(){
        assertArrayEquals(new int[]{2, 3}, findRedundantConnection_union(new int[][]{{1, 2}, {1, 3}, {2, 3}}));
        assertArrayEquals(new int[]{1, 4}, findRedundantConnection_union(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}));

        assertArrayEquals(new int[]{2, 3}, findRedundantConnection_graph(new int[][]{{1, 2}, {1, 3}, {2, 3}}));
        assertArrayEquals(new int[]{1, 4}, findRedundantConnection_graph(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}));
    }
}
