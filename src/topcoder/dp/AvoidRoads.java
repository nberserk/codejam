package topcoder.dp;

import java.awt.Point;
import java.util.HashMap;

/*
 * http://community.topcoder.com/stat?c=problem_statement&pm=1889&rd=4709
 */
public class AvoidRoads {

	private int[][] mBad;

	public AvoidRoads() {
		// mCache.put(new Point(0, 1), (long) 1);
		// mCache.put(new Point(1, 0), (long) 1);
	}


	public long numWays(int width, int height, String[] bad) {

		mCache.clear();
		mBad = new int[bad.length][4];
		for (int i = 0; i < bad.length; i++) {
			String[] v = bad[i].split(" ");

			int x1 = Integer.parseInt(v[0]);
			int y1 = Integer.parseInt(v[1]);
			int x2 = Integer.parseInt(v[2]);
			int y2 = Integer.parseInt(v[3]);

			if (x1 + y1 < x2 + y2) {
				mBad[i][0] = x1;
				mBad[i][1] = y1;
				mBad[i][2] = x2;
				mBad[i][3] = y2;
			} else {
				mBad[i][0] = x2;
				mBad[i][1] = y2;
				mBad[i][2] = x1;
				mBad[i][3] = y1;
			}

			

		}

		long count = 0;


		int i = width;
		int j = height;
		count = ways(i - 1, j, i, j) + ways(i, j - 1, i, j);

		return count;
	}

	HashMap<Point, Long> mCache = new HashMap<Point, Long>();


	private long ways(int sx, int sy, int dx, int dy) {
		if (sx < 0 || sy < 0) {
			return 0;
		}

		for (int i = 0; i < mBad.length; i++) {
			if (sx == mBad[i][0] && sy == mBad[i][1] && dx == mBad[i][2]
					&& dy == mBad[i][3]) {
				return 0;
			}
		}

		Point p = new Point(sx, sy);
		Long r = mCache.get(p);
		if (r != null) {
			return r;
		}
		
		if (sx==0 && sx==sy) {
			r = (long) 1;
		}else{
			// use cache
			r = ways(sx - 1, sy, sx, sy) + ways(sx, sy - 1, sx, sy);	
		}
		
		mCache.put(p, r);
		return r;
	}

	public static void main(String[] args) {
		AvoidRoads a = new AvoidRoads();
		long r = 0;
		r = a.numWays(6, 6, new String[] { "0 0 0 1", "6 6 5 6" });
		System.out.println(r);

		r = a.numWays(1, 1, new String[] {});
		System.out.println(r);

		r = a.numWays(35, 31, new String[] {});
		System.out.println(r);

		r = a.numWays(2, 2, new String[] { "0 0 1 0", "1 2 2 2", "1 1 2 1" });
		System.out.println(r);

	}

}
