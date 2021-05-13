package leetcode;


import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC435 {
    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }


    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) return 0; 
        Arrays.sort(intervals, (a, b) -> a.end - b.end);

        int end = intervals[0].end;
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                count++;
                end = intervals[i].end;
            }
        }

        return intervals.length - count;
    }


    @org.junit.Test(timeout = 1000)
    public void test() {
        Interval[] inter = new Interval[4];
        inter[0] = new Interval(1, 2);
        inter[1] = new Interval(2, 3);
        inter[2] = new Interval(3, 4);
        inter[3] = new Interval(1, 3);

        assertEquals(1, eraseOverlapIntervals(inter));

        Interval[] inter2 = new Interval[3];
        inter2[0] = new Interval(1, 2);
        inter2[1] = new Interval(1, 2);
        inter2[2] = new Interval(1, 2);
        assertEquals(2, eraseOverlapIntervals(inter2));


    }

}
