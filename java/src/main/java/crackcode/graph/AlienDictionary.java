package main.java.crackcode.graph;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 9/27/16.
 */
public class AlienDictionary {

    int dfs(HashMap<Integer, Set<Integer>> m, int here, int[] v, List<Integer> order){
        if(v[here]==2) return 1;
        if(v[here]==1) return 0; // detect cycle

        v[here]=1;
        Set<Integer> next = m.get(here);
        if(next!=null){
            for(int n: next){
                dfs(m, n, v, order);
            }
        }

        v[here]=2;
        order.add(here);

        return 1;
    }

    public String alienOrder(String[] words) {
        HashMap<Integer,Set<Integer>> m = new HashMap<>();
        for(int i=1;i<words.length;i++ ){
            String p= words[i-1];
            String cur= words[i];
            int len = Math.min(p.length(), cur.length());
            for(int j=0;j<len;j++ ){
                if(p.charAt(j)==cur.charAt(j))continue;
                int from = p.charAt(j)-'a';
                int to = cur.charAt(j)-'a';
                System.out.println(from+","+to);
                Set<Integer> set = m.get(from);
                if(set==null){
                    set = new HashSet<>();
                    m.put(from, set);
                }
                set.add(to);
                break;
            }
        }

        ArrayList<Integer> order = new ArrayList<>();
        int[] visited=new int[26];
        Set<Integer> keys = m.keySet();
        for(int s : keys){
            int r = dfs(m, s, visited, order);
            if(r==0) return "";
            
        }

        StringBuilder sb = new StringBuilder();
        for(int i=order.size()-1;i>=0;i-- ){
            sb.append((char) ('a'+order.get(i)));
        }
        return sb.toString();
    }

    @Test
    public void test(){
        assertEquals("wertf", alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
    }
}
