package crackcode.sort;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 10. 12..
 */
public class MedianOfUnsortedArray {

    void swap(int[] a, int i, int j){
        int t = a[i];
        a[i]=a[j];
        a[j]=t;
    }


    // start, end inclusive
    int partition(int[] a, int start, int end){
        int pivot = a[end];
        int j = start-1;
        for (int i = start; i <end ; i++) {
            if(a[i]<pivot)
                swap(a, i, ++j);
        }
        swap(a, end, j+1);
        return j+1;
    }

    // k: 0 based index
    // Time complexity : O(N)
    int quickselect(int[] a, int k){
        int left = 0;
        int right = a.length-1;
        while(true){
            int m = partition(a, left, right);
            if (k>m)
                left = m+1;
            else if (k<m)
                right=m-1;
            else return a[m];
        }
    }

    int median(int[] a){
        return quickselect(a, a.length/2);
    }

    @Test
    public void test(){
        int[] a = {7,1,45,8,9};
        assertEquals(8, median(a));


    }
}
