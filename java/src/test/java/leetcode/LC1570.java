package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LC1570 {
    Map<Integer,Integer> map = new HashMap<>();

    public void init(int[] nums) {
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0) continue;
            map.put(i, nums[i]);
        }
    }

    public int getValue(int pos){
        if(!map.containsKey(pos))
            return 0;
        return map.get(pos);
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(LC1570 vec) {
        if(map.size()>vec.map.size()){
            return vec.dotProduct(this);
        }

        int product = 0;
        for(Integer key:map.keySet()){
            product += map.get(key)*vec.map.getOrDefault(key, 0);
        }
        return product;
    }

    @Test
    public void test(){
        LC1570 v1 = new LC1570(); v1.init(new int[]{1,0,0,2,3});
        LC1570 v2 = new LC1570(); v2.init(new int[]{0,3,0,4,0});
        Assert.assertEquals(8, v1.dotProduct(v2));
    }
}
