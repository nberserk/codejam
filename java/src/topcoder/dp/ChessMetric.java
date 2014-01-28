package topcoder.dp;

import java.util.Arrays;

/*
 * http://community.topcoder.com/stat?c=problem_statement&pm=1592&rd=4482
 */
public class ChessMetric {

	int dx, dy;
	int size;
	private long[][][] cache;

	public long howMany(int size, int[] start, int[] end, int numMoves) {
		long c = 0;

		this.size = size;
		dx = end[0];
		dy = end[1];

		cache = new long[100][100][51];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				Arrays.fill(cache[i][j], -1);
			}
		}
		c = howMany2(start[0], start[1], numMoves);

		return c;
	}

	private long howMany2(int x, int y, int numMoves) {
		int[] offsetx = { -2, -1, 1, 2, 2, 1, -1, -2, -1, 0, 1, 1, 1, 0, -1, -1, };
		int[] offsety = { -1, -2, -2, -1, 1, 2, 2, 1, -1, -1, -1, 0, 1, 1, 1, 0, };

		if (x == dx && y == dy && numMoves == 0) {
			return 1;
		}

		if (numMoves == 0) {
			return 0;
		}

		if (cache[x][y][numMoves] != -1) {
			return cache[x][y][numMoves];
		}

		long c = 0;
		for (int k = 0; k < offsety.length; k++) {
			int nx = x + offsetx[k];
			int ny = y + offsety[k];

			if (nx < 0 || ny < 0 || nx >= size || ny >= size) {
				continue;
			}

			c += howMany2(nx, ny, numMoves - 1);
		}

		cache[x][y][numMoves] = c;
		// System.out.println(String.format("(%d,%d)-%d", x, y, numMoves));
		return c;
	}

	public static void main(String[] args) {
		ChessMetric c = new ChessMetric();
		
		long r = 0;
		r = c.howMany(3, new int[] { 0, 0 }, new int[] { 1, 0 }, 1);
		System.out.println(r);

		r = c.howMany(3, new int[] { 0, 0 }, new int[] { 1, 2 }, 1);
		System.out.println(r);

		r = c.howMany(3, new int[] { 0, 0 }, new int[] { 2, 2 }, 1);
		System.out.println(r);

		r = c.howMany(3, new int[] { 0, 0 }, new int[] { 0, 0 }, 2);
		System.out.println(r);

		r = c.howMany(100, new int[] { 0, 0 }, new int[] { 0, 99 }, 50);
		System.out.println(r);

	}

}
