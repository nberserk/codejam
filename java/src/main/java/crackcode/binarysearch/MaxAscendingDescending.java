package main.java.crackcode.binarysearch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/20/16.
 *
 * https://www.careercup.com/question?id=5737503739871232
 *
 * Find the largest and smallest number in a list. The list is stored as two sections, one in ascending order and the other in descending order.
 *
 *  input [ 2 3 4 5 6 7 10 9 8 7]
    smallest : 2
    Largest :10


 */
public class MaxAscendingDescending {

    int getMax(int[] a){
        int N = a.length;
        if(N==1) return a[0];
        if(N==2) return Math.max(a[0], a[1]);

        int lo =0;
        int hi =N-1;
        while(lo<hi){
            int m = (lo+hi+1)/2;
            if(a[m]-a[m-1]>0)
                lo=m;
            else hi=m-1;
        }
        return a[lo];
    }


    @Test
    public void test(){
        int[] a= {2,3,4,5,6,8,10,7,6,5,3};

        assertEquals(10, getMax(a));
    }
}
