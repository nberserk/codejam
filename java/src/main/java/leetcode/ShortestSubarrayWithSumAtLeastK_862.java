package leetcode;

import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class ShortestSubarrayWithSumAtLeastK_862 {

    public int shortestSubarray(int[] A, int K) {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < A.length; i++) {
            if(A[i]>=K) return 1;
            int target = K-A[i];
            Integer val = tree.get(target);
            if(val!=null){
                ret = Math.min(ret, i-val+1);
            }
            Integer key = tree.higherKey(target);
            while (key!=null){
                ret = Math.min(ret,i-tree.get(key)+1);
                key = tree.higherKey(key);
            }

            // add
            tree.clear();
            int v =0;
            for (int j = i; j >=0 ; j--) {
                v+=A[j];
                tree.put(v, j);
            }
        }

        if (ret==Integer.MAX_VALUE) ret =-1;
        return ret;
    }


    @org.junit.Test
    public void test(){

        assertEquals(9, shortestSubarray(new int[]{31,63,-38,43,65,74,90,-23,45,22}, 341));
        assertEquals(1, shortestSubarray(new int[]{45,95,97,-34,-42}, 21));

        assertEquals(3, shortestSubarray(new int[]{84,-37,32,40,95}, 167));
        assertEquals(1, shortestSubarray(new int[]{77,19,35,10,-14}, 19));
        assertEquals(3, shortestSubarray(new int[]{2,-1,2}, 3));
        assertEquals(-1, shortestSubarray(new int[]{1,2}, 4));
        assertEquals(1, shortestSubarray(new int[]{1}, 1));
    }
}
