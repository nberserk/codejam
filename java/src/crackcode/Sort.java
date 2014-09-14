package crackcode;

import java.util.Arrays;
import java.util.Comparator;

public class Sort {

	public static void main(String[] args) {

		int[] a = { 7, 3, 9, 5, 6, 1 };
		int[] b = { 1, 1, 1, 1, 1, 1 };

		mergeSort(a, b, 0, 5);
		System.out.println(Arrays.toString(a));

		String[] ss = { "aabbc", "bbaac", "abc", "cba", "a" };

		Arrays.sort(ss, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});

		System.out.println(Arrays.toString(ss));
	}

	static void qsort(int[] a, int start, int end) {

	}

	// a& b inclusive
	static void mergeSort(int[] a, int[] b, int start, int end) {
		if (start == end) {
			return;
		}
		
		int m = (start+end)/2;
		mergeSort(a, b, start, m);
		mergeSort(a, b, m+1, end);
		// copy sorted a to b
		int l = start;
		int r = m + 1;
		for (int i = start; i <= end; i++) {
			if (l > m) {
				b[i] = a[r++];
				continue;
			}
			if (r > end) {
				b[i] = a[l++];
				continue;
			}

			if (a[l] > a[r]) {
				b[i] = a[r++];
			} else {
				b[i] = a[l++];
			}
		}
		
		// copy b back to a
		for (int i = start; i <= end; i++) {
			a[i] = b[i];
		}
	}

}
