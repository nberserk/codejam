package leetcode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LC659 {
    boolean split2(List<Integer> f){
        List<Integer> open = new ArrayList<>();
        List<Integer> close = new ArrayList<>();

        int prev=0;
        int t;
        for(int i=0;i<f.size();i++){
            if(f.get(i) ==prev) {
            }
            else if(f.get(i)>prev){
                t=f.get(i)-prev;
                while(t>0){
                    open.add(i);
                    t--;
                }
            }else{
                t=-f.get(i)+prev;
                while(t>0){
                    close.add(i);
                    t--;
                }
            }
            prev=f.get(i);
        }
        t=f.get(f.size()-1);
        while(t>0){
            close.add(f.size()-1);
            t--;
        }

        if(open.size()!=close.size()) return false;
        for(int i=0;i<open.size();i++){
            if(close.get(i)-open.get(i)<2) return false;
        }
        return true;
    }
    public boolean isPossible2(int[] nums) {
        ArrayList<Integer> f = new ArrayList<>();
        int prev=nums[0];
        int count=1;
        for(int i=1;i<nums.length;i++){
            if(prev==nums[i])
                count++;
            else if(prev+1==nums[i]){
                f.add(count);
                count=1;
            }else{
                if(!split2(f)) return false;
                f.clear();
                count=1;
            }
            prev=nums[i];
        }
        f.add(count);
        if(!split2(f)) return false;
        return true;
    }

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
        assertEquals(false, isPossible2(new int[]{2,5,5,5,6,7,8,8,8,9}));

        assertEquals(true, isPossible(new int[]{1,2,3,3,4,5}));
        assertEquals(true, isPossible(new int[]{1,2,3,3,4,4,5,5}));
        assertEquals(false, isPossible(new int[]{1,2,3,4,4,5}));
    }
}
