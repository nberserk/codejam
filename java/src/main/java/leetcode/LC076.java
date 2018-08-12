package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 *
 *
 */
public class LC076 {

    public String minWindow(String s, String t) {
        int N = s.length();
        int T = t.length();

        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            if(map.containsKey(c))
                map.put(c, map.get(c)+1);
            else
                map.put(c, 1);
        }

        int start=0;
        int end=0;
        int remain = T;
        int min = Integer.MAX_VALUE;
        int minStart = 0;
        while(end<N){
            char last = s.charAt(end);
            if(map.containsKey(last)){
                if(map.get(last)>0) remain--;
                map.put(last, map.get(last)-1);
            }

            while(remain==0){
                int d = end-start+1;
                if(min>d){
                    minStart=start;
                    min=d;
                }
                char first = s.charAt(start);
                if(map.containsKey(first)){
                    if(map.get(first)==0) remain++;
                    map.put(first, map.get(first)+1);
                }
                start++;
            }
            end++;
        }
        return min==Integer.MAX_VALUE ? "": s.substring(minStart, minStart+min);
    }

    public String minWindow_first(String s, String t) {
        int N = s.length();
        int T = t.length();

        Map<Character, Integer> org = new HashMap<>();
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            if(org.containsKey(c))
                org.put(c, org.get(c)+1);
            else
                org.put(c, 1);
            map.put(c, 0);
        }

        String ret = "";

        int len=0;
        int head=-1;
        int tail=0;
        for(int i=0;i<N;i++){
            char c = s.charAt(i);
            if(org.containsKey(c)){
                if(head==-1) head=i;
                map.put(c, map.get(c)+1);
                if(map.get(c)>org.get(c)){
                }else{
                    len++;
                    tail=i;
                    if(len == T){
                        ret = s.substring(head, i+1);
                        break;
                    }
                }
            }
        }

        if(head==-1 || len !=T) return ret;

        while(tail<N){
            // move head
            char cHead = s.charAt(head);
            map.put(cHead, map.get(cHead)-1);
            head++;
            while(!map.containsKey(s.charAt(head))){
                head++;
                if(head==N) return ret;
            }

            if(map.get(cHead)>=org.get(cHead)){
            }else{
                // move tail
                tail++;
                while( tail<N ){
                    char ctail = s.charAt(tail);
                    if( org.containsKey(ctail)) {

                        map.put(ctail, map.get(ctail) + 1);
                        if ( cHead==s.charAt(tail))
                            break;
                    }
                    tail++;
                }
                if (tail==N) break;
            }

            if(ret.length()==0 || ret.length() > tail-head+1){
                ret = s.substring(head, tail+1);
            }
        }


        return ret;
    }

    @Test
    public void test(){

        assertEquals("baca", minWindow("acbbaca", "aba"));
        assertEquals("ba", minWindow("bba", "ab"));
        assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
        assertEquals("aa", minWindow("aa", "aa"));
        assertEquals("aba", minWindow("aba", "aa"));


    }
}
