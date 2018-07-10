package leetcode;


import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class TwoKeysKeyboard_650 {


    int find(int n, int len, int paste, int press) {
        if (n == len) {
            return press;
        }
        if (len > n) return Integer.MAX_VALUE;

        int ret = Integer.MAX_VALUE;
        if(len!=paste)
            ret = Math.min(ret, find(n, len, len, press + 1));

        if (paste > 0)
            ret = Math.min(ret, find(n, len + paste, paste, press + 1));

        return ret;
    }

    public int minSteps_n2(int n) {
        return find(n, 1, 0, 0);
    }

    public int minSteps(int n) {
        int step=0;
        int d=2;
        while(n>1){
            while(n%d==0){
                step+=d;
                n/=d;
            }
            d++;
        }
        return step;
    }


    @org.junit.Test(timeout = 1000)
    public void test(){
        assertEquals(0, minSteps(1));
        assertEquals(3, minSteps(3));
        assertEquals(7, minSteps(10));
        assertEquals(21, minSteps(1000));
        assertEquals(28, minSteps(10000));
    }

    @org.junit.Test(timeout = 1000)
    public void test_n2(){
        assertEquals(0, minSteps_n2(1));
        assertEquals(3, minSteps_n2(3));
        assertEquals(7, minSteps_n2(10));
        assertEquals(21, minSteps_n2(1000));
        assertEquals(28, minSteps(10000));
    }
}
