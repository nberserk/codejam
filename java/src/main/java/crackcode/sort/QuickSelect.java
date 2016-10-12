package crackcode.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 10. 12..
 */
public class QuickSelect {

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


    @Test
    public void test(){
        int[] a2 = {9,2,5,4,8,7,0,1,4,6,78,8};
        assertEquals(6, quickselect(a2, a2.length/2));
        Arrays.sort(a2);
        System.out.println(Arrays.toString(a2));
    }
}
