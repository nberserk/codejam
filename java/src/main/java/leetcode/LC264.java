package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 *
 *
 */
public class LC264 {

    public int nthUglyNumber(int n) {
        List<Integer> ret = new ArrayList<>();
        ret.add(1);
        int i2=0;
        int i3=0;
        int i5=0;
        while(ret.size()<n){
            int next = Math.min(ret.get(i2)*2, Math.min(ret.get(i3)*3,ret.get(i5)*5) );
            ret.add(next);
            if(next==ret.get(i2)*2) i2++;
            if(next==ret.get(i3)*3) i3++;
            if(next==ret.get(i5)*5) i5++;
        }
        return ret.get(n-1);
    }

    @Test
    public void test(){
        assertEquals(36, nthUglyNumber(20));
    }
}
