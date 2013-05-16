package topcoder.dp;

import java.util.Arrays;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 */
public class CoinChange {

	int[][] cache;

	int count(int[] s, int m, int N) {
		int c = 0;

		cache = new int[m + 1][N + 1];
		for (int i = 0; i < cache.length; i++) {
			Arrays.fill(cache[i], -1);
		}

		c = localCount(s, m - 1, N) + localCount(s, m, N - s[m]);

		return c;
	}

	private int localCount(int[] s, int m, int N) {
		if (m < 0 || N < 0) {
			return 0;
		}
		if (N == 0) {
			return 1;
		}

		if (cache[m][N] != -1) {
			return cache[m][N];
		}

		int c = localCount(s, m - 1, N) + localCount(s, m, N - s[m]);
		cache[m][N] = c;
		return c;
	}

	public static void main(String[] args) {
		CoinChange c = new CoinChange();
		int count = c.count(new int[] { 1, 2, 3 }, 2, 4);
		System.out.println(count);

		count = c.count(new int[] { 2, 5, 3, 6 }, 3, 10);
		System.out.println(count);

	}

}
