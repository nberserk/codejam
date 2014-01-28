package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * http://algospot.com/judge/problem/read/MAXSUM
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		test();

		InputStreamReader isr = new InputStreamReader(System.in);

		BufferedReader br = new BufferedReader(isr);
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int count = Integer.parseInt(br.readLine());
			String[] v = br.readLine().split(" ");
			int[] a = new int[count];
			for (int j = 0; j < count; j++) {
				a[j] = Integer.parseInt(v[j]);
			}

			int result = Main.solve(a);
			System.out.println(result);

		}
	}

	private static void test() {
		int s = 0;
		s = Main.solve(new int[] { -1, -2, -10 });
		if (s != 0) {
			System.out.println("can't be here");
		}

		s = Main.solve(new int[] { 1, 2, 3, -10, -2, 10 });
		if (s != 10) {
			System.out.println("can't be here");
		}

		s = Main.solve(new int[] { 1, 2, 3, -10, -2, 5 });
		if (s != 6) {
			System.out.println("can't be here");
		}

		s = Main.solve(new int[] { -1, 0, 1 });
		if (s != 1) {
			System.out.println("can't be here");
		}

	}

	public static int solve(int[] a) {
		int max = 0;
		int curMax = 0;

		ArrayList<Integer> s = new ArrayList<Integer>();

		// simplify
		int p = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] == 0) {
				continue;
			}
			if (a[i] > 0 && p >= 0) {
				p += a[i];
			} else if (a[i] < 0 && p <= 0) {
				p += a[i];
			} else {
				s.add(p);
				p = 0;
			}
		}

		return max;
	}


}
