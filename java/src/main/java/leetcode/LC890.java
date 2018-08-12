package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC890 {
    boolean isBipartitle(HashMap<Integer,List<Integer>> map, int n, Map<Integer, Integer> colors, int color){
        if(colors.containsKey(n)){
            return colors.get(n)==color;
        }
        colors.put(n, color);

        int nextColor = color==0?1:0;
        if(map.get(n)!=null) {
            for(int next:map.get(n)){
                if(!isBipartitle(map, next, colors, nextColor))
                    return false;
            }
        }
        return true;
    }
    public boolean possibleBipartition(int N, int[][] dislikes) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int[] d : dislikes){
            map.putIfAbsent(d[0], new ArrayList<>());
            map.putIfAbsent(d[1], new ArrayList<>());
            map.get(d[0]).add(d[1]);
            map.get(d[1]).add(d[0]);
        }
        HashMap<Integer, Integer> color = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            if (color.containsKey(i)) continue;
            if( !isBipartitle(map, i, color, 0))
                return false;
        }
        return true;
    }

    @Test
    public void test(){
        assertEquals(false, possibleBipartition(5, new int[][] {{1,2},{2,3},{3,4},{4,5},{1,5}}));
    }
}
