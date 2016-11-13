package main.java.hackerrank.strings;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/13/16.
 *
 * https://www.hackerrank.com/challenges/palindrome-index?h_r=internal-search
 *
 *
 */
public class PalindromeIndex {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();


        for (int i = 0; i < N; i++) {
            String in = s.next();
            int r = solve(in);
            System.out.println(r);
        }
    }

    private static int solve(String in) {
        return solveInternal(in, 0, in.length()-1, -1);
    }

    private static int solveInternal(String in, int left, int right, int removedIdx) {
        if(left>=right) return removedIdx;

        while(left<right){
            if(in.charAt(left)==in.charAt(right)) {
                left++; right--;
                continue;
            }
            if(removedIdx>=0) return -1;
            int temp = solveInternal(in, left + 1, right, left);
            if(temp!=-1) return temp;
            temp = solveInternal(in, left, right - 1, right);
            if(temp!=-1) return temp;
        }
        return removedIdx;
    }

    @Test
    public void test(){
        assertEquals(3, solve("aaab"));
        assertEquals(-1, solve("aaa"));
        assertEquals(0, solve("baa"));
    }


}
