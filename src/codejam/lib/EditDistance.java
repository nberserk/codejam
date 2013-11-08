package codejam.lib;

import java.util.Arrays;


public class EditDistance {

	private static int sCostReplace;
	private static int sCostDelete;
	private static int sCostInsert;
	private static int[][] sCache;

	public static void main(String[] args) {

		String from= "a";
		String to= "a";
		System.out.println(from + "->" + to + ":" + editDistance(from, to));
		System.out.println(from + "->" + to + ":"
				+ editDistanceRecursive(from, to));

		// from= "a";
		// to= "b";
		// System.out.println(from + "->" + to + ":" + editDistance(from, to));
		// System.out.println(from + "->" + to + ":"
		// + editDistanceRecursive(from, to, 0, 0));

		// from= "a";
		// to= "ab";
		// System.out.println(from + "->" + to + ":" + editDistance(from, to));
		// System.out.println(from + "->" + to + ":"
		// + editDistanceRecursive(from, to, 0, 0));

		from= "hieroglyphology";
		to= "michaelangelo";
		long time= System.currentTimeMillis();
		int distance= editDistance(from, to);
		String log= String.format("%s->%s:%d (%dms)", from, to, distance,
				(System.currentTimeMillis() - time));
		System.out.println(log);
		time= System.currentTimeMillis();
		distance= editDistanceRecursive(from, to);
		log= String.format("%s->%s:%d (%dms)", from, to, distance,
				(System.currentTimeMillis() - time));
		System.out.println(log);
	}

	static int editDistanceRecursive2(String from, String to, int fromIndex,
			int toIndex) {
		int cost = Integer.MAX_VALUE-10;		
		if (fromIndex >= from.length() || toIndex >= to.length()) {
			return cost;
		}

		// cache hit ?
		if (sCache[fromIndex][toIndex] != -1) {
			return sCache[fromIndex][toIndex];
		}
		

		if (fromIndex == from.length() - 1 && toIndex == to.length() - 1) {
			if (from.charAt(fromIndex) == to.charAt(toIndex)) {
				cost= 0;
			} else {
				cost= sCostReplace;
			}
		} else {
			int parentR= editDistanceRecursive2(from, to, fromIndex + 1,
					toIndex + 1);
			int parentD= editDistanceRecursive2(from, to, fromIndex + 1,
					toIndex);
			int parentI= editDistanceRecursive2(from, to, fromIndex,
					toIndex + 1);
			if (from.charAt(fromIndex) == to.charAt(toIndex)) {
				cost= Math.min(parentR, parentD);
				cost= Math.min(cost, parentI);

			} else {
				parentR+= sCostReplace;
				parentD+= sCostDelete;
				parentI+= sCostInsert;

				cost= Math.min(parentR, parentD);
				cost= Math.min(cost, parentI);
			}
		}

		if (sCache[fromIndex][toIndex] == -1) {
			sCache[fromIndex][toIndex]= cost;
		}

		return cost;
	}

	static int editDistanceRecursive(String from, String to) {

		sCache= new int[from.length()][to.length()];
		for (int i= 0; i < sCache.length; i++) {
			Arrays.fill(sCache[i], -1);
		}

		return editDistanceRecursive2(from, to, 0, 0);
	}

	static int editDistance(String from, String to) {
		sCostReplace= 1;
		sCostDelete= 1;
		sCostInsert= 1;

		int m= from.length();
		int n= to.length();

		int[][] t= new int[m][n];
		for (int i= m - 1; i >= 0; i--) {
			for (int j= n - 1; j >= 0; j--) {

				boolean matched= from.charAt(i) == to.charAt(j) ? true : false;

				if (i == m - 1 && j == n - 1) {
					t[i][j]= matched ? 0 : sCostReplace;
					continue;
				}

				int d= sCostDelete;
				int in= sCostInsert;
				int r= sCostReplace;
				if (from.charAt(i) == to.charAt(j)) {
					d= in= r= 0;
				}

				int min= Integer.MAX_VALUE;
				if (i + 1 < m) {
					min= Math.min(min, t[i + 1][j] + d);
				}
				if (j + 1 < n) {
					min= Math.min(min, t[i][j + 1] + in);
				}
				if (j + 1 < n && i + 1 < m) {
					min= Math.min(min, t[i + 1][j + 1] + r);
				}

				t[i][j]= min;
				System.out.println(i + "," + j + "=" + t[i][j]);
			}
		}
		
		return t[0][0];
	}

}
