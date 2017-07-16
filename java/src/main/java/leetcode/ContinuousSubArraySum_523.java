package leetcode;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;


public class ContinuousSubArraySum_523 {

    public boolean checkSubarraySum(int[] nums, int k) {

        int sum=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
            if(k!=0)
                sum %=k;
            Integer prev = map.get(sum);
            if(prev==null){
                map.put(sum, i);
            }else{
                if (i-prev>1)
                    return true;
            }
        }
        return false;
    }


    @org.junit.Test
    public void test(){
        assertEquals(true, checkSubarraySum(new int[]{0,0}, 0));
        assertEquals(false, checkSubarraySum(new int[]{0,1,0}, 0));
    }
}
