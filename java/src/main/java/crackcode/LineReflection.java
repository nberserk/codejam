package main.java.crackcode;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by darren on 9/17/16.
 *
 * from: https://leetcode.com/problems/line-reflection/
 */
public class LineReflection extends TestCase{
    public boolean isReflected(int[][] points) {
        if (points == null || points.length == 0) return true;
        int n = points.length;

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] m, int[] o) {
                if (m[0] == o[0])
                    return m[1] - o[1];
                return m[0] - o[0];
            }
        });
        int minx = points[0][0];
        int maxx = points[n - 1][0];

        double midx = (minx + maxx)/2.0;

        int lo = 0;
        int hi = n - 1;
        while (lo < hi) {
            if (points[lo][1] != points[hi][1])
                return false;
            if ((points[lo][0] + points[hi][0])/2 != midx )
                return false;
            while (lo < hi && points[lo][0] == points[lo + 1][0] && points[lo][1] == points[lo + 1][1])
                lo++;

            while (lo < hi && points[hi][0] == points[hi - 1][0] && points[hi][1] == points[hi - 1][1])
                hi--;
            lo++;
            hi--;
        }
        if (lo == hi && points[lo][0] != midx)
            return false;

        return true;
    }


    public void test(){
        int[][] points = {{-16,1},{16,1},{16,1}};

        //assertEquals(true, isReflected(points));

        int[][] p2 = {{0,0},{1,0},{3,0}};
        assertEquals(false, isReflected(p2));
    }
}
