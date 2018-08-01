package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class _207 {
    HashMap<Integer,List<Integer>> g = new HashMap<>();
    boolean traverse(int cur, Set<Integer> open, Set<Integer> visited){
        if(open.contains(cur)) return false;
        if(visited.contains(cur)) return true;
        visited.add(cur);

        open.add(cur);

        if(g.get(cur)!=null){
            for(int next: g.get(cur)){
                boolean ret = traverse(next, open, visited);
                if(ret==false) return false;
            }
        }
        open.remove(cur);
        return true;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int N = numCourses;

        for(int[] p: prerequisites){
            if(!g.containsKey(p[0]))
                g.put(p[0], new ArrayList<>());
            g.get(p[0]).add(p[1]);
        }

        HashSet<Integer> open = new HashSet<>();
        HashSet<Integer> close = new HashSet<>();
        for(int i=0;i<N;i++){
            if(traverse(i, open, close)==false)
                return false;
        }
        return true;
    }

    @Test
    public void test() {
        assertEquals(false, canFinish(2, new int[][]{{1,0}, {0,1}}));
    }
}
