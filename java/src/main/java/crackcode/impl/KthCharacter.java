package main.java.crackcode.impl;

import junit.framework.TestCase;

/**
 * Created by darren on 9/18/16.
 *
 * if we make string something like below, find kth digit number!
 * 123456789101112131415..100101102
 *
 */
public class KthCharacter extends TestCase {

    int kthChar(int k){
        k--;
        int d =9;
        int start=1;
        int numDigit = 1;
        while(k-d>=0){
            k-=d;
            d*=10;
            start *=10;
            numDigit++;
        }
        int target = start + k/numDigit;
        int kthDigit = numDigit-(k%numDigit);

        int divide=1;
        kthDigit--;
        while(kthDigit>0){
            divide*=10;
            kthDigit--;
        }
        int ret = (target%(divide*10))/divide;

        return ret;
    }


    public void testKthChar(){
        KthCharacter k = new KthCharacter();

        assertEquals(1, k.kthChar(1));
        assertEquals(9, k.kthChar(9));
        assertEquals(1, k.kthChar(10));
        assertEquals(1, k.kthChar(100));
        assertEquals(0, k.kthChar(104));
        assertEquals(1, k.kthChar(105));
        assertEquals(8, k.kthChar(156));
    }
}
