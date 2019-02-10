package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class LC992 {
    int count(int start, int min){
        int sum=1;
        int mul = 2;
        for(int i=min-1;i>=start;i--){
            sum +=mul;
            mul*=2;
        }
        return sum;
    }
    public int subarraysWithKDistinct(int[] A, int K) {
        if(K==1) return A.length;
        int start=0;
        int ret=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], i);
            if(map.size()>K){
                // remove
                int minKey=0;
                int min=Integer.MAX_VALUE;
                for (int k: map.keySet()){
                    int v = map.get(k);
                    if(v<min){
                        min=v;
                        minKey=k;
                    }
                }
                ret += count(start, min);
                map.remove(minKey);
                start=min+1;
            }
        }
        if(start!=A.length && map.size()==K){
            int minKey=0;
            int min=Integer.MAX_VALUE;
            for (int k: map.keySet()){
                int v = map.get(k);
                if(v<min){
                    min=v;
                    minKey=k;
                }
            }
            ret+=count(start, min);
        }
        return ret;
    }


    @Test
    public void test(){

        Assert.assertEquals(7, subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2));
        Assert.assertEquals(3, subarraysWithKDistinct(new int[]{1,2,1,3,4}, 3));
    }
}
