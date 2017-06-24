package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 6/23/2017.
 */
public class FindClosestPalindrome_564 {

    boolean isPalindrome(String n){
        int N = n.length();
        int l = 0;
        int h = N-1;
        while(l<h){
            if(n.charAt(l) != n.charAt(h))
                return false;
            l++;
            h--;
        }
        return true;
    }

    public String nearestPalindromic(String n) {

        boolean palin = isPalindrome(n);
        int N = n.length();
        boolean odd = N%2==1;
        char[] c = n.toCharArray();


        int lo=0;
        int hi=0;
        if (odd){
            int center = N/2;
            if(palin){
                if(c[center]=='0')
                    c[center]++;
                else c[center]--;
            } else{
                lo = center+1;
                hi = center-1;
                while(hi>=0){
                    c[lo++] = c[hi--];
                }
            }
        }else {
            hi = N/2 -1;
            lo = hi+1;
            if(palin){
                if (c[hi]=='0'){
                    c[lo]++;
                    c[hi]++;
                }else{
                    c[lo]--;
                    c[hi]--;
                }
            }else{
                while(hi>=0){
                    c[lo++] = c[hi--];
                }
            }
        }

        return new String(c);
    }


    @Test
    public void test2(){
        assertEquals(true, isPalindrome("1"));
        assertEquals(false, isPalindrome("12"));
        assertEquals(true, isPalindrome("121"));

        assertEquals("121", nearestPalindromic("123"));
        assertEquals("6116", nearestPalindromic("6006"));

    }
}
