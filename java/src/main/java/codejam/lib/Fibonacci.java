package main.java.codejam.lib;

import static org.junit.Assert.assertEquals;

/**
 * from http://www.careercup.com/question?id=5713892824055808
 *
 * count number of fibonacci numbers less than a given number.
 * n = 6, {0,1,1,2,3,5}
 *
 */
public class Fibonacci {

    static int countFibonacci(int n){
        int a = 0;
        int b=1;
        int count=2;
        while(a+b<n){
            count ++;
            int t = b;

            b = a+b;
            a = t;
        }
        return count;
    }

    public static void main(String[] args) {

        assertEquals(6, countFibonacci(6));
        assertEquals(7, countFibonacci(10));


    }

}
