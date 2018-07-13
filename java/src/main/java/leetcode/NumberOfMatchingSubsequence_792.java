package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;


public class NumberOfMatchingSubsequence_792 {
    
    public int numMatchingSubseq(String S, String[] words) {
        HashMap<Character, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            map.putIfAbsent(c, new TreeSet<>());
            map.get(c).add(i);
        }

        int ret = 0;
        for (String w : words) {
            boolean matched = true;
            int pos = -1;
            for (char c : w.toCharArray()) {
                if (map.get(c) == null) {
                    matched = false;
                    break;
                }
                Integer next = map.get(c).higher(pos);
                if (next != null && next > pos) {
                    pos = next;
                } else {
                    matched = false;
                    break;
                }
            }
            if (matched)
                ret++;
        }

        return ret;
    }

    @Test(timeout = 1000)
    public void test() {
        assertEquals(3, numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"}));
        assertEquals(3, numMatchingSubseq("abcdefjdaklfdjsaklfjdsalfkdjsaljfkdlsafkdlsahfdsahfafakfhdsafhajkhfskajhfsadjkhfjakhfsjkdahfjsdakhfdjakslhfajdsklfhdjakl",
                new String[]{"a", "bb", "afjalkfjdsaklcd", "acefjalkfdalkfjdsalfkldaj", "jfkaljfkdlafjqhfhqfdklajfklda"}));

    }
}
