package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class LC1762 {
    public int[] findBuildings(int[] heights) {
        List<Integer> bldg = new ArrayList<>();
        bldg.add(heights.length -1);

        int max=heights[heights.length-1];
        for(int i=heights.length-2;i>=0;i--){
            if(heights[i]>max){
                bldg.add(i);
            }
            max=Math.max(max, heights[i]);
        }

        bldg.sort( (a,b) -> a-b);

        int[] r = new int[bldg.size()];
        for(int i=0;i<r.length;i++){
            r[i] = bldg.get(i);
        }
        return r;
    }

    @Test
    public void test(){

        Assert.assertArrayEquals(new int[]{0,2,3}, findBuildings(new int[]{4,2,3,1}));
        Assert.assertArrayEquals(new int[]{0,1,2,3}, findBuildings(new int[]{4,3,2,1}));
        Assert.assertArrayEquals(new int[]{3}, findBuildings(new int[]{1,3,2,4}));
        Assert.assertArrayEquals(new int[]{3}, findBuildings(new int[]{2,2,2,2}));


    }
}
