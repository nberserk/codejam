package codejam.lib;

import java.util.Arrays;

public class PrimMST {

	public static void main(String[] args) {

		int[][] m = new int[][] { { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 },
				{ 0, 3, 0, 0, 7 }, { 6, 8, 0, 0, 9 }, { 0, 5, 7, 9, 0 }, };
		int n = PrimMST(m);
		System.out.println(n);

		m = new int[][] { 
				{ 0, 4, 0, 0, 0, 0, 0, 8, 0 }, // 0
				{ 4, 0, 8, 0, 0, 0, 0, 11, 0 }, // 1
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 },
				{ 0, 0, 7, 0, 9, 14, 0, 0, 0 },
				{ 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, // 5
				{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, // 6
				{ 8, 11, 0, 0, 0, 0, 1, 0, 7 }, // 7
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 },
		};

		n = PrimMST(m);
		System.out.println(n);
	}

	public static int PrimMST(int[][] mat) {
		int V = mat.length;

		// validate matrix
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (mat[i][j] != mat[j][i]) {
					assert false;
				}
			}
		}

		boolean[] mstSet = new boolean[V];
		int[] key = new int[V];
		int[] parent = new int[V];
		Arrays.fill(key, Integer.MAX_VALUE);
		key[0] = 0;
		parent[0] = -1;

		for (int i = 0; i < V - 1; i++) {
			// find min
			int minIndex = -1;
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < key.length; j++) {
				if (key[j] < min && mstSet[j] == false) {
					minIndex = j;
					min = key[j];
				}
			}
			mstSet[minIndex] = true;

			int u = minIndex;
			min = Integer.MAX_VALUE;
			System.out.println(i);
			for (int j = 0; j < V; j++) {
				if (mat[u][j] == 0) {
					continue;
				}
				if (mstSet[j] == false && mat[u][j] < key[j]) {
					System.out.println(j + " is updated to " + mat[u][j]
							+ ", was " + key[j]);
					key[j] = mat[u][j];
					parent[j] = u;

				}
			}
		}

		// print
		for (int i = 1; i < V; i++) {
			System.out.println(parent[i] + " - " + i + "\t" + key[i]);
		}

		int cost = 0;
		for (int i : key) {
			cost += i;
		}
		return cost;
	}

}
