package crackcode;

import codejam.lib.CheckUtil;

import java.util.Arrays;
import java.util.Comparator;

public class Sort {

	public static void main(String[] args) {
		int[] a = { 7, 3, 9, 5, 6, 1, 100,88,1000 };
        mergeSort(a);
        CheckUtil.check(1, a[0]);
        CheckUtil.check(3, a[1]);
        CheckUtil.check(5, a[2]);
        CheckUtil.check(6, a[3]);
        CheckUtil.check(7, a[4]);
        CheckUtil.check(9, a[5]);
        CheckUtil.check(88, a[6]);
        CheckUtil.check(100, a[7]);
        CheckUtil.check(1000, a[8]);
        // System.out.println(Arrays.toString(a));

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

        CheckUtil.check(100, findKthNumber(a, 0, a.length - 1, a.length - 2));
		CheckUtil.check(5, findKthNumber(a, 0, a.length - 1, 2));

        //
        int[] x = {17,5,1,9};
        int[] c = {3,2,4,1};
        qsortCustom(c,x,0,x.length-1);
        System.out.println(Arrays.toString(x));
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

	static int partitionDecreasing(int[] a, int s, int e) {
		int p = a[e];
		int i = s - 1;
		for (int j = s; j <= e - 1; j++) {
			if (a[j] >= p) {
				i++;
				swap(a, i, j);
			}
		}
		swap(a, i + 1, e);
		return i + 1;
	}

	static void qsortDecreasing(int[] a, int s, int e) {
		if (s < e) {
			int m = partition(a, s, e);
			qsort(a, s, m - 1);
			qsort(a, m + 1, e);
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
    static void mergeSort(int[] a) {
        int[] b = new int[a.length];
        mergeSortInternal(a, b, 0, a.length - 1);
    }
    static void mergeSortInternal(int[] a, int[] b, int start, int end) {
		if (start == end) {
			return;
		}

		int m = (start+end)/2;
        mergeSortInternal(a, b, start, m);
        mergeSortInternal(a, b, m + 1, end);
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

    static int partitionCustom(int[] a, int[] b, int s, int e) {
        int p = a[e];
        int i = s - 1;
        for (int j = s; j <= e-1; j++){
            if (a[j]<=p){
                i++;
                swap(b, i,j);
                swap(a, i,j);
            }
        }
        swap(a, e, i+1);
        swap(b, e, i + 1);
        return i+1;
    }

    static void qsortCustom(int[] a, int[] b, int s, int e){
        if (s<e){
            int r = partitionCustom(a,b, s, e);
            qsortCustom(a,b, s, r-1);
            qsortCustom(a,b, r+1, e);
        }
    }

}
