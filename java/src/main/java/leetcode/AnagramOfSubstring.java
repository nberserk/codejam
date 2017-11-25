package leetcode;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 *
 *

 Given 2 words, return true if second word has a substring that is also an anagram of word 1.
 LGE , GOOGLE- True
 GEO, GOOGLE - False

 */
public class AnagramOfSubstring {


    boolean isAnagram(String full, String ana){
        int len = ana.length();
        if(len>full.length()) return false;

        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> target = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = full.charAt(i);
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c)+1);

            char c2 = ana.charAt(i);
            target.putIfAbsent(c2, 0);
            target.put(c2, target.get(c2)+1);
        }

        if(map.equals(target)) return true;
        for (int i = len; i < full.length(); i++) {
            char del = full.charAt(i-len);
            map.put(del, map.get(del)-1);
            if(map.get(del)==0) map.remove(del);

            char add = full.charAt(i);
            map.putIfAbsent(add, 0);
            map.put(add, map.get(add)+1);

            if(map.equals(target)) return true;
        }
        return false;
    }

    @Test
    public void test(){
        assertEquals(true, isAnagram("GOOGLE", "LGE"));
        assertEquals(false, isAnagram("GOOGLE", "GEO"));
    }
}
