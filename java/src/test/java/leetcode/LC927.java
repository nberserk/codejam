package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC927 {
    public int[] threeEqualParts(int[] A) {
        int[] ret = new int[]{-1,-1};

        List<Integer> one = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i]==1) one.add(i);
        }
        if (one.size()%3!=0) return ret;
        if(one.size()==0){
            return new int[]{0, A.length-1};
        }

        int offset=one.size()/3;
        int[] p = new int[3];
        for (int i = 0; i < one.size() / 3; i++) {
            if (i!=0){
                int d = one.get(i)-p[0];
                int d2 = one.get(i+offset)-p[1];
                int d3 = one.get(i+offset*2)-p[2];
                if(d!=d2 || d2!=d3)
                    return ret;
            }

            p[0] = one.get(i);
            p[1] = one.get(i+offset);
            p[2] = one.get(i+offset*2);
        }

        // if last has 0,
        if (A[A.length-1]==0)
            return ret;

        ret[0] = one.get(offset-1);
        ret[1] = one.get(offset*2-1)+1;
        return ret;
    }

    @Test
    public void test(){
        Assert.assertArrayEquals(new int[]{-1,-1}, threeEqualParts(new int[]{1,0,1,1,0}));

        Assert.assertArrayEquals(new int[]{0,3}, threeEqualParts(new int[]{1,0,1,0,1}));
        Assert.assertArrayEquals(new int[]{2,7}, threeEqualParts(new int[]{1,0,1,0,1,0,1,0,1,0,1}));
        Assert.assertArrayEquals(new int[]{-1,-1}, threeEqualParts(new int[]{1,1,0,1,1}));
    }
}
