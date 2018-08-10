package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC299 {
    public String getHint(String secret, String guess) {
        Map<Character,Set<Integer>> org = new HashMap<>();
        for(int i=0;i<secret.length();i++){
            char c = secret.charAt(i);
            org.putIfAbsent(c, new HashSet<>());
            org.get(c).add(i);
        }
        Map<Character,Set<Integer>> map = new HashMap<>();
        for(int i=0;i<guess.length();i++){
            char c = guess.charAt(i);
            map.putIfAbsent(c, new HashSet<>());
            map.get(c).add(i);
        }

        int s=0;
        int b=0;
        for(char key : org.keySet()){
            Set<Integer> set = org.get(key);
            Set<Integer> t = map.get(key);
            if(t==null) continue;
            int cs=0, cb=0;
            for(int p: set){
                if(t.contains(p)) cs++;
            }
            cb=Math.min(set.size()-cs, t.size()-cs);
            s+=cs;
            b+=cb;
        }
        return s+"A"+b+"B";
    }


    @Test
    public void test(){
        assertEquals("1A3B", getHint("1807", "7810"));
    }
}
