package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class LC992 {

    /**
     *
     * a0,      a1, a2, a3,     a4
     * startK     ,   , startK1, right
     * count of subarray ending at a3 = startK-startK1 = 3 = {a0-a4, a1-a4, a2-a4}
     *
     */
    public int subarraysWithKDistinct(int[] A, int K) {

        int startK=0;
        int startK1=0;
        HashMap<Integer, Integer> mapK = new HashMap<>();
        HashMap<Integer, Integer> mapK1 = new HashMap<>();
        int ret=0;
        for (int right = 0; right < A.length; right++) {
            mapK.put(A[right], right);
            mapK1.put(A[right], right);
            if(mapK.size()>K){
                while(startK!=mapK.get(A[startK]))
                    startK++;
                mapK.remove(A[startK]);
                startK++;
            }

            if(mapK1.size()>K-1){
                // remove
                while(startK1!=mapK1.get(A[startK1]))
                    startK1++;
                mapK1.remove(A[startK1]);
                startK1++;
            }

            ret += startK1-startK;
        }
        return ret;
    }


    @Test
    public void test(){
        Assert.assertEquals(7, subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2));
        Assert.assertEquals(3, subarraysWithKDistinct(new int[]{1,2,1,3,4}, 3));
    }
}
