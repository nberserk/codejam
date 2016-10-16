package main.java.crackcode.math;

/**
 * Created by darren on 10/16/16.
 *
 * https://leetcode.com/problems/nth-digit/
 *
 *
 * idea:
 *      1 digit : 9 [1-9]
 *      2 digit : 90 [10-99]
 *      3 digit : 900 [100-999]
 *      ....
 */
public class NthDigit {

    public int findNthDigit(long n) {
        //n--; // 10

        int start =1;
        int len=1;
        long cnt=9;
        while(n>cnt*len){
            n-=cnt*len;//1
            len++;//2
            start*=10;//10
            cnt*=10;
        }
        long num = start + (n-1)/len;
        int digit = (int)(n-1)%len;
        //System.out.println(num+","+digit);
        String str = String.valueOf(num);
        return str.charAt(digit)-'0';
    }
}
