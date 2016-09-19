package crackcode;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import codejam.lib.CheckUtil;

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
		
		char[] in2 = "abcd".toCharArray();
		char[] out2 = new char[in2.length];
		ArrayList<String> subset = new ArrayList<String>();
		allSubset(in2, out2, 0, 0, 4, subset);
		System.out.println(subset.toString());

		int[] numbers = { 8, 6, 6, 2, 6, 6, 5 };
		int[] numbers2 = { 8, 1, 1, 1, 1, 1, 1, };
		pPhoneNumber(numbers2, new char[7], 0);
		System.out.println(sPhonumberCount);

		// printNum
		for (int i = 1; i < 10; i++) {
			printNum(i, 10);
		}

        //
        CheckUtil.check(true, isAggregatedNumber("112358"));
        CheckUtil.check(false, isAggregatedNumber("117"));
        CheckUtil.check(true, isAggregatedNumber("112112224"));
        CheckUtil.check(true, isAggregatedNumber("1299111210"));
        CheckUtil.check(true, isAggregatedNumber("122436"));

		//
		String[] in = { "dfgs", "sdfg", "ghjhk" };
		String[] in3 = { "adsd", "qasdasdb", "ndasdf", "aasddq", "fasda", "deasdn", "oooa" };
		CheckUtil.check(true, canBeTermedAllString(in));
		CheckUtil.check(true, canBeTermedAllString(in3));

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

	static void printNum(int n, int max) {
		if (n > max)
			return;
		System.out.println(n);

		for (int i = 0; i < 10; i++) {
			printNum(n * 10 + i, max);
		}
	}

    // http://www.careercup.com/question?id=14948278
    static boolean isAggregatedNumber(String in){
        int N = in.length();
        int maxLen = (N - 1) / 2;
        int start = 0;
        for (int i = 1; i <= maxLen; i++){ //i: first len
            for (int j = 1; j <= maxLen; j++){
                if (i + j + Math.max(i, j) > N)
                    continue;
                start = 0;
                String first = in.substring(start, start + i);
                String second = in.substring(start+i, start+i+j);
                int f = Integer.parseInt(first);
                int s = Integer.parseInt(second);
                int a = f+s;
                String added = String.valueOf(a);

                String sub = in.substring(start + i + j, start + i + j + added.length());
                if (!added.equals(sub))
                    continue;

                start+=i+j+added.length();
                while (true){
                    if(start==N)
                        return true;
                    int next = s+a;
                    String sNext = String.valueOf(next);

                    if (start + sNext.length() > N)
                        break;

                    if (!sNext.equals(in.substring(start, start + sNext.length())))
                        break;

                    start += sNext.length();
                    s=a;
                    a=next;
                }

            }
        }
        return false;
    }

    static boolean isAggregatedNumberInternal(String in, int start, int curSum) {
        int N = in.length();
        if (start == N)
            return true;
        int sumLen = String.valueOf(curSum).length();
        if (N - start < sumLen)
            return false;

        int maxLen = N - start - sumLen;
        for (int i = 1; i <= maxLen; i++) {
            String second = in.substring(start, start + i);
            int s = Integer.parseInt(second);

            int sum = s + curSum;
            String sumStr = String.valueOf(sum);
            boolean matched = true;
            for (int k = 0; k < sumStr.length(); k++) {
                if (sumStr.charAt(k) != in.charAt(start + i + k)) {
                    matched = false;
                    break;
                }
            }
            if (!matched) {
                continue;
            }
            System.out.println(String.format("trying to %d+%d=%d", curSum, s, sum));
            if (isAggregatedNumberInternal(in, start + i + sumStr.length(), sum))
                return true;
        }

        return false;
    }

	// http://www.careercup.com/question?id=6362892580421632
	static boolean canBeTermedAllString(String[] s) {
		boolean[] used = new boolean[s.length];
		return canBeTermedAllStringInternal(s, used, 0, (char) 0);
	}

	static boolean canBeTermedAllStringInternal(String[] s, boolean[] used, int count, char prev) {
		if (count == s.length) {
			return true;
		}
		for (int i = 0; i < s.length; i++) {
			if (used[i])
				continue;
			if (prev == 0 || prev == s[i].charAt(0)) {
				used[i] = true;
				if (canBeTermedAllStringInternal(s, used, count + 1, s[i].charAt(s[i].length() - 1)))
					return true;
				used[i] = false;
			}
		}

		return false;
	}
}
