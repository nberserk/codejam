package codejam.lib;

import java.util.Arrays;

public class PrimMST {

	public static void main(String[] args) {

		int[][] m = new int[][] { { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 },
				{ 0, 3, 0, 0, 7 }, { 6, 8, 0, 0, 9 }, { 0, 5, 7, 9, 0 }, };

		int n = PrimMST(m);
		System.out.println(n);
	}

	public static int PrimMST(int[][] mat) {
		int V = mat.length;

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
			for (int j = 0; j < V; j++) {

				if (mat[u][j] != 0 && mstSet[j] == false && mat[u][j] < key[j]) {
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
