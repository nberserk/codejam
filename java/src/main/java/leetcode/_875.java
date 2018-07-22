package leetcode;


import static org.junit.Assert.assertEquals;

public class _875 {

    boolean possible(int[] p, int H, int K) {
        for (int i = 0; i < p.length; i++) {
            H -= p[i] / K;
            if (p[i] % K > 0) H--;
            if (H < 0) return false;
        }
        return true;
    }

    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1;
        int hi = Integer.MAX_VALUE - 10000;
        while (hi - lo > 1) {
            int mid = (lo + hi + 1) / 2;

            if (possible(piles, H, mid)) {
                hi = mid;
            } else
                lo = mid;
        }
        if (possible(piles, H, lo)) return lo;

        return hi;
    }

    @org.junit.Test
    public void test() {
        assertEquals(4, minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        assertEquals(30, minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
    }
}
