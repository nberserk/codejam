package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LC665 {
    public boolean checkPossibility(int[] nums) {
        int curMax=Integer.MIN_VALUE;
        boolean used=false;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                if(used) return false;
                if(curMax<=nums[i+1])
                    used=true;
                else{
                    nums[i+1]=nums[i];
                    used=true;
                }
            }
            curMax=Math.max(curMax, nums[i]);
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertEquals(true, checkPossibility(new int[]{2,3,1,5}));
        Assert.assertEquals(true, checkPossibility(new int[]{100,1,5,9}));
        Assert.assertEquals(true, checkPossibility(new int[]{4,2,3}));
    }
}
