package main.java.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/5/17.
 * https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/?tab=Description
 */
public class KthSmallestInLexicographicalOrder_440 {
    public int findKthNumber(int n, int k) {
        if(k>n) return 0;

        int cur = 1;
        k--;
        while(k>0){
            int s = step(n, cur, cur+1 );
            //System.out.println("step-"+cur+":"+s);
            if(k>=s){
                k-=s;
                cur++;
            }else{
                k--;
                cur*=10;
            }
        }
        return cur;
    }

    int step(int n, long n1, long n2){
        int c=0;
        while(n1<=n){
            c+= Math.min(n2, n+1)-n1; // n+1: due to exclusive
            n1*=10;
            n2*=10;
        }
        return c;
    }

    @Test
    public void test(){
        assertEquals(3, findKthNumber(22, 16));
    }
}
