package crackcode;

import java.awt.Point;
import java.util.ArrayList;


public class Robot {
	final int N = 5;
	int[][] mGrid = new int[N][N];

	public static void main(String[] args) {

		Robot r = new Robot();
		ArrayList<Point> path = new ArrayList<Point>();
		int count = r.findAllPath(0, r.N - 1, path);
		System.out.println(count);

	}

	int findAllPath(int x, int y, ArrayList<Point> path) {
		if (y < 0 || x >= N)
			return 0;

		if (x == N - 1 && y == 0) {
			System.out.println(path.toString());
			return 1;
		}

		Point cur = new Point(x, y);
		path.add(cur);

		int ret = findAllPath(x, y - 1, path);
		ret += findAllPath(x + 1, y, path);

		path.remove(cur);

		return ret;
	}

}
