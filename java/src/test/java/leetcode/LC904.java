package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 *
 */
public class LC904 {
    public int totalFruit(int[] tree) {
        int max=0;

        HashMap<Integer,Integer> map = new HashMap<>();
        int start=0;
        for(int i=0;i<tree.length;i++){
            map.put(tree[i], i);
            if(map.size()>2){
                int min=Integer.MAX_VALUE;
                int minKey=0;
                for(int t : map.keySet()){
                    if(map.get(t)<min){
                        min=map.get(t);
                        minKey=t;
                    }
                }
                start=min+1;
                map.remove(minKey);
            }
            max=Math.max(max, i-start+1);
        }

        return max;
    }

    @Test
    public void test(){
        Assert.assertEquals(5, totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4}));

    }
}
