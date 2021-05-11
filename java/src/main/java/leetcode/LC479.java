package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 16/02/2017.
 */
public class LargestPalindromProduct_479 {
    public int largestPalindrome(int n) {

        if(n==1) return 9;

        long max = (long)Math.pow(10, n)-1;
        long min = (long)Math.pow(10, n-1);

        long mod = max+1;
        long finalMod = 1337;

        long palindrome = max*max;     // possible max
        long left = palindrome/mod;
        long right = palindrome%mod;
        if(left>right) left--;

        while(left>=min){

            palindrome = left*mod+reverse(left);

            // note that if i is greater that n digit, this for loop will not be executed.
            // a*b (a>b) checked we don't need to check b*a again.
            for (long i = max; i>= palindrome/i ; i--) {
                if(palindrome%i==0) {
                    return (int) (palindrome % finalMod);
//                    return (int)possibleMax;
                }
            }
            left--;
        }


        return (int)(palindrome%finalMod);
    }

    long reverse(long d){
        long r = 0;
        while(d>0){
            r *=10;

            long t = d%10;
            r += t;
            d/=10;
        }
        return r;
    }


    @Test
    public void test(){
        assertEquals(9, largestPalindrome(1));
        assertEquals(987, largestPalindrome(2));
        assertEquals(475, largestPalindrome(8));
    }
}
