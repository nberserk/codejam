package topcoder.dp;

import java.util.Arrays;
import java.util.HashSet;

public class Jewelry {

	public static long howMany(int[] values) {

		int n = values.length;
		// sort
		Arrays.sort(values);

		int c = 0;
		for (int i = 1; i < values.length; i++) {
			int[] set = new int[values.length - 1];
			int index = 0;
			for (int j = 0; j < values.length; j++) {
				if (j == i) {
					continue;
				}
				set[index++] = values[j];
			}
			long t = countSubsetSum(set, n - 1, values[i]);
			System.out.println(Arrays.toString(set) + "," + values[i] + "-" + t);
			c += t;
		}

		HashSet<Integer, int[]> a = new HashSet<Integer, int[]>();

		return c;
	}

	public static long countSubsetSum(int[] set, int n, int sum) {
		// System.out.println(String.format("%d,%d called", n, sum));
		if (sum == 0) {
			return 1;
		}

		if (n == 0 && sum != 0) {
			return 0;
		}

		if (set[n - 1] > sum) {
			return countSubsetSum(set, n - 1, sum);
		}

		//
		return countSubsetSum(set, n - 1, sum) + countSubsetSum(set, n - 1, sum - set[n - 1]); // excluding
																							// last
																							// one
																							// +
																							// including
																							// last
																							// one

	}

	public static void main(String[] args) {

		long r = 0;
		r = howMany(new int[] { 1, 2, 5, 3, 4, 5 });
		System.out.println(r);

	}

}
