package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 16/19/2017.
 *
 *

 Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one)
 and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|.
 Your task is to find the maximum distance.

 Example 1:
 Input:
 [[1,2,3],
 [4,5],
 [1,2,3]]
 Output: 4

 Explanation:
    One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
 Note:
     Each given array will have at least 1 number. There will be at least two non-empty arrays.
     The total number of the integers in all the m arrays will be in the range of [2, 10000].
     The integers in the m arrays will be in the range of [-10000, 10000].

 */
public class MaximumDistanceInArrays_624 {

    public int maxDistance(int[][] arrays) {
        int max = Integer.MIN_VALUE;

        int curmin = arrays[0][0];
        int curmax = arrays[0][arrays[0].length-1 ];
        for (int i = 1; i < arrays.length; i++) {
            int head = arrays[i][0];
            int tail = arrays[i][arrays[i].length-1];

            max = Math.max(max, Math.abs(tail-curmin));
            max = Math.max(max, Math.abs(head-curmax));

            curmin = Math.min(curmin, head);
            curmax = Math.max(curmax, tail);
        }
        return max;
    }



    @Test
    public void test(){
        int[][] m = {{1,2,3}, {4,5}, {1,2,3}};
        assertEquals(4, maxDistance(m));
    }
}
