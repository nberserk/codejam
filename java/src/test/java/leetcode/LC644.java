package leetcode;

import static org.junit.Assert.assertEquals;

public class LC644 {

    public double findMaxAverage(int[] nums, int k) {
        int N = nums.length;

        double min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        for (int i:nums){
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        double error = Integer.MAX_VALUE;
        double prev = max;
        while (error > 0.0001){
            double mid = (min+max)*0.5;
            if(check(nums, mid, k))
                min = mid;
            else
                max = mid;
            error = Math.abs(mid-prev);
            prev = mid;
        }
        return min;
    }

    boolean check(int[] n, double avg, int k){
        double sum=0;
        for (int i = 0; i < k; i++) {
            sum += n[i]-avg;
        }
        if(sum>=0) return true;

        // ni .. nj
        // (ni-avg) + ... + (nj-avg)>=0
        // Si + ... Sj
        // Sj - Si >=0, any region could be. so we track min Si . that's the point
        double prev=0;
        double minSum=0;
        for (int i = k; i < n.length; i++) {
            sum += n[i]-avg;
            prev += n[i-k]-avg;
            minSum = Math.min(minSum, prev);
            if(sum>= minSum)
                return true;
        }

        return false;
    }


    @org.junit.Test
    public void test(){
        assertEquals(1.8, findMaxAverage(new int[]{0,0,3,2,4}, 5), 0.001);
        assertEquals(-1.0, findMaxAverage(new int[]{-1}, 1), 0.001);
        assertEquals(12.75, findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4), 0.001);
    }
}
