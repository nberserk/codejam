package leetcode;


import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class _804 {

    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = new String[] {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        HashSet<String> set = new HashSet<>();
        for (String w : words) {
            String v = "";
            for (char c : w.toCharArray()) {
                v += morse[c - 'a'];
            }
            set.add(v);
        }
        return set.size();
    }

    @org.junit.Test
    public void test() {
        assertEquals(2, uniqueMorseRepresentations(new String[] {"gin", "zen", "gig", "msg"}));
    }
}
