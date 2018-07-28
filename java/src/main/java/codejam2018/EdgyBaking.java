package codejam2018;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 5/17/17.
 */

public class EdgyBaking {


    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int P = in.nextInt();

            int[][] rect = new int[N][2];
            for (int j = 0; j < N; j++) {
                rect[j][0] = in.nextInt();
                rect[j][1] = in.nextInt();
            }

            double r = solve(P, rect);

            System.out.println(String.format("Case #%d: %.6f", i, r));
        }
    }

    private static double solve(int P, int[][] rect) {
        double cur = 0;
        double max = 0;
        for (int i = 0; i < rect.length; i++) {
            cur += rect[i][0] * 2 + rect[i][1] * 2;
        }
        if (cur == (double) P) return cur;

        ArrayList<double[]> base = new ArrayList<>(); //use tree

        for (int i = 0; i < rect.length; i++) {
            double[] v = new double[2];
            v[0] = Math.min(rect[i][0], rect[i][1]) * 2.0;
            v[1] = Math.sqrt(rect[i][0] * rect[i][0] + rect[i][1] * rect[i][1]) * 2.0;
            if (cur + v[0] > P) continue;
            if (cur + v[1] >= P) return P;
            max = Math.max(max, v[1] + cur);

            for (int j = 0; j < base.size(); j++) {
                double[] next = new double[2];
                next[0] = base.get(j)[0] + v[0];
                next[1] = base.get(j)[1] + v[1];
                if (next[0] + cur > P) continue;
                if (next[1] + cur >= P) return P;
                max = Math.max(max, next[1] + cur);
                base.add(next);
            }
            base.add(v);
        }

        return max;
    }

    @Test
    public void test() {
        int[][] m = new int[][]{
                {1, 1}
        };
        assertEquals(6.828427, solve(7, m), 0.000001);

        int[][] m2 = new int[][]{
                {10, 20},
                {20, 30},
                {30, 10}
        };
        assertEquals(240, solve(240, m2), 0.000001);

        int[][] m3 = new int[][]{
                {7, 4
                }
        };
        assertEquals(32, solve(32, m3), 0.000001);
    }


}
