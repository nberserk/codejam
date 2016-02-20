package main.java.codejam.lib;

import java.util.Arrays;

/**
 * Created by darren on 2/16/16.
 */
public class CountSort {

    static void countSort(int[] a){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }

        int count = max-min+1;
        int [] c = new int[count];
        for (int i = 0; i < a.length; i++) {
            c[a[i]-min] ++;
        }

        for (int i = 1; i < count; i++) {
            c[i] = c[i] + c[i-1];
        }

        int [] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[ c[a[i]-min]-1 ] = a[i];
            c[a[i]-min]--;
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = result[i];
        }

        //System.out.println(Arrays.toString(result));
    }

    public static void main(String[] args) {

        int[] a = new int[] {9,4,10,8,2,4};
        CountSort.countSort( a );
        System.out.println(Arrays.toString(a));

    }
}
