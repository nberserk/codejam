package main.java.crackcode.binarysearch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/1/16.
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * idea:
 * M : length of first sorted array
 * N : length of second sorted array
 * Let's divide array like this
 * A[0] - A[i-1] | A[i] - A[M-1]
 * B[0] - B[j-1] | B[j] - B[N-1]
 *
 * where    i+j = (M+N)/2, j = (M+N)/2 - i
 *          A[i-1] <= B[j]
 *          B[j-1] <= A[i]
 *
 *          half = (M+N)/2
 *          M>N
 * so binary search with i
 *      when
 *
 * median is
 * when odd, Min(A[i], B[j])
 * when even, { Max(A[i-1], B[j-1]) + MIN(A[i], B[j]) } /2
 *
 */
public class MedianOfTwoSortedArray {

    double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int M = nums1.length;
        int N = nums2.length;
        if(M>N) return findMedianSortedArrays(nums2, nums1);
        int half = (M+N)/2; // why ?
        boolean odd = (M+N)%2==1;
        int lo = 0;
        int hi = M;
        while(lo<=hi){
            int i = (lo+hi)/2;
            int j = half-i;

            /*
            if( (i==0 || j==N || nums1[i-1]<= nums2[j])
                    && ( i==M || j==0 || nums2[j-1]<= nums1[i]) ){
                // found answer

            }else
            */if (i>0 && j<N  && nums1[i-1]>nums2[j])
                hi=i-1;
            else if (j>0 && i<M && nums2[j-1]> nums1[i])
                lo=i+1;
            else{
                int min = Integer.MAX_VALUE;
                int max=Integer.MIN_VALUE;
                if(odd){
                    if(i==M)
                        min = nums2[j];
                    else if(j==N)
                        min = nums1[i];
                    else min = Math.min(nums1[i], nums2[j]);
                    return min;
                }else{
                    if(i==0)
                        max = nums2[j-1];
                    else if(j==0)
                        max = nums1[i-1];
                    else max = Math.max(nums1[i - 1], nums2[j - 1]);

                    if(i==M)
                        min = nums2[j];
                    else if(j==N)
                        min = nums1[i];
                    else min = Math.min(nums1[i], nums2[j]);

                    return (min+max)/2.0;
                }
            }
        }

        return 0.0;
    }


    @Test
    public void test(){
        int[] a = {1,3};
        int[] b = {2};
        assertEquals(2.0, findMedianSortedArrays(a, b), 0.0000001);
        int[] a2 = {1,2};
        int[] b2 = {3,4};
        assertEquals(2.5, findMedianSortedArrays(a2, b2), 0.0000001);

        int[] a3 = {2,3,4,5};
        int[] b3 = {1};
        assertEquals(3.0, findMedianSortedArrays(a3, b3), 0.0000001);

        int[] a4 = {};
        int[] b4 = {1};
        assertEquals(1.0, findMedianSortedArrays(a4, b4), 0.0000001);
    }
}
