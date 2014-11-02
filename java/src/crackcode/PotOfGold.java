package crackcode;

import codejam.lib.CheckUtil;


// http://www.careercup.com/question?id=15422849
public class PotOfGold {
	static int N = 100;
	static int[][] gCachePot = new int[N][N];
	static int[][][] choices = new int[N][N][2];

	private static void resetPotCache() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				gCachePot[i][j] = -1;
				choices[i][j][0] = choices[i][j][1] = -1;
			}
		}
	}

	public static void main(String[] args) {

		int[] a = { 1, 2, 3, 4 };
		resetPotCache();
		int gold = maxCoin(a, 0, 3);
		CheckUtil.check(6, gold);
		// System.out.println(gold);

		resetPotCache();
		int[] a2 = { 1, 10, 2, 3 };
		gold = maxCoin(a2, 0, 3);
		// System.out.println(gold);
		CheckUtil.check(13, gold);
	}
	
	public static int maxCoin(int[] p, int start, int end) {
		resetPotCache();
		int possibleMaxCoin = maxCoinInternal(p, start, end);
		
		int s = start;
		int e = end;

		while (true) {
			System.out.println("A:" + choices[s][e][0] + ", B:"
					+ choices[s][e][1]);

			for (int i = 0; i < 2; i++) {
				if (s == choices[s][e][i])
					s++;
				else
					e--;
			}

			if (s > e)
				break;
		}
		
		return possibleMaxCoin;
	}

	public static int maxCoinInternal(int[] p, int start, int end) {
		if (start > end) {
			return 0;
		}
		if (gCachePot[start][end] != -1) {
			return gCachePot[start][end];
		}
		
		int takeFirst = p[start]
				+ Math.min(maxCoinInternal(p, start + 2, end),
						maxCoinInternal(p, start + 1, end - 1));
		int takeLast = p[end]
				+ Math.min(maxCoinInternal(p, start + 1, end - 1),
						maxCoinInternal(p, start, end - 2));

		int v = Math.max(takeFirst, takeLast);
		if (v == takeFirst) {
			choices[start][end][0] = start;
			if (maxCoinInternal(p, start + 2, end) > maxCoinInternal(p,
					start + 1, end - 1)) {
				choices[start][end][1] = end;
			} else {
				choices[start][end][1] = start + 1;
			}

		} else {
			choices[start][end][0] = end;
			if (maxCoinInternal(p, start + 1, end - 1) > maxCoinInternal(p,
					start, end - 2)) {
				choices[start][end][1] = end - 1;
			} else {
				choices[start][end][1] = start;
			}
		}

		gCachePot[start][end] = v;
		// System.out.println(String.format("(%d,%d) ", start, end) + "A:"
		// + choices[start][end][0] + ", B:"
		// + choices[start][end][1]);
		return v;
	}
}
