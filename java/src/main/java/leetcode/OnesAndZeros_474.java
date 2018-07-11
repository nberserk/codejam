package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OnesAndZeros_474 {
    int MOD = 1000;
    int[][][] dp;

    public int findMaxForm(String[] strs, int m, int n) {
        int[] a = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            int t = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '0') t++;
                else t += MOD;
            }
            a[i] = t;
        }

        dp = new int[a.length][m + 1][n + 1];
        return find(a, 0, m, n);
    }

    public int findMaxForm_1st(String[] strs, int m, int n) {
        int[] a = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            int t = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '0') t++;
                else t += MOD;
            }
            a[i] = t;
        }
        dp = new int[a.length][m + 1][n + 1];
        return find(a, 0, m, n);
    }

    int find(int[] a, int start, int m, int n) {
        if (start == a.length) return 0;
        if (m == 0 && n == 0) return 0;
        if (dp[start][m][n] != 0)
            return dp[start][m][n];

        int ret = Integer.MIN_VALUE;

        int mThis = a[start] % MOD;
        int nThis = a[start] / MOD;
        if (m >= mThis && n >= nThis) {
            ret = Math.max(ret, 1 + find(a, start + 1, m - mThis, n - nThis));
        }
        ret = Math.max(ret, find(a, start + 1, m, n));
        dp[start][m][n] = ret;
        return ret;
    }


    @Test(timeout = 1000)
    public void test() {
        assertEquals(4, findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        assertEquals(41, findMaxForm(new String[]{"10", "0001", "111001", "1", "0",
                "10", "0001", "111001", "1", "0", "10", "0001", "111001", "1", "0", "10", "0001", "111001", "1", "0",
                "10", "0001", "111001", "1", "0", "10", "0001", "111001", "1", "0", "10", "0001", "111001", "1", "0",
                "10", "0001", "111001", "1", "0", "10", "0001", "111001", "1", "0", "10", "0001", "111001", "1", "0",
                "10", "0001", "111001", "1", "0"}, 50, 30));
    }
}
