package crackcode.hash;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 11. 10..
 *
 * from: http://www.geeksforgeeks.org/rearrange-a-string-so-that-all-same-characters-become-at-least-d-distance-away/
 *
 */
public class RearrangeSameCharKDistanceAway {

    static class CharFrequency{
        char c;
        int count;
        CharFrequency(char c, int count){
            this.c=c;
            this.count=count;
        }
    }

    String rearrange(String in, int k ){
        int N = in.length();

        Map<Character, Integer> map = new HashMap<>();
        for(char c: in.toCharArray()){
            if(map.containsKey(c)) {
                int v = map.get(c) + 1;
                map.put(c, v);
            } else map.put(c, 1);
        }

        PriorityQueue<CharFrequency> pq = new PriorityQueue<>(100, new Comparator<CharFrequency>() {
            @Override
            public int compare(CharFrequency o1, CharFrequency o2) {
                return o2.count-o1.count;
            }
        });
        for (char c:map.keySet()){
            pq.add(new CharFrequency(c, map.get(c)));
        }

        char[] ret = new char[in.length()];
        Arrays.fill(ret, '0');
        while(pq.size()>0){
            int i=0;

            CharFrequency cf = pq.poll();
            for (int j = 0; j < cf.count; j++) {
                while(i<N&&ret[i]!='0') i++;
                if(i>=N){
                    return "";
                }
                ret[i]=cf.c;
                i+=k;

            }
        }

        return String.valueOf(ret);
    }


    @Test
    public void test(){
        assertEquals("bab", rearrange("abb", 2));
        assertEquals("bacbac", rearrange("aacbbc", 3));
        assertEquals("ekseksegfegro", rearrange("geeksforgeeks", 3));
        assertEquals("", rearrange("aaa", 2));
    }

}
