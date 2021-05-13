package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LC668 {
    int m,n;
    int smaller(int target){
        int k=0;
        int same=0;
        for(int r=1;r<=m;r++){
            int th = target/r;
            if(r*th==target) same++;
            th=Math.min(th, n);
            k+=th;
        }
        return k;
    }

    public int findKthNumber(int m, int n, int k) {
        this.m=m;
        this.n=n;
        int lo=1;
        int hi=m*n;
        while(lo<hi){
            int mid = (lo+hi)/2;
            int range = smaller(mid);
            if(range>=k)
                hi=mid;
            else
                lo=mid+1;
        }
        return lo;
    }

    @Test
    public void test2(){
        assertEquals(312, findKthNumber(45, 12, 471));
    }
}
