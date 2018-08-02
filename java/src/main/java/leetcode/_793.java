package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class _793 {
    int zCount(long n){
        int c=0;
        while(n>0){
            n/=5;
            c+=n;
        }
        return c;
    }
    public int preimageSizeFZF(int K) {
        long lo=0;
        long hi= 5_000_000_000L;
        while(lo<hi){
            long mid = (lo+hi)/2;
            int k=zCount(mid);
            if(k==K){
                hi=mid;
            }else if(k<K)
                lo=mid+1;
            else hi=mid-1;
        }
        if(zCount(lo)!=K) return 0;
        return 5;
    }

    @Test
    public void test() {
        assertEquals(0, preimageSizeFZF(5));
        assertEquals(5, preimageSizeFZF(1000000000));
    }
}
