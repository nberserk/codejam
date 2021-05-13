package leetcode;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LC698 {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(k==1) return true;
        Arrays.sort(nums);
        int sum = 0;
        for (int i: nums){
            sum+=i;
        }
        if(sum%k!=0) return false;
        int target = sum/k;
        if(nums[nums.length-1]>target) return false;

        int[] partition = new int[k];
        return split(partition, nums, nums.length-1, target);
    }

    private boolean split(int[] partition, int[] nums, int start, int target) {
        if(start<0) return true;

        for (int i = 0; i < partition.length; i++) {
            int v = nums[start];
            if(partition[i]+v<=target){
                partition[i] += v;
                if(split(partition, nums, start-1, target))
                    return true;
                partition[i]-=v;
            }
        }

        return false;
    }

    @org.junit.Test
    public void test(){
        assertEquals(true, canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        assertEquals(true, canPartitionKSubsets(new int[]{4,5,3,2,5,5,5,1,5,5,5,5,5,5,5,5}, 14));
    }
}
