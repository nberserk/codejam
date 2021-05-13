package leetcode;


import static org.junit.Assert.assertEquals;

public class LC413 {
    public int numberOfArithmeticSlices(int[] A) {
        int ret = 0;
        int diff = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < A.length - 1; i++) {
            int d = A[i + 1] - A[i];
            if (d == diff) {
                count++;
            } else {
                if (count >= 2) {
                    int org = count + 1;
                    //ret++;
                    for (int j = 3; j < count + 1; j++) {
                        ret += org - j + 1;
                    }
                }
                count = 0;
                diff = d;
            }
        }
        if (count >= 2) {
            int org = count + 1;
            //ret++;
            for (int j = 3; j <= count + 1; j++) {
                ret += org - j + 1;
            }
        }
        return ret;
    }

    @org.junit.Test
    public void test() {
        assertEquals(3, numberOfArithmeticSlices(new int[] {1, 2, 3, 4}));
    }
}
