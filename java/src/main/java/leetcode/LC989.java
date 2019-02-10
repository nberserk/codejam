package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC989 {

    public List<Integer> addToArrayForm(int[] A, int K) {
        int N=A.length;
        List<Integer> ret = new ArrayList<>();
        int carry=0;
        for(int i=N-1;i>=0;i--){
            int k = K%10;
            K/=10;
            k+=A[i]+carry;
            if(k>=10){
                carry=1;
                k-=10;
            }else carry=0;
            ret.add(k);
        }
        while(K>0){
            int k = K%10 + carry;
            K/=10;
            if(k>=10){
                carry=1;
                k-=10;
            }else carry=0;
            ret.add(k);
        }
        if(carry>0) ret.add(1);

        Collections.reverse(ret);
        return ret;
    }

    @Test
    public void test(){
//        List<Integer> out = addToArrayForm(new int[]{1,2,0,0}, 34);
//        Assert.assertEquals( 4, (int)out.get(3));

//        List<Integer> out = addToArrayForm(new int[]{0}, 34);
//        Assert.assertEquals( 4, (int)out.get(3));

        List<Integer> out = addToArrayForm(new int[]{6}, 809);
        Assert.assertEquals( 4, (int)out.get(3));

    }
}
