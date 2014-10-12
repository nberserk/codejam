package crackcode;

import java.util.ArrayList;

import codejam.lib.CheckUtil;

// from http://www.careercup.com/question?id=5717669093310464
public class TopologicalSort {

	public static void main(String[] args) {
		
		int[][]	 m = {{0,1,0,0},
				{0,0,1,1},
				{0,0,0,1},
				{0,0,0,0}};
		
		ArrayList<Integer> order = topologicalSort(m);
		CheckUtil.check("[3, 2, 1, 0]", order.toString());
		System.out.println(order);
		
		int[][] m2 = { { 0, 1, 0, 0 },
				{0,0,1,1},
				{0,0,0,1},
				{ 0, 1, 0, 0 } };
		order = topologicalSort(m2); // should throw exception
		// CheckUtil.check("[3, 2, 1, 0]", order.toString());
		System.out.println(order);
	}

	static void topologicalSortInternal(int[][] m, int here, int[] state,
			ArrayList<Integer> order) {
		if (state[here] == 2)
			return;
		if (state[here] == 1)
			throw new RuntimeException("found cycle");

		state[here] = 1;
		int n = m[0].length;
		for (int i = 0; i < n; i++) {
			if (m[here][i] == 1)
				topologicalSortInternal(m, i, state, order);
		}

		state[here] = 2;
		order.add(here);
	}
	
	static ArrayList<Integer> topologicalSort(int[][] m){
		
		int n = m[0].length;
		int[] state = new int[m[0].length];
		ArrayList<Integer> order = new ArrayList<Integer>();
		
		for(int i=0;i<n;i++	){
			topologicalSortInternal(m, i, state, order);
		}
		return order;
	}

}
