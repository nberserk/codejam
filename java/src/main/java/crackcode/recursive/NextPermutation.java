package main.java.crackcode.recursive;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 9/25/16.
 *
 * https://leetcode.com/problems/next-permutation/
 */
public class NextPermutation {
    void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    void reverse(int[] a, int start){
        int lo = start;
        int hi=a.length-1;
        while(lo<hi) swap(a, lo++, hi--);
    }

    void nextPermutation(int[] nums){
        int n = nums.length;
        if(n<=1) return;
        // find first pos nums[i-1]< nums[i] from the end
        int i=n-1;
        for (; i >=1; i--) {
            if( nums[i-1] < nums[i])
                break;
        }

        // find min but greater than nums[i-1]
        // we can use descending order property to find idx
        int j = nums.length-1;
        for (; j >i; j--) {
            if (nums[i-1]< nums[j]){
                break;
            }
        }
        swap(nums, i-1, j);

        // still descending order from nums[i] to the end
        reverse(nums, i);
    }

    @Test
    public void test(){
        int[] a = {4,1,3,2};

        nextPermutation(a);
        assertEquals("[4, 2, 1, 3]", Arrays.toString(a));

        nextPermutation(a);
        assertEquals("[4, 2, 3, 1]", Arrays.toString(a));

        nextPermutation(a);
        assertEquals("[4, 3, 1, 2]", Arrays.toString(a));
    }
}
