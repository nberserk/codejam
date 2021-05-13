package leetcode;

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class LC683 {

    public int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> tree = new TreeSet<>();
        int min =0;
        int max = 20001;
        tree.add(min);
        tree.add(max);

        for (int i = 0; i < flowers.length; i++) {
            int v = flowers[i];
            tree.add(v);
            int low = tree.lower(v);
            int high = tree.higher(v);

            if(high!=max && high-v==k+1){
                return i+1;
            }
            if(low!=min && v-low==k+1)
                return i+1;
        }

        return -1;
    }

    @org.junit.Test
    public void test(){

        assertEquals(2, kEmptySlots(new int[]{1,3,2}, 1));
        assertEquals(-1, kEmptySlots(new int[]{1,2,3}, 1));

    }
}
