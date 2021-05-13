package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class LC491 {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();

        LinkedList<Integer> cur = new LinkedList<>();
        find(ret, nums, 0, cur);

        return ret;
    }

    private void find(List<List<Integer>> ret, int[] nums, int start, LinkedList<Integer> cur) {
        int N = nums.length;

        if (cur.size()>1)
            ret.add(new ArrayList<>(cur));

        HashSet<Integer> used = new HashSet<>();
        for (int j = start; j < N; j++) {
            if (used.contains(nums[j])) continue;
            if ( cur.size()==0 || nums[j]>=cur.getLast() ){
                used.add(nums[j]);
                cur.addLast(nums[j]);
                find(ret, nums, j+1, cur);
                cur.removeLast();
            }
        }

    }


    @org.junit.Test
    public void test(){
        assertEquals("[[4, 6], [4, 6, 7], [4, 6, 7, 7], [4, 7], [4, 7, 7], [6, 7], [6, 7, 7], [7, 7]]", findSubsequences( new int[]{4, 6, 7, 7}).toString());
    }
}
