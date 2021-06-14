package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Find the kth positive integer that is missing from this array.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * Example 2:
 *
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 */
public class LC1539 {
    // find max index , which satisfying a[i]-(i+1) <=k
    public int findKthPositive(int[] arr, int k) {
        int lo = 0; //lo=0
        int hi = arr.length-1; // 3
        while(lo<hi){
            int mid = (lo+hi+1)/2; // 3
            int midK = arr[mid]-(mid+1); // 0
            if(midK>k){
                hi=mid-1;//hi=3
            }else if(midK<k){
                lo=mid;  // lo=3
            }else{
                System.out.println("possible???");
                break;
            }
        }

        int curK = arr[lo]-(lo+1); //7-4=3
        return arr[lo] + (k-curK);
    }

    @Test
    public void test(){
        Assert.assertEquals(9, findKthPositive(new int[]{2,3,4,7,11}, 5));
        Assert.assertEquals(6, findKthPositive(new int[]{1,2,3,4}, 2));
    }
}
