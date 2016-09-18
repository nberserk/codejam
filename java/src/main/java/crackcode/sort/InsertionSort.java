package main.java.crackcode.sort;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by darren on 9/7/16.
 */

public class InsertionSort extends TestCase{


    void insertionSort(int[] a){
        for (int i = 1; i < a.length; i++) {
            int j=i-1;
            int key = a[i];
            while(j>=0 && key<a[j]){
                a[j+1] = a[j];
                j--;
            }
            a[j+1] =key;
        }
    }

    @Test
    public void test_insertionSort(){
        int[] a = {1,6,4,234,88,44,9,234,33};
        insertionSort(a);

        for (int i = 0; i < a.length - 1; i++) {
            assertTrue(a[i]<=a[i+1]);
        }

    }

}
