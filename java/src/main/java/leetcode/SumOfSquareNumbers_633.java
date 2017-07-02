package leetcode;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**

 *
 */
public class SumOfSquareNumbers_633 {
    public boolean judgeSquareSum(int c) {
        HashSet<Integer> set = new HashSet<>();



        int max = (int)Math.sqrt(c);
        for (int i = 1; i <= max; i++) {
            int r = c -i*i;
            if(set.contains(r)) return true;
            set.add(i*i);
        }

        return false;
    }
    public boolean judgeSquareSum_naive(int c) {
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
        assertEquals(true, judgeSquareSum_naive(5));
        assertEquals(false, judgeSquareSum_naive(3));

        assertEquals(true, judgeSquareSum(5));
        assertEquals(false, judgeSquareSum(3));
    }
}
