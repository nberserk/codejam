package topcoder.dp;

import java.awt.Point;
import java.util.HashMap;

/**
 * http://community.topcoder.com/stat?c=problem_statement&pm=2402&rd=5009
 */

public class BadNeighbors {
	public int localMax(int start, int end) {
		if (start>end) {
			return 0;
		}
		Point key = new Point(start, end);
		int r = 0;
		if (mCache.containsKey(key)) {
			r = mCache.get(key);
			// System.out.println(String.format("(%d,%d)->%d", start, end, r));
			return r;
		}
		
		r = Math.max(mDonations[start] + localMax(start + 2, end), localMax(start + 1, end));
		mCache.put(key, r);
		// System.out.println(String.format("(%d,%d)->%d", start, end, r));
		return r;

	}

	int[] mDonations;
	HashMap<Point, Integer> mCache = new HashMap<Point, Integer>();

	public int maxDonations(int[] d) {
		mDonations = d;
		mCache.clear();
		int n = d.length;

		return Math.max(localMax(0, n - 2), localMax(1, n - 1));
	}

	public static void main(String[] args) {

		BadNeighbors b = new BadNeighbors();
		int r = b.maxDonations(new int[] { 11, 15 });
		System.out.println(r);

		r = b.maxDonations(new int[] { 10, 3, 2, 5, 7, 8 });
		System.out.println(r);

		r = b.maxDonations(new int[] { 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95,
				265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 });
		System.out.println(r);

		r = b.maxDonations(new int[] { 7, 7, 7, 7, 7, 7, 7 });
		System.out.println(r);

	}

}
