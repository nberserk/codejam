package leetcode;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class _323 {
    int parent(int[]p, int i){
        while(p[i]!=i){
            i=p[i];
        }
        return i;
    }
    public int countComponents(int n, int[][] edges) {
        int[] p = new int[n];
        for(int i=0;i<n;i++){
            p[i]=i;
        }
        for(int[] e:edges){
            int p1 = parent(p, e[0]);
            int p2 = parent(p, e[1]);
            if(p1!=p2) p[p1]=p2;
        }
        HashSet<Integer> distinct = new HashSet<>();
        for(int i=0;i<n;i++){
            distinct.add(parent(p, i));
        }
        return distinct.size();
    }

    @Test
    public void test(){
        assertEquals(2, countComponents(5, new int[][]{{0,1},{1,2},{3,4}}));

    }
}
