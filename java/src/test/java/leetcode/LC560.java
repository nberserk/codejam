package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;


public class LC560 {

    public int subarraySum(int[] nums, int k) {
        int[] sumArray= new int[nums.length+1];
        int sum=0;
        sumArray[0]=0;
        for(int i=1;i<sumArray.length;i++){
            sum+=nums[i-1];
            sumArray[i]=sum;
        }

        int count =0;
        Map<Integer,Integer> sumFrequency = new HashMap<>();
        for(int i=0;i<sumArray.length;i++){
            int targetSum = -k + sumArray[i];
            if(sumFrequency.containsKey(targetSum)){
                count+=sumFrequency.get(targetSum);
            }

            // update sumFrequency
            sumFrequency.putIfAbsent(sumArray[i],0);
            sumFrequency.put(sumArray[i], sumFrequency.get(sumArray[i])+1);
        }
        return count;
    }

    public int subarraySum_Old(int[] nums, int k) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int N = nums.length;
        int[] s = new int[N];
        s[0] = nums[0];
        for (int i=1;i<N;i++ ) {
            s[i] = s[i - 1] + nums[i];
        }

        map.put(0, 1);
        int ret = 0;
        for (int i=0;i<N;i++){
            int t = s[i] - k;
            if (map.containsKey(t))
                ret += map.get(t);
            map.putIfAbsent(s[i], 0);
            map.put(s[i], map.get(s[i]) + 1);
        }
        return ret;
    }

    @org.junit.Test
    public void test(){

        assertEquals(2, subarraySum(new int[]{1,1,1}, 2));
    }
}
