package crackcode;

import java.util.TreeSet;

public class SmallestRange {

	public static void main(String[] args) {
		int[] a = { 4, 10, 15, 24, 26 };
		int[] b = { 0, 9, 12, 20 };
		int[] c = { 5, 18, 22, 30 };

		smallestRange(a, b, c);
	}

	public static int smallestRange(int[] a, int[] b, int[] c) {
		int i, j, k;
		i = j = k = 0;
		int ret = 987654321;
		int[] sorted = new int[3];

		TreeSet<Integer> tree = new TreeSet<Integer>();
		while (true) {
			tree.add(a[i]);			 
			tree.add(b[j]);
			tree.add(c[k]);

			sorted[0] = tree.first();
			tree.remove(tree.first());
			sorted[1] = tree.first();
			tree.remove(tree.first());
			sorted[2] = tree.first();
			tree.remove(tree.first());
			 

			int min = sorted[0];
			int max = sorted[2];
			
			int range = max - min + 1;
			if (range < ret) {
				ret = range;
				System.out.println(String
						.format("%d,%d,%d\n", a[i], b[j], c[k]));
			}
			
			for (int l = 0; l < 3; l++) {
				if (sorted[l] == a[i] && i != a.length - 1) {
					i++;
					break;
				}
				if (sorted[l] == b[j] && j != b.length - 1) {
					j++;
					break;
				}
				if (sorted[l] == c[k] && k != c.length - 1) {
					k++;
					break;
				}
			}

			if (i == a.length - 1 && j == b.length - 1 && k == c.length - 1)
				break;
		}

		return ret;
	}
}
