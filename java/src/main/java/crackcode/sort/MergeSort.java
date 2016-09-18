package main.java.crackcode.sort;

import junit.framework.TestCase;

/**
 * Created by darren on 9/18/16.
 */
public class MergeSort extends TestCase {

    void mergeSort(int[] a){
        int[] b = new int[a.length];
        _mergesort(a, b, 0, a.length-1);
    }

    // s,e : inclusive
    private void _mergesort(int[] a, int[] b, int s, int e) {
        if(s==e) return;

        int m = s + (e-s)/2;
        _mergesort(a,b,s,m);
        _mergesort(a,b,m+1,e);

        int lo=s;
        int hi=m+1;
        for (int i = s; i <= e; i++) {
            if(lo>m ) {
                b[i] = a[hi];
                hi++;
            }else if (hi>e){
                b[i] = a[lo];
                lo++;
            }else if (a[lo]<=a[hi]){
                b[i] = a[lo];
                lo++;
            }else{
                b[i] = a[hi];
                hi++;
            }
        }
        for (int i = s; i <= e; i++) {
            a[i] = b[i];
        }
    }

    public void testMergeSort(){
        int[] a = {8,4,6,9,2,0,33,11,100};
        mergeSort(a);
        for (int i = 1; i < a.length; i++) {
            assertTrue(a[i-1]<=a[i]);
        }
    }

}
