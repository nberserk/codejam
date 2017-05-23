package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 * https://leetcode.com/problems/coin-change-2/
 *
 */
public class MinimumWindowSubstring_76 {

    public String minWindow(String s, String t) {
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

        String ret = s+"T";

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

        while(tail<N){
            // move head
            char cHead = s.charAt(head);
            map.put(cHead, map.get(cHead)-1);
            head++;
            while(!map.containsKey(s.charAt(head))){
                head++;
            }

            if(map.get(cHead)>=org.get(cHead)){

            }else{
                // move tail
                tail++;
                while(cHead==s.charAt(tail)){
                    
                    tail++;
                }
            }

            if(ret.length()> tail-head+1){
                ret = s.substring(head, tail+1);
            }
        }



        if(ret.length()>N) return "";
        return ret;
    }

    @Test
    public void test(){
        assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
        assertEquals("aa", minWindow("aa", "aa"));


    }
}
