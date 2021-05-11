package leetcode;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ConvexPolygon_469 {

    int ccw(int[] p, int[] q, int[] i){
        int qx = q[0] - p[0];
        int qy = q[1] - p[1];
        int ix = i[0] - p[0];
        int iy = i[1] - p[1];

        return qx*iy-qy*ix;
    }

    public boolean isConvex(List<List<Integer>> points) {
        int R = points.size();
        int[][] p = new int[R][2];
        int idx=0;
        for (List<Integer> l: points){
            p[idx][0] = l.get(0);
            p[idx++][1] = l.get(1);
        }

        return convex(p);
    }

    boolean convex(final int[][] points) {
        int N = points.length;

        boolean gotNegative = false;
        boolean gotPositive = false;
        int prev=0;
        for (int i = 0; i < N; i++) {
            int p = i==0?N-1:i-1;
            int q = i;
            int r = i==N-1?0:i+1;

            int v =ccw(points[p], points[q], points[r]);
            if (v < 0) {
                gotNegative = true;
            }
            else if (v > 0) {
                gotPositive = true;
            }
            if (gotNegative && gotPositive) return false;
        }

        return true;
    }


    @Test
    public void test(){

//        assertEquals(false, convex(
//                new int[][]{{1,0}, {0,1}, {-1,0},{0,-1},{0,0}}));
//
//        assertEquals(true, isConvex(
//                List.of(List.of(0,0), List.of(0,1), List.of(1,1), List.of(1,0))));
//        assertEquals(true, convex(
//                new int[][]{{0,0}, {1,0}, {1,1},{-1,1},{-1,0}}));

    }
}
