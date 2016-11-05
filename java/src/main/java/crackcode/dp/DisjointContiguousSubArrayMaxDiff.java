package main.java.crackcode.dp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/5/16.
 *
 * from: https://www.careercup.com/question?id=19286747
 *
 * Given an array of integers. Find two disjoint contiguous sub-arrays such that the absolute difference between the sum of two sub-array is maximum.
 * The sub-arrays should not overlap.

 eg> [2 -1 -2 1 -4 2 8] ans - (-1 -2 1 -4) (2 8), diff = 16

 */
public class DisjointContiguousSubArrayMaxDiff {

    int maxdiff(int[] a){
        int N = a.length;
        int[] max = new int[N];
        int[] min = new int[N];
        int[] revMax = new int[N];
        int[] revMin = new int[N];

        max[0]=a[0];
        min[0]=a[0];
        for (int i = 1; i < N; i++) {
            max[i] = Math.max(max[i-1]+a[i], a[i]);
            min[i] = Math.min(min[i-1]+a[i], a[i]);
        }

        revMax[N-1]=a[N-1];
        revMin[N-1]=a[N-1];
        for (int i = N-2; i >=0; i--) {
            revMax[i] = Math.max(revMax[i+1]+a[i], a[i]);
            revMin[i] = Math.min(revMin[i+1]+a[i], a[i]);
        }

        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < N-1; i++) {
            int cur = Math.abs(max[i] - revMin[i+1]);
            ret=Math.max(ret, cur);
            cur = Math.abs(min[i] - revMax[i+1]);
            ret=Math.max(ret, cur);
        }

        return ret;
    }


    @Test
    public void test(){
        int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        int[] a2= {2, -1, -2, 1, -4, 2, 8};
        int[] a3 = {-1, -2, 3, 4, -5, 6};

        assertEquals(12, maxdiff(a));
        assertEquals(16, maxdiff(a2));
        assertEquals(12, maxdiff(a3)); // {3,4} {-5}
    }
}
