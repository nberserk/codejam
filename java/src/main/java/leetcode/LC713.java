package leetcode;

import static org.junit.Assert.assertEquals;

public class SubArrayProductLessThanK_713 {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int N = nums.length;
        if(N==0 ) return 0;

        int product = 1;
        int left=0;

        int count=0;

        for (int right = 0; right < N; right++) {
            product*=nums[right];
            while(left<=right&&product>=k) product/=nums[left++];

            count+=right-left+1;
        }

        return count;
    }

    @org.junit.Test
    public void test(){
        assertEquals(0, numSubarrayProductLessThanK(new int[]{1,2,3}, 0));
        assertEquals(0, numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 1));
        assertEquals(8, numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
        assertEquals(3, numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 10));

    }
}
