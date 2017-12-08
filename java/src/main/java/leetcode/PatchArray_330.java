package leetcode;

import java.util.TreeSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatchArray_330 {

    public int minPatches(int[] nums, int n) {

        TreeSet<Integer> set = new TreeSet<>();
        int N = nums.length;

        for (int cur : nums){
            TreeSet<Integer> temp = new TreeSet<>(set);
            set.add(cur);
            for (int i: temp){
                set.add(cur+i);
            }
        }

        int cur=1;
        int patch=0;
        while(cur<=n){
            while ( set.contains(cur)){
                cur++;
            }
            if(set.size()+1==cur){
                while (cur<n){
                    cur+=cur+1;
                    patch++;
                }
                break;
            }
            if(cur>n) break;

            TreeSet<Integer> temp = new TreeSet<>(set);
            set.add(cur);
            for (int i: temp){
                set.add(cur+i);
            }
            patch++;
        }

        return patch;
    }


    @org.junit.Test
    public void test(){
        assertEquals(4, minPatches(new int[]{}, 8));
        assertEquals(1, minPatches(new int[]{1,3}, 6));
        assertEquals(2, minPatches(new int[]{1,5,10}, 20));
        assertEquals(0, minPatches(new int[]{1,2,2}, 5));
        assertEquals(44, minPatches(new int[]{1,2,31,33}, 2147483647));


    }
}
