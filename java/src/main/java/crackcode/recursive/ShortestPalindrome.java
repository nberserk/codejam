package crackcode.recursive;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 10. 23..
 *
 * https://leetcode.com/problems/shortest-palindrome/
 *
 */
public class ShortestPalindrome {

    public String shortestPalindrome(String s) {
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) { j += 1; }
        }
        if (j == s.length()) { return s; }
        String suffix = s.substring(j);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
    }

    @Test
    public void test(){

        assertEquals("aaacecaaa", shortestPalindrome("aacecaaa"));
        assertEquals("dcbabcd", shortestPalindrome("abcd"));
    }
}
