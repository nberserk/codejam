package codejam.lib.dp;

import java.awt.Point;
import java.util.Arrays;


public class EditDistance {
    private static boolean sIsDebug = true;
    private static int sCostReplace = 1;
    private static int sCostDelete = 1;
    private static int sCostInsert = 1;
	private static int[][] sCache;

	static void debugPrint(String s) {
		if (sIsDebug) {
			System.out.println(s);
		}
	}

    static void test(String from, String to) {
        long time = System.currentTimeMillis();
        int distance = editDistanceSuffix(from, to);
        String log = String.format("%s->%s:%d (%dms) using suffix", from, to,
                distance,
                (System.currentTimeMillis() - time));
        System.out.println(log);
        time = System.currentTimeMillis();
        distance = editDistancePrefix(from, to);
        log = String.format("%s->%s:%d (%dms) using prefix", from, to,
                distance,
                (System.currentTimeMillis() - time));
        System.out.println(log);
        time = System.currentTimeMillis();
        distance = editDistanceRecursive(from, to);
        log = String.format("%s->%s:%d (%dms) using recursive\n", from, to,
                distance, (System.currentTimeMillis() - time));
        System.out.println(log);
    }

	public static void main(String[] args) {
        test("a", "a");
        test("a", "b");
        test("a", "ab");
        test("hieroglyphology", "michaelangelo");
        test("sunday", "saturday");

        test("abcdefghij", "aaabbbbccccddddtt");
	}

	static int editDistanceRecursive2(String from, String to, int fromIndex,
			int toIndex) {
		int cost = Integer.MAX_VALUE-10;		
        if (fromIndex >= from.length()) {
            return sCostInsert * (to.length() - toIndex);
		}

        if (toIndex >= to.length()) {
            return sCostDelete * (from.length() - fromIndex);
        }

		// cache hit ?
		if (sCache[fromIndex][toIndex] != -1) {
			return sCache[fromIndex][toIndex];
        }

        boolean isMatch = from.charAt(fromIndex) == to.charAt(toIndex) ? true
                : false;

        int parentR = editDistanceRecursive2(from, to, fromIndex + 1,
                toIndex + 1) + (isMatch ? 0 : sCostReplace);
        int parentD = editDistanceRecursive2(from, to, fromIndex + 1, toIndex)
                + sCostDelete;
        int parentI = editDistanceRecursive2(from, to, fromIndex, toIndex + 1)
                + sCostInsert;

        cost = Math.min(Math.min(parentR, parentD), parentI);

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

    static int editDistancePrefix(String from, String to) {
        int m = from.length() + 1;
        int n = to.length() + 1;

        int[][] t = new int[m][n];
        Point[][] p = new Point[m][n];
        char[][] p2 = new char[m][n];

        // base cases
        for (int i = 0; i < n; i++) {
            t[0][i] = sCostInsert * i;
        }
        for (int i = 0; i < m; i++) {
            t[i][0] = sCostDelete * i;
        }

        // start from 1
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                boolean matched = from.charAt(i - 1) == to.charAt(j - 1) ? true
                        : false;

                int d = sCostDelete + t[i - 1][j];
                int in = sCostInsert + t[i][j - 1];
                int r = t[i - 1][j - 1];
                r += matched ? 0 : sCostReplace;

                t[i][j] = Math.min(Math.min(d, in), r);

                if (t[i][j] == d) {
                    p[i][j] = new Point(i - 1, j);
                    p2[i][j] = 'd';
                } else if (t[i][j] == in) {
                    p[i][j] = new Point(i, j - 1);
                    p2[i][j] = 'i';

                } else if (t[i][j] == r) {
                    p[i][j] = new Point(i - 1, j - 1);
                    p2[i][j] = matched ? ' ' : 'r';
                }
                // debugPrint(i + "," + j + " = " + t[i][j]);
            }
        }

        // print path
        Point cur = new Point(m - 1, n - 1);
        while (true) {
            char op = p2[cur.x][cur.y];
            cur = p[cur.x][cur.y];
            if (cur == null) {
                break;
            }
            // debugPrint(String.format("%c (%d, %d)", op, cur.x, cur.y));
        }

        return t[m - 1][n - 1];
    }

	static int editDistanceSuffix(String from, String to) {
        int m = from.length() + 1;
        int n = to.length() + 1;

		int[][] t= new int[m][n];
		Point[][] p= new Point[m][n];
		char[][] p2= new char[m][n];

        // base cases
        for (int i = 0; i < n; i++) {
            t[m - 1][i] = sCostInsert * (n - 1 - i);
        }
        for (int i = 0; i < m; i++) {
            t[i][n - 1] = sCostDelete * (m - 1 - i);
        }

        // start from m-2
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
				boolean matched= from.charAt(i) == to.charAt(j) ? true : false;

                int d = sCostDelete + t[i + 1][j];
                int in = sCostInsert + t[i][j + 1];
                int r = t[i + 1][j + 1];
                r += matched ? 0 : sCostReplace;

                t[i][j] = Math.min(Math.min(d, in), r);

                if (t[i][j] == d) {
                    p[i][j] = new Point(i + 1, j);
                    p2[i][j] = 'd';
                } else if (t[i][j] == in) {
                    p[i][j] = new Point(i, j + 1);
                    p2[i][j] = 'i';

                } else if (t[i][j] == r) {
                    p[i][j] = new Point(i + 1, j + 1);
                    p2[i][j] = matched ? ' ' : 'r';
                }
                // debugPrint(i + "," + j + " = " + t[i][j]);
			}
        }

        // print path
		Point cur = new Point(0, 0);
		while (true) {
			char op = p2[cur.x][cur.y];
			cur = p[cur.x][cur.y];
            if (cur == null) {
                break;
            }
            // debugPrint(String.format("%c (%d, %d)", op, cur.x, cur.y));
		}

		return t[0][0];
	}

}
