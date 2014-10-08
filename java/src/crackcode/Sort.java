package crackcode;

import java.util.Arrays;
import java.util.Comparator;

import codejam.lib.CheckUtil;

public class Sort {

	public static void main(String[] args) {

		int[] a = { 7, 3, 9, 5, 6, 1 };
		int[] b = { 1, 1, 1, 1, 1, 1 };

		mergeSort(a, b, 0, 5);
		System.out.println(Arrays.toString(a));

        int[] a2= { 7, 3, 9, 5, 6, 1 };
		qsort(a2, 0, 5);
		System.out.println(Arrays.toString(a2));
        

		
		String[] ss = { "aabbc", "bbaac", "abc", "cba", "a" };
		customSort(ss);
		

		Arrays.sort(ss, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
		System.out.println(Arrays.toString(ss));

		CheckUtil
				.check(9, findKthNumber(a, 0, a.length - 1, a.length - 1));
		CheckUtil.check(5, findKthNumber(a, 0, a.length - 1, 2));
	}

	private static void customSort(String[] strings) {

		for (int i = 0; i < strings.length; i++) {
			char[] c = strings[i].toCharArray();
			Arrays.sort(c);
			strings[i] = new String(c);
		}

		Arrays.sort(strings);

		System.out.println(Arrays.toString(strings));
	}

	static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

    static int partition(int[] a, int s, int e){
		int p = a[e];
		int i = s - 1;
        for (int j = s; j <= e-1; j++){
            if (a[j]<=p){
                i++;
				swap(a, i, j);
			}
        }
		swap(a, i + 1, e);
        return i+1;
    }
    
    static void qsort(int[] a, int s, int e){
        if (s<e){
            int m = partition(a, s, e);
            qsort(a, s, m-1);
            qsort(a, m+1, e);
        }
    }

	static int findKthNumber(int[] a, int s, int e, int k) {
		int q = partition(a, s, e);
		if (k > q)
			return findKthNumber(a, q + 1, e, k - q);
		else if (k < q)
			return findKthNumber(a, s, q - 1, k);
		return a[q];
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
