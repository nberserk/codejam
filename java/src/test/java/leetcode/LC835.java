package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LC835 {
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        int[] a = new int[N];
        int[] b = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                b[i] |= B[i][j] << j;
                a[i] |= A[i][j] << j;
            }
        }
        // right down
        int ret = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int c = 0;
                int c2 = 0;
                for (int k = 0; k < N; k++) {
                    if (k - i < 0) continue;
                    int t = b[k] & (a[k - i] >> j);
                    int t2 = b[k] & (a[k - i] << j);
                    while (t > 0) {
                        if ((t & 1) == 1) c++;
                        t = t >> 1;
                    }
                    while (t2 > 0) {
                        if ((t2 & 1) == 1) c2++;
                        t2 = t2 >> 1;
                    }
                }
                ret = Math.max(ret, c);
                ret = Math.max(ret, c2);
                c = c2 = 0;
                for (int k = 0; k < N; k++) {
                    if (k + i >= N) break; // up
                    int t = b[k] & (a[k + i] >> j);
                    int t2 = b[k] & (a[k + i] << j);
                    while (t > 0) {
                        if ((t & 1) == 1) c++;
                        t = t >> 1;
                    }
                    while (t2 > 0) {
                        if ((t2 & 1) == 1) c2++;
                        t2 = t2 >> 1;
                    }
                }
                ret = Math.max(ret, c);
                ret = Math.max(ret, c2);
            }
        }
        return ret;
    }

    @Test
    public void test() {
        int[][] a = {
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        int[][] b = {
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 1}
        };
        assertEquals(3, largestOverlap(a, b));
    }


}
