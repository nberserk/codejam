package crackcode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

// http://www.careercup.com/question?id=23823662
public class GenNumberSortedOrder {

	public static void main(String[] args) {
		int[] prime = { 2, 3, 5, 7 };
		genNumber(prime, 100);
		// genNumber2(prime, 10);
		printAllMultiples(20);
	}

	static void genNumber(int[] prime, int max) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(20, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

		for (int i = 0; i < prime.length; i++) {
			q.add(prime[i]);
		}
		
		HashSet<Integer> seen = new HashSet<Integer>();

		while (true) {
			int cur = q.poll();
			if(cur>max)
				break;
			System.out.println(cur);
			for (int i = 0; i < prime.length; i++) {
				int next = cur * prime[i];
				if (seen.contains(next)) {
					continue;
				}
				q.add(next);
				seen.add(next);
			}
		}

	}

	static void genNumber2(int[] prime, int max) {
		int n = prime.length;
		int[] next = new int[n];
		int[] idx = new int[n];
		
		for (int i = 0; i < n; i++) {
			next[i] = prime[i];
		}
		
		int[] ret = new int[max];
		ret[0] = 1;

		for (int i = 1; i < max; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				min = Math.min(min, next[j]);
			}
			if (min>max) {
				break;
			}
			System.out.println(min);
			ret[i] = min;
			for (int j = 0; j < n; j++) {
				if (min != next[j])
					continue;
				if (next[j] == min) {
					idx[j]++;
					next[j] = ret[idx[j]] * prime[j];
				}
			}
		}
	}

	static void printAllMultiples(int n) {
		int[] ugly = new int[n];
		int next_multiple_2 = 2;
		int next_multiple_3 = 3;
		int next_multiple_5 = 5;
		int next_multiple_7 = 7;
		int i2 = 0, i3 = 0, i5 = 0, i7 = 0;
		ugly[0] = 1;
		int next_ugly_no;
		int i;
		System.out.println(ugly[0]);
		for (i = 1; i < n; i++) {
			next_ugly_no = Math.min(next_multiple_2, next_multiple_3);
			next_ugly_no = Math.min(next_ugly_no, next_multiple_5);
			next_ugly_no = Math.min(next_ugly_no, next_multiple_7);
			ugly[i] = next_ugly_no;
			System.out.println(next_ugly_no);

			if (next_ugly_no == next_multiple_2) {
				i2 = i2 + 1;
				next_multiple_2 = ugly[i2] * 2;
			}
			if (next_ugly_no == next_multiple_3) {
				i3 = i3 + 1;
				next_multiple_3 = ugly[i3] * 3;
			}
			if (next_ugly_no == next_multiple_5) {
				i5 = i5 + 1;
				next_multiple_5 = ugly[i5] * 5;
			}
			if (next_ugly_no == next_multiple_7) {
				i7 = i7 + 1;
				next_multiple_7 = ugly[i7] * 7;
			}
		}
	}

}
