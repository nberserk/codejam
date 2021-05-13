package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class LC473 {




    public boolean makesquare(int[] nums) {
        long sum=0;
        for (int i:nums){
            sum+=i;
        }
        if(sum%4!=0 || nums.length<4) return false;

        long target = sum/4;
        for (int i:nums){
            if (i>target) return false;
        }

        //Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        return match(nums, used, target, 0, 0, 0);
    }

    private boolean match(int[] nums, boolean[] used, long target, int start, int count, int v) {
        if (count==4){
            // do not need to check this.
//            for(boolean b: used){
//                if(!b)
//                    return false;
//
//            }
            return true;
        }

        if(v==target){
            if(match(nums, used, target, 0, count+1, 0))
                return true;
        }else if(v>target) return false;
        else{
            for (int i = start; i < nums.length; i++) {
                if(used[i]) continue;
                used[i]=true;
                if(match(nums, used, target, i+1, count, v+nums[i]))
                    return true;
                used[i]=false;
            }
        }

        return false;
    }


//    private boolean match(int[] nums, long target, int start, int v) {
//        int N = nums.length;
//        if(start==0) return true;
//
//        long cur = v%target;
//        for (int i = start; i >0; i--) {
//            if(cur+nums[i]<=target){
//                swap(nums, start, i);
//                boolean matched = match(nums, target, start-1, v+nums[start]);
//                if(matched)return true;
//                swap(nums, start, i);
//            }
//        }
//        return false;
//    }

    @Test(timeout = 1000)
    public void test() {

        Assert.assertEquals(false, makesquare(new int[]{5,5,5,5,16,4,4,4,4,4,3,3,3,3,4}));
        Assert.assertEquals(true, makesquare(new int[]{5,5,5,5,4,4,4,4,3,3,3,3}));
        Assert.assertEquals(true, makesquare(new int[]{1,1,2,2,2}));
        Assert.assertEquals(false, makesquare(new int[]{3,3,3,3,4}));
        Assert.assertEquals(true, makesquare(new int[]{1,2,3,4,5,6,7,8,9,10,5}));
    }
}
