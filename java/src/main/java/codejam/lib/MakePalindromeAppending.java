package main.java.codejam.lib;

import static org.junit.Assert.assertEquals;

/**
 * http://www.careercup.com/question?id=5673151029575680
 * make palindrome by appending minimum set of chars at the end
 */
public class MakePalindromeAppending {

    static String checkPalindrome(String s, int left, int right){
        int size = s.length();
        while(left>=0 && right<size){
            if(s.charAt(left)!= s.charAt(right))
                return null;
            left--;
            right++;
        }

        if (left<0 && right>=size)
            return s;
        else if(left<0){
            return null;
        }else if(right>=size){
            String r=s;
            for (int i = left; i >=0 ; i--) {
                r += s.charAt(i);
            }
            return r;
        }
        return null;
    }

    static String min(String s){
        if(s.length()==1) return s;

        int n = s.length();
        int half = n/2;
        String min=s+s;
        for (int i = half-1; i < n; i++) {
            if(i!=n-1 && s.charAt(i) == s.charAt(i+1)){
                // xxaaxx
                String r = checkPalindrome(s, i-1, i+2);
                if(r!=null && r.length() < min.length()){
                    min = r;
                }
            }

            String r = checkPalindrome(s, i-1, i+1);
            if(r!=null && r.length() < min.length()){
                min = r;
            }
        }

        return min;
    }

    static String makePalindrome(String s){
        String reverse = new StringBuffer(s).reverse().toString();

        int len = s.length();
        for (int i = 0; i <len; i++) {
            boolean match=true;
            int j;
            for (j = 0; j < len; j++) {
                if(i+j>=len)break;
                if(s.charAt(i+j) != reverse.charAt(j)){
                    match=false;
                    break;
                }
            }
            if(match){
                return s+reverse.substring(len-i,len);
            }
        }
        return "";
    }

    public static void main(String[] args) {

        assertEquals("testset", min("test"));
        assertEquals("testset", makePalindrome("test"));

        assertEquals("bab", min("bab"));
        assertEquals("bab", makePalindrome("bab"));

        assertEquals("babbab", min("babb"));
        assertEquals("babbab", makePalindrome("babb"));
    }
}
