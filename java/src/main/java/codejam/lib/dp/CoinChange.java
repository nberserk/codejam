package main.java.codejam.lib.dp;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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

	int countIterative(int[] s, int m, int N){
		int[][] t = new int[N+1][m];

		for (int i = 0; i < m; i++) {
			t[0][i]=1;
		}

		int x,y;
		for (int i=1;i<=N;i++){
			for (int j = 0; j < m; j++) {
				// include s[j]
				x = i-s[j]>=0 ? t[i-s[j]][j] : 0; // include s[j];
				y = j-1>=0    ? t[i][j-1]: 0;

				t[i][j] = x+y;
			}
		}

		return t[N][m-1];
	}

	public static void main(String[] args) {
		CoinChange c = new CoinChange();
		int[] s = new int[] { 1, 2, 3 };
		int count = c.count(s, 2, 4);
		System.out.println(count);
		assertEquals(count , c.countIterative(s, s.length, 4));


		s = new int[] { 2, 5, 3, 6 };
		count = c.count(s, s.length-1, 10);
		System.out.println(count);
		assertEquals(count, c.countIterative(s, s.length, 10));

	}

}
