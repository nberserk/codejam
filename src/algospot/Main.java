package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * http://algospot.com/judge/problem/read/MAXSUM
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
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

	public static int solve(int[] a) {
		int max = Integer.MIN_VALUE;
		int curMax = a[0];

		for (int i = 1; i < a.length; i++) {
			if (a[i] >= 0) {
				if (curMax < 0) {
					curMax = a[i];
				} else {
					curMax += a[i];
				}
			} else {
				curMax = Math.max(curMax, a[i]);
			}
			if (curMax > max) {
				max = curMax;
			}
		}

		return max;
	}


}
