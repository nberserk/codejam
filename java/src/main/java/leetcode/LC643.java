package leetcode;

import static org.junit.Assert.assertEquals;


public class MaximumAverageSubArray_643 {

    public double findMaxAverage(int[] nums, int k) {
        int N = nums.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        double max = (double)sum/k;
        int left = 0;
        int right = k;
        while(true){
            if(right==N-1) break;
            sum -= nums[left++];
            sum += nums[right++];
            max = Math.max(max, (double)sum/k);
        }

        return max;
    }


    @org.junit.Test
    public void test(){
        assertEquals(12.75f, findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4), 0.0001);
    }
}
