package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReachNumber_754 {
    public int reachNumber(int target) {
        if (target == 0) {
            return 0;
        }
        long t = target;
        if (target < 0) {
            t = -t;
        }
        int step = 0;
        long pos = 0;
        long unit = 1;

        while (pos < t) {
            pos += unit;
            unit++;
            step++;
        }

        if (pos == t) {
            return step;
        }

        // before
        long prevBest = t - (pos - (unit - 1));
        long diff = t - prevBest;
        long ret = step - 1 + diff * 2;

        // after
        while ((pos - t) % 2 != 0) {
            pos += unit;
            unit++;
            step++;
        }
        ret = Math.min(ret, step);

        return (int) ret;
    }

    @Test(timeout = 1000)
    public void test() {
        assertEquals(5, reachNumber(5));
        assertEquals(15, reachNumber(100));
        assertEquals(2, reachNumber(3));
        assertEquals(3, reachNumber(2));
    }
}
