package crackcode;

import codejam.lib.CheckUtil;

public class NextHigherNum {

	public static void main(String[] args) {
		int[] a = { 2, 1, 7, 6, 5 };
		int[] higher = nextHigherNumb(a);
		// 25167
		CheckUtil.check(2, higher[0]);
		CheckUtil.check(5, higher[1]);
		CheckUtil.check(1, higher[2]);
		CheckUtil.check(6, higher[3]);
		CheckUtil.check(7, higher[4]);
	}

	static int[] nextHigherNumb(int[] a) {
		if (a == null)
			return null;
		if (a.length <= 1)
			return a;

		int n = a.length;
		int min = a[n - 1];
		int minIdx = n - 1;
		for (int i = n - 2; i >= 0; i--) {
			if (a[i] < min) {
				int temp = a[i];
				a[i] = min;
				a[minIdx] = temp;

				Sort.qsortDecreasing(a, i + 1, n - 1);
				break;
			}
		}
		return a;
	}

}
