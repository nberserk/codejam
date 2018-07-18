package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 3/16/17.
 */
public class SingleElementInSortedArray_540 {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0; // 0
        int hi = nums.length - 1; // 2
        while (lo < hi) {
            int m = (lo + hi) / 2; // 1
            if (nums[m] == nums[m + 1]) { // 0-m-1,
                if (m % 2 == 0)
                    lo = m + 2;
                else
                    hi = m - 1;
            } else if (nums[m] == nums[m - 1]) { // 0-m-2,
                if (m % 2 == 0)
                    hi = m - 2;
                else
                    lo = m + 1;
            } else {
                return nums[m];
            }
        }

        return nums[lo];
    }

    @Test
    public void test() {
        assertEquals(2, singleNonDuplicate(new int[] { 1, 1, 2 }));
        assertEquals(2, singleNonDuplicate(new int[] { 1, 1, 2, 3, 3, 4, 4, 8, 8 }));

    }
}
