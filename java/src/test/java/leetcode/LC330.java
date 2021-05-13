package leetcode;

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class LC330 {

    public int minPatches(int[] nums, int n) {
        long miss=1;
        int i=0;
        int patch=0;
        while (miss<=n){
            if(i<nums.length && nums[i]<=miss){
                miss+=nums[i++];
            }else {
                miss += miss;
                patch++;
            }
        }

        return patch;
    }

    public int minPatches_1(int[] nums, int n) {

        TreeSet<Integer> set = new TreeSet<>();
        int N = nums.length;

        for (int cur : nums){
            TreeSet<Integer> temp = new TreeSet<>(set);
            set.add(cur);
            for (int i: temp){
                set.add(cur+i);
            }
        }

        long last=0;
        int patch=0;
        while(last<n){
            while ( set.contains((int)last+1)){
                last++;
            }
            if(last>=n) break;
            if(set.size()==last){
                while (last<n){
                    last=last*2+1;
                    patch++;
                }
                return patch;
            }

            TreeSet<Integer> temp = new TreeSet<>(set);
            set.add((int)last+1);
            for (int i: temp){
                set.add((int)last+1+i);
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
        assertEquals(28, minPatches(new int[]{1,2,31,33}, 2147483647));
        assertEquals(2, minPatches(new int[]{1,7,21,31,34,37,40,43,49,87,90,92,93,98,99}, 12));
        assertEquals(2, minPatches(new int[]{1,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,5,5,5,5,5,6,6,6,7,7,8,8,8,9,9,9,10,10,10,11,12,12,12,12,13,13,13,13,13,14,14,14,15,15,15,16,16,17,17,17,17,17,18,19,19,20,20,20,20,21,23,23,23,23,23,23,23,23,24,24,24,24,24,24,25,25,25,25,26,26,27,27,27,27,27,27,28,28,28,28,29,29,29,29,29,30,30,30,30,30,30,31,31,31,31,32,32,32,33,33,33,33,33,35,35,35,35,36,36,36,36,37,37,37,38,38,39,39,39,40,41,42,42,42,42,42,42,43,43,43,43,43,44,44,44,44,45,45,45,45,46,46,46,46,46,46,47,47,47,48,48,48,48,48,48,49,49,49,49,50,50,50,50,50,51,51,51,51,52,52,52,52,52,52,53,53,53,53,53,54,54,54,54,54,54,54,54,54,54,54,55,55,55,55,55,56,56,56,56,57,57,57,58,58,58,59,59,59,59,60,60,60,61,61,61,61,62,62,62,62,62,62,62,63,63,63,63,63,63,64,64,64,65,65,65,65,65,65,66,66,66,67,67,67,67,67,68,68,68,69,70,70,70,70,70,70,70,70,70,71,71,71,71,72,72,72,73,73,73,73,73,73,74,74,74,75,75,75,75,76,76,76,76,76,76,76,77,78,78,78,78,79,79,79,79,80,80,80,80,80,80,80,81,81,82,82,83,83,84,84,84,84,85,85,85,85,85,85,86,86,86,86,87,87,87,88,88,88,89,89,90,90,90,90,90,90,90,90,91,91,91,91,92,93,93,93,93,94,94,94,94,94,94,94,94,94,94,95,95,95,96,96,97,97,97,97,97,97,98,98,98,99,99,99,99,99,99,99,100,100,100,100,100}, 62842));



    }
}
