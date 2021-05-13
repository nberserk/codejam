package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 *
 *
 */
public class LC785 {

    boolean valid(HashMap<Integer,Set<Integer>> g, HashMap<Integer,Integer> colors, int n, int c){
        if(colors.containsKey(n)){
            return c==colors.get(n);
        }
        colors.put(n, c);
        int nextColor = c==0?1:0;
        if(g.containsKey(n)){
            for(int next: g.get(n)){
                if(!valid(g,colors,next, nextColor))
                    return false;
            }
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        HashMap<Integer,Set<Integer>> g = new HashMap<>();
        for(int i=0;i<graph.length;i++){
            g.put(i, new HashSet<>());
            for(int n:graph[i]){
                g.get(i).add(n);
            }
        }
        HashMap<Integer, Integer> colors = new HashMap<>();
        for(int i=0;i<graph.length;i++){
            if(!colors.containsKey(i) && !valid(g, colors, i, 0)) return false;
        }
        return true;
    }

    @Test
    public void test(){

        assertEquals(true, isBipartite(new int[][]{{1,3},{0,2},{1,3},{0,2}}));



    }
}
