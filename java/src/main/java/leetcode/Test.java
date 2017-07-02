package leetcode;

import static org.junit.Assert.assertEquals;

/**

 *
 */
public class Test {
    public boolean judgeSquareSum(int c) {
        int max = (int)Math.sqrt(c);

        for (int i = 1; i <= max; i++) {
            int r = c-i*i;
            int min = (int)Math.sqrt(r);
            if (r==min*min) return true;
        }

        return false;
    }

    @org.junit.Test
    public void test(){
        assertEquals(true, judgeSquareSum(5));
        assertEquals(false, judgeSquareSum(3));


    }
}
