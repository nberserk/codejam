package leetcode.common;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class JUnitHelper {

    public static boolean assert2DArrayEquals(int[][] expected, int[][] actual){
        int r1 = expected.length;
        int r2 = actual.length;
        assertEquals(r1,r2);
        for (int i = 0; i < r1; i++) {
            Assert.assertArrayEquals(expected[i], actual[i]);
        }
        return true;
    }
}
