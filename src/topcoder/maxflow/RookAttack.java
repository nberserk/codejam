package topcoder.maxflow;

import java.util.Arrays;

public class RookAttack {

	int howMany(int rows, int cols, String[] cutouts) {

		// make flow matrix
		int[][] m = new int[cols][rows];
		for (int i = 0; i < m.length; i++) {
			Arrays.fill(m[i], 1);
		}

		for (int i = 0; i < cutouts.length; i++) {
			String[] temp = cutouts[i].split(" ");
			int r = Integer.parseInt(temp[0]);
			int c = Integer.parseInt(temp[1]);
			m[c][r] = 0;
		}

		
		
		// search

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
