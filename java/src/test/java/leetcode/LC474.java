package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LC474 {
    int MOD = 1000;
    int[][][] dp;

    public int findMaxForm_2nd(String[] strs, int m, int n) {
        int[][][] dp2 = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            int zero = 0, one = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '0') zero++;
                else one++;
            }
            for (int j = 0; j < m + 1; j++) {
                for (int k = 0; k < n + 1; k++) {
                    dp2[i + 1][j][k] = dp2[i][j][k];
                    if (zero <= j && one <= k) {
                        dp2[i + 1][j][k] = Math.max(dp2[i + 1][j][k], 1 + dp2[i][j - zero][k - one]);
                    }
                }
            }
        }

        return dp2[strs.length][m][n];
    }

    // space optimized
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp2 = new int[2][m + 1][n + 1]; // we only need previous row. so we can reduce
        for (int i = 0; i < strs.length; i++) {
            int zero = 0, one = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '0') zero++;
                else one++;
            }

            int cur = i % 2 == 0 ? 1 : 0; // i=0, => cur 1, prev 0
            int prev = i % 2 == 0 ? 0 : 1;

            for (int j = 0; j < m + 1; j++) {
                for (int k = 0; k < n + 1; k++) {
                    dp2[cur][j][k] = dp2[prev][j][k];
                    if (zero <= j && one <= k) {
                        dp2[cur][j][k] = Math.max(dp2[cur][j][k], 1 + dp2[prev][j - zero][k - one]);
                    }
                }
            }
        }

        return dp2[strs.length % 2 == 0 ? 0 : 1][m][n]; // when strs.length==1, dp2[1] should be used
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
