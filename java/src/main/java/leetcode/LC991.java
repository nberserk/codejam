package leetcode;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LC991 {
    public int brokenCalc(int X, int Y) {
        if(X>=Y)
            return X-Y;
        if(Y%2==0)
            return 1+brokenCalc(X, Y/2);
        else
            return 1+brokenCalc(X,Y+1);
    }


    @Test
    public void test(){

        assertEquals(3, brokenCalc(3, 10));
        assertEquals(39, brokenCalc(1, 1000000000));
    }
}
