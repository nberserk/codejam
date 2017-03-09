package main.java.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 3/9/17.
 *
 * https://leetcode.com/problems/super-washing-machines/
 *
 */
public class SuperWashingMachines_517 {

    public int findMinMoves(int[] machines) {

        int N = machines.length;
        int[] sum = new int[N];
        sum[0]=machines[0];
        for(int i=1;i<N;i++){
            sum[i]=machines[i]+sum[i-1];
        }
        if(sum[N-1]%N!=0) return -1;
        int avg = (int)(sum[N-1]/N);
        int ret=0;

        for(int i=0;i<N;i++){
            int l=0;
            int r=0;
            if(i!=0)
                l = (i)*avg - sum[i-1];
            if(i!=N-1)
                r = (N-1-i)*avg - (sum[N-1]-sum[i]);
            if(l>0&&r>0)
                ret=Math.max(ret, l+r );
            else
                ret=Math.max(ret, Math.max(Math.abs(l), Math.abs(r) ) );
        }

        return ret;
    }

    @Test
    public void test(){
        assertEquals(3, findMinMoves(new int[]{1, 0,5}));
        assertEquals(4, findMinMoves(new int[]{4, 4, 0, 0}));
        assertEquals(2, findMinMoves(new int[]{4, 0, 4, 0}));
    }
}
