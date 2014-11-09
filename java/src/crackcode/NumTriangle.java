package crackcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import codejam.lib.CheckUtil;


public class NumTriangle {

	public static void main(String[] args) {
		
		int N = 5;
		int[][] e = { { 0, 1 }, { 2, 1 }, { 0, 2 }, { 4, 1 }, { 4, 0 } };
		
		CheckUtil.check(2, numTriangle(e, N));
		CheckUtil.check(2, numTriangle2(e));
	}

	static int[][] buildAdjacencyMatrix(int[][] a, int n) {
		int[][] m = new int[n][n];

		for (int i = 0; i < a.length; i++) {
			m[a[i][0]][a[i][1]] = 1;
			m[a[i][1]][a[i][0]] = 1;
		}

		return m;
	}

	static Map<Integer, Set<Integer>> buildAdjacencyMap(int[][] edges) {
		HashMap<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < edges.length; i++) {
			int from = edges[i][0];
			int to = edges[i][1];

			// from -> to
			Set<Integer> set = map.get(from);
			if (set == null) {
				set = new HashSet<Integer>();
				map.put(from, set);
			}
			set.add(to);

			// to -> from
			set = map.get(to);
			if (set == null) {
				set = new HashSet<Integer>();
				map.put(to, set);
			}
			set.add(from);
		}

		return map;
	}

	static int numTriangle(int[][] edges, int N) {
		int[][] m = buildAdjacencyMatrix(edges, N);
		int[] v = new int[N];
		int n = 0;
		for (int i = 0; i < N; i++) {
			Arrays.fill(v, 0);
			n += numTriangleInternal(m, i, i, 0, v);
		}

		return n / 3;
	}

	static int numTriangleInternal(int[][] m, int start, int here, int depth,
			int[] visited) {

		if (depth == 2) {
			if (m[here][start] == 1) {
				return 1;
			}
			return 0;
		}

		visited[here] = 1;
		int ret = 0;
		for (int i = 0; i < m.length; i++) {
			if (m[here][i] == 0)
				continue;
			if (visited[i] == 1) {
				continue;
			}
			ret += numTriangleInternal(m, start, i, depth + 1, visited);
		}
		return ret;
	}

	static int numTriangle2(int[][] edges) {
		int ret = 0;
		Map<Integer, Set<Integer>> map = buildAdjacencyMap(edges);

		for (Set<Integer> connected : map.values()) {
			for (Integer e1 : connected) {
				for (Integer e2 : connected) {
					if (e1 != e2 && map.get(e1).contains(e2)) {
						ret++;
					}

				}
			}
		}

		return ret / 6;
	}
}
