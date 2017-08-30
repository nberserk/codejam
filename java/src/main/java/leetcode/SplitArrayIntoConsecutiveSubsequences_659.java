package leetcode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SplitArrayIntoConsecutiveSubsequences_659 {

    boolean possible(List<Integer> chunk){
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();


        int prev_count=0;
        for (int i = 0; i < chunk.size(); i++) {
            int count = chunk.get(i);
            if ( count > prev_count ){
                for (int j = 0; j < count-prev_count; j++) {
                    start.add(i);
                }
            }else if (count < prev_count){
                for (int j = 0; j < -count+prev_count; j++) {
                    end.add(i - 1);
                }
            }
            prev_count = count;
        }

        for (int j = 0; j < chunk.get(chunk.size()-1); j++) {
            end.add(chunk.size()-1);
        }

        for (int i = 0; i < start.size(); i++) {
            if(end.get(i)-start.get(i)<2)
                return false;
        }

        return true;
    }

    public boolean isPossible(int[] nums) {
        int N = nums.length;
        if(N==0) return false;

        LinkedHashMap<Integer, Integer> freq = new LinkedHashMap<>();
        for (int v: nums){
            int f = freq.getOrDefault(v, 0);
            freq.put(v, f+1);
        }

        int prev = -1;
        List<Integer> chunk = new ArrayList<>();
        for(int key: freq.keySet()){
            //System.out.println(key);
            if(prev==-1 || prev+1==key){
                chunk.add(freq.get(key));
            }else {
                if (!possible(chunk))
                    return false;
                chunk.clear();
            }
            prev=key;
        }


        return possible(chunk);
    }

    @org.junit.Test
    public void test(){
        assertEquals(true, isPossible(new int[]{1,2,3,3,4,5}));
        assertEquals(true, isPossible(new int[]{1,2,3,3,4,4,5,5}));
        assertEquals(false, isPossible(new int[]{1,2,3,4,4,5}));
    }
}
