package main.java.crackcode.sort;

import junit.framework.TestCase;

/**
 * Created by darren on 9/18/16.
 */
public class QuickSort extends TestCase{


    void quicksort(int[] a){
        _quicksort(a, 0, a.length - 1);
    }

    void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] =t;
    }

    private void _quicksort(int[] a, int s, int e) {
        if(s<e){
            int m = partition(a, s,e);
            _quicksort(a, s, m-1);
            _quicksort(a, m+1, e);
        }
    }

    private int partition(int[] a, int s, int e) {
        int p = a[e];
        int j=s-1;
        for (int i = s; i < e; i++) {
            if(a[i]<=p){
                j++;
                swap(a, i,j);
            }
        }
        swap(a, j+1, e);
        return j+1;
    }

    public void testQuickSort(){
        int[] a = {8,4,6,9,2,0,33};
        QuickSort q = new QuickSort();
        q.quicksort(a);
        for (int i = 1; i < a.length; i++) {
            assertTrue(a[i-1]<=a[i]);
        }
    }
}
