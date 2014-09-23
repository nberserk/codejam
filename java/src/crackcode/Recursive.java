package crackcode;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class Recursive {
	static int[] stage = { 25, 10, 5, 1 };
	static int[] columnForRow = new int[8];
	static int gQueenWay;
	static int gQueenWay2;
	private static int sPhonumberCount;
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

		char[] str = { 'a', 'b', 'c', 'd' };
		char[] cur = new char[str.length];
		permutation(str, cur, 0);

		char[] inForCombination = "123".toCharArray();
		char[] out = new char[inForCombination.length];
		combination(inForCombination, out, 0, 0, 1);
		combination(inForCombination, out, 0, 0, 2);
		combination(inForCombination, out, 0, 0, 3);
		
		char[] in2 = "abcd".toCharArray();
		char[] out2 = new char[in2.length];
		ArrayList<String> subset = new ArrayList<String>();
		allSubset(in2, out2, 0, 0, 4, subset);
		System.out.println(subset.toString());

		int[] numbers = { 8, 6, 6, 2, 6, 6, 5 };
		int[] numbers2 = { 8, 1, 1, 1, 1, 1, 1, };
		pPhoneNumber(numbers2, new char[7], 0);
		System.out.println(sPhonumberCount);
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

	static void permutation(char[] c, char[] cur, int idx) {
		if (idx == c.length) {
			System.out.println(Arrays.toString(cur));
			return;
		}

		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				continue;
			}
			cur[idx] = c[i];
			char t = c[i];
			c[i] = ' ';
			permutation(c, cur, idx + 1);
			c[i] = t;
		}
	}

	static void combination(char[] in, char[] out, int start, int k, int maxk) {
		if (k == maxk) {
			System.out.println(Arrays.toString(out));
			return;
		}

		for (int i = start; i < in.length; i++) {
			out[k] = in[i];
			combination(in, out, i + 1, k + 1, maxk);
		}
	}

	static void allSubset(char[] in, char[] out, int start, int k, int maxk,
			ArrayList<String> array) {
		if (k == maxk) {
			System.out.println(Arrays.toString(out));
			return;
		}

		array.add(new String(out, 0, k));

		for (int i = start; i < in.length; i++) {
			out[k] = in[i];
			allSubset(in, out, i + 1, k + 1, maxk, array);
		}
	}

	static void pPhoneNumber(int[] numbers, char[] out, int idx) {
		if (idx == numbers.length) {
			System.out.println(Arrays.toString(out));
			sPhonumberCount++;
			return;
		}

		if (numbers[idx] == 1 || numbers[idx] == 0) {
			out[idx] = (char) ('0' + numbers[idx]);
			pPhoneNumber(numbers, out, idx + 1);
		} else {
			for (int i = 0; i < 3; i++) {
				out[idx] = getCharKey(numbers[idx], i + 1);
				pPhoneNumber(numbers, out, idx + 1);
			}
		}

	}

	static char getCharKey(int key, int place) {
		char[][] table = { { 'a', 'b', 'c' }, { 'd', 'e', 'f' },
				{ 'g', 'h', 'i' }, { 'j', 'k', 'l' }, { 'm', 'n', 'o' },
				{ 'p', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y' } };

		return table[key - 2][place - 1];
	}

}
