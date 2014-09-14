package crackcode;

import java.awt.Point;
import java.util.ArrayList;

public class Recursive {
	static int[] stage = { 25, 10, 5, 1 };
	static int[] columnForRow = new int[8];
	static int gQueenWay;
	static int gQueenWay2;
	public static void main(String[] args) {


		int N = 3;
		char[] c = new char[N * 2];
		parenthesis(N, N, N, c);

		int[] a = new int[] { 1, 4, 8, 5, 9 };
		ArrayList<Integer> set = new ArrayList<Integer>();
		subset(a, 0, set);

		int r = cent(25, 0);
		System.out.println(r);


		placeQueen(0);

		ArrayList<Point> ptQueens = new ArrayList<Point>();
		queen(ptQueens);
	}

	static boolean isShareX(ArrayList<Point> pt, int x) {
		for (int i = 0; i < pt.size(); i++) {
			if (pt.get(i).x == x) {
				return true;
			}
		}
		return false;
	}

	static boolean isShareY(ArrayList<Point> pt, int y) {
		for (int i = 0; i < pt.size(); i++) {
			if (pt.get(i).y == y) {
				return true;
			}
		}
		return false;
	}

	static boolean isShareDiagonal(ArrayList<Point> pt, int x, int y) {
		for (int i = 0; i < pt.size(); i++) {
			int diffx = x - pt.get(i).x;
			int diffy = y - pt.get(i).y;
			if (Math.abs(diffx) == Math.abs(diffy)) {
				return true;
			}
		}
		return false;
	}
	
	static void placeQueen(int row){
		if (row == 8) {
			for (int i = 0; i < 8; i++) {
				System.out.println("(" + i + "," + columnForRow[i] + ") ");
			}
			System.out.println("--- " + gQueenWay++);
			return;
		}
		for (int i = 0; i < 8; i++) {
			columnForRow[row] = i;
			if (check(row)) {
				placeQueen(row + 1);
			}
		}

	}

	private static boolean check(int row) {
		for (int i = 0; i < row; i++) {
			int diff = Math.abs(columnForRow[i] - columnForRow[row]);
			if (diff == 0 || diff == row - i) {
				return false;
			}

		}
		return true;
	}

	static void queen(ArrayList<Point> points) {
		if (points.size()==8) {
			gQueenWay2++;
			System.out.println(gQueenWay2 + points.toString());
			return;
		}

		int x = points.size();
		// for (int x = 0; x < 8; x++) {
		// if (isShareX(points, x))
		// continue;
			for (int y = 0; y < 8; y++) {
				if (isShareY(points, y) || isShareDiagonal(points, x, y))
					continue;

				Point p = new Point(x, y);
				points.add(p);
				queen(points);
				points.remove(p);
			}
		// }
	}

	static int cent(int n, int s) {
		if (s == 3) {
			return 1;
		}

		int ret = 0;
		int c = 0;
		while (n >= c * stage[s]) {
			ret += cent(n - c * stage[s], s + 1);
			c++;
		}

		return ret;
	}

	static void subset(int[] s, int idx, ArrayList<Integer> set) {
		if (idx >= s.length) {
			System.out.println(set.toString());
			return;
		}

		subset(s, idx + 1, set);

		set.add(s[idx]);
		subset(s, idx + 1, set);
		set.remove(new Integer(s[idx]));
	}

	static void parenthesis(int N, int open, int closed, char[] c) {
		int idx = N * 2 - open - closed;
		if (idx == N * 2) {
			for (char d : c) {
				System.out.print(d);
			}
			System.out.println("");
			return;
		}

		if (open > 0) {
			c[idx] = '(';
			parenthesis(N, open - 1, closed, c);
		}
		if (closed > open) {
			c[idx] = ')';
			parenthesis(N, open, closed - 1, c);
		}
	}

}
