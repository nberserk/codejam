package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class LengthOfLargestCycle {


    int largestCycle(int[] edges){
        int N = edges.length;
        HashSet<Integer> visited = new HashSet<>();

        int r =0;
        for (int i = 0; i < N; i++) {
            List<Integer> path = new ArrayList<>();
            int len = traverse(edges, path, visited, i);
            r=Math.max(r, len);
        }
        return r;
    }

    private int traverse(int[] edges, List<Integer> path, Set<Integer> v, int cur) {
        if (cur==-1) return 0;
        if (v.contains(cur)){
            int i = path.indexOf(cur);
            return path.size()-i;
        }

        v.add(cur);
        path.add(cur);
        return traverse(edges, path, v, edges[cur]);
    }

    @org.junit.Test
    public void test(){
        assertEquals(3, largestCycle(new int[]{1,-1,3,4,2}));
        assertEquals(6, largestCycle(new int[]{1,2,3,4,5,0}));
    }
}
