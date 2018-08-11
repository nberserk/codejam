package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC035 {
    public int searchInsert(int[] nums, int target) {
        int lo=0;
        int hi=nums.length-1;
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            if(nums[mid]==target) return mid;
            else if(nums[mid]>target)
                hi=mid-1;
            else lo=mid+1;
        }
        return lo;
    }

    @Test
    public void test(){
        assertEquals(0, searchInsert(new int[]{1,3,5,7}, 0));
        assertEquals(4, searchInsert(new int[]{1,3,5,7}, 9));
        assertEquals(2, searchInsert(new int[]{1,3,5,7}, 5));
    }
}
