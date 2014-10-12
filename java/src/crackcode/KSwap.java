package crackcode;

import java.util.ArrayList;

import codejam.lib.CheckUtil;

// from http://www.careercup.com/question?id=5637944224251904

public class KSwap {

	public static void main(String[] args) {

		CheckUtil.check(312, getMax(132, 1));
		CheckUtil.check(321, getMax(132, 2));
		CheckUtil.check(9987, getMax(8799, 2));
	}

	static int getMax(int n, int k) {
		ArrayList<Integer> ns = new ArrayList<Integer>();

		while (n > 0) {
			int c = n % 10;
			ns.add(c);
			n /= 10;
		}

		int[] a = new int[ns.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = ns.get(i);
		}
		return getMaxUtil(a, k);
	}

	static int getMaxUtil(int[] n, int k) {
		if (k == 0) {
			int sum = 0;
			for (int i = n.length - 1; i >= 0; i--) {
				sum *= 10;
				sum += n[i];
			}
			return sum;
		}

		int ret = Integer.MIN_VALUE;

		for (int i = n.length - 1; i > 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (n[i] < n[j]) {
					swap(n, i, j);
					ret = Math.max(ret, getMaxUtil(n, k - 1));
					swap(n, i, j);
				}
			}
		}
		return ret;
	}

	private static void swap(int[] n, int i, int j) {
		int temp = n[i];
		n[i] = n[j];
		n[j] = temp;
	}
}
