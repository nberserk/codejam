package topcoder.maxflow;

import java.util.ArrayList;
import java.util.Arrays;


/*
 * max bipartite matching
 * http://community.topcoder.com/stat?c=problem_statement&pm=1931&rd=4709
 */

public class RookAttack {
	
	class Node{
		public Node(int c) {
			node = c;
		}

		public Node(int i, Node parent) {
			node = i;
			this.parent = parent;
		}

		int node;
		Node parent;
	}

	public int howMany(int rows, int cols, String[] cutouts) {
		System.out.println("-------");
		
		
		int size = cols + rows + 1;
		int [][] m = new int[size][size];
		for (int i = 0; i < m.length; i++) {
			Arrays.fill(m[i], 0);
		}
		// row -> cols
		for (int j = 0; j < rows; j++) {
			for (int k = rows; k < size - 1; k++) {
				m[j][k] = 1;
			}
		}

		// cols -> super-sink
		for (int j = rows; j < size - 1; j++) {
			m[j][size - 1] = 1;
		}
		//int[][] cuts = new int[cutouts.length][2];
		for (int i = 0; i < cutouts.length; i++) {
			String[] temp2 = cutouts[i].split(",");
			for (String string : temp2) {
				String[] temp = string.split(" ");
				int r = Integer.parseInt(temp[0]);
				int c = Integer.parseInt(temp[1]);
				m[r][c + rows] = 0;
			}
		}

		// search
		int totalFlow = 0;

		int[][] org_m = new int[size][size];
		for (int i = 0; i < org_m.length; i++) {
			for (int j = 0; j < org_m[i].length; j++) {
				org_m[i][j] = m[i][j];
			}
		}

		for (int i = 0; i < rows; i++) {
			int flow = bfs2(m, size, i);
			if (flow > 0) {
				totalFlow += flow;
			}
		}

		// print actual pair
		for (int i = 0; i < rows; i++) {
			for (int k = rows; k < size - 1; k++) {
				if (org_m[i][k] != m[i][k]) {
					System.out.println(i + "->" + k);
				}
			}
		}

		return totalFlow;
	}

	private int bfs(int[][] m, int size, int startRow) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node(startRow));
		
		boolean[] v = new boolean[size];
		Arrays.fill(v, false);

		// for (int i = 0; i < m.length; i++) {
		// System.out.println(Arrays.toString(m[i]));
		// }
		// System.out.println("");

		while (nodes.isEmpty() == false) {
			Node current = nodes.get(0);
			v[current.node] = true;
			nodes.remove(0);

			if (current.node == size - 1) {
				// path found
				Node c = current;
				while (c.parent != null) {
					Node prevNode = c.parent;
					int prev = prevNode.node;
					m[prev][c.node] -= 1;
					m[c.node][prev] += 1;
					c = prevNode;
				}
				return 1;
			}

			for (int i = 0; i < size; i++) {
				if (m[current.node][i] > 0 && v[i] == false) {
					nodes.add(new Node(i, current));
				}
			}
		}

		return 0;
	}

	/*
	 * enhanced bfs version. use parent array instead of Node structure
	 */
	private int bfs2(int[][] m, int size, int startRow) {
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		nodes.add(startRow);

		boolean[] v = new boolean[size];
		Arrays.fill(v, false);

		int[] parent = new int[size + 1];
		Arrays.fill(parent, -1);
		parent[startRow] = startRow;
		// for (int i = 0; i < m.length; i++) {
		// System.out.println(Arrays.toString(m[i]));
		// }
		// System.out.println("");

		while (nodes.isEmpty() == false) {
			int current = nodes.get(0);
			v[current] = true;
			nodes.remove(0);

			if (current == size - 1) {
				// path found

				while (parent[current] != current) {

					int prev = parent[current];
					m[prev][current] -= 1;
					m[current][prev] += 1;
					current = prev;
				}
				return 1;
			}

			for (int i = 0; i < size; i++) {
				if (m[current][i] > 0 && v[i] == false) {
					nodes.add(i);
					parent[i] = current;
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		RookAttack r = new RookAttack();
		int flow;
		flow = r.howMany(8, 8, new String[] {});
		System.out.println(flow);
		if (flow != 8) {
			System.out.println("incorrect answer");
		}

		flow = r.howMany(2, 2, new String[] { "0 0", "0 1", "1 1", "1 0" });
		System.out.println(flow);
		if (flow != 0) {
			System.out.println("incorrect answer");
		}

		flow = r.howMany(3, 3, new String[] { "0 0", "1 0", "1 1", "2 0", "2 1", "2 2" });
		System.out.println(flow);
		if (flow != 2) {
			System.out.println("incorrect answer");
		}

		flow = r.howMany(3, 3, new String[] { "0 0", "1 2", "2 2" });
		System.out.println(flow);
		if (flow != 3) {
			System.out.println("incorrect answer");
		}

		flow = r.howMany(200, 200, new String[] {});
		System.out.println(flow);
		if (flow != 200) {
			System.out.println("incorrect answer");
		}

		flow = r.howMany(300, 300, new String[] { "149 149", "149 151", "151 149", "151 151", "148 148", "148 152",
				"152 148", "152 152", "147 147", "147 153", "153 147", "153 153", "146 146", "146 154", "154 146",
				"154 154", "145 145", "145 155", "155 145", "155 155", "144 144", "144 156", "156 144", "156 156",
				"143 143", "143 157", "157 143", "157 157", "142 142", "142 158", "158 142", "158 158", "141 141",
				"141 159", "159 141", "159 159", "140 140", "140 160", "160 140", "160 160", "139 139", "139 161",
				"161 139", "161 161", "138 138", "138 162", "162 138", "162 162", "137 137", "137 163" });
		System.out.println(flow);
		if (flow != 300) {
			System.out.println("incorrect answer");
		}

		flow = r.howMany(6, 6, new String[] { "0 0", "0 2", "0 4", "1 1", "1 3", "1 5", "2 0", "2 2", "2 4", "3 1",
				"3 3", "3 5", "4 0", "4 2", "4 4", "5 1", "5 3", "5 5", "2 0", "2 2", "2 4" });
		System.out.println(flow);
		if (flow != 6) {
			System.out.println("incorrect answer");
		}
		
	
		flow = r.howMany(15, 25, new String[] { "11 23,6 4,10 2,12 15,13 0,1 3,1 17,13 17,8 4,7 13",
				"3 11,12 6,9 19,0 16,7 8,5 21,1 6,12 3,13 9,11 15", "9 14,11 0,14 14,14 0,3 2,6 11,4 6,7 18,8 21,4 23",
				"10 15,7 21,4 7,13 0,13 16,0 5,9 14,2 23,1 4,13 17",
				"6 6,2 10,5 20,10 10,2 6,14 12,5 5,5 18,8 17,8 22", "9 2,10 16,9 19,3 1,8 5,3 5,4 19,14 15,7 21,9 3",
				"10 17,12 17,2 6,0 19,13 15,9 24,14 17,5 2,2 23", "5 9,2 19,14 16,2 5,14 7,1 8,13 12,11 5,6 19,9 12",
				"13 22,8 22,0 14,13 15,4 1,7 3,7 19,3 13,2 15,9 3",
				"3 4,14 6,5 3,6 19,5 17,8 6,9 23,14 1,1 7,3 12,5 8",
				"5 23,7 13,6 20,13 16,8 8,6 24,12 20,9 11,4 16,13 6",
				"14 21,7 6,13 13,12 8,8 7,5 18,0 12,0 12,6 23,5 16",
				"8 18,9 8,5 7,7 0,0 13,11 15,7 19,11 12,2 15,6 19", "7 14,11 14,10 11,1 9,2 20,9 12,1 2,4 1,12 0,9 5",
				"1 18,3 2,14 17,7 10,12 4,1 19,4 13,0 8,13 11,0 4", "10 14,1 11,11 21,14 2,4 17,2 2,2 6,7 5,5 22,9 18",
				"13 13,14 3,3 20,13 16,0 4,4 7,7 19,2 23,7 7,9 2",
				"9 18,6 2,6 14,7 21,13 12,1 20,11 17,5 24,12 7,7 21",
				"1 18,9 22,4 18,2 10,11 22,11 0,10 5,0 22,10 4,4 21",
				"10 12,2 15,7 5,7 2,13 3,5 17,0 24,11 24,9 21,13 18",
				"8 5,11 0,13 4,13 22,7 6,10 23,1 22,12 9,3 11,5 0", "12 13,8 16,3 14,14 21,11 0,14 14,14 16,10 6,13 3",
				"2 4,14 11,14 18,10 15,2 13,12 18,5 7,3 22,2 24", "12 7,4 22,11 11,9 21,14 7,0 6,3 3,14 8,11 17,10 8",
				"5 3,8 2,4 1,5 9,2 21,10 7,0 13,13 0,11 1,9 10,3 21",
				"13 4,1 15,7 10,10 0,6 3,10 19,11 20,4 21,14 13,0 4",
				"0 22,8 5,11 15,2 21,2 8,0 24,0 16,3 12,9 16,13 14",
				"5 7,9 3,5 4,11 4,2 16,10 6,11 21,14 15,0 17,5 20", "6 0,7 11,6 5,0 15,6 15,8 19,4 7,14 13,14 7,0 24",
				"10 4,0 1,8 24,10 2,0 7,7 4,14 12,11 3,13 22,8 12",
				"10 22,4 16,3 4,0 1,3 21,0 5,13 16,11 15,4 14,13 4", "7 7,4 17,13 9,1 3,8 7,6 4,8 21,13 11,6 1,11 13",
				"3 20,12 12,2 6,12 15,8 12,1 10,9 22,10 20,10 7", "7 24,0 18,14 8,9 10,10 4,7 4,0 8,4 13,14 15,6 11",
				"12 2,8 10,5 10,13 8,13 10,0 1,4 19,6 1,14 9,14 14",
				"12 16,7 11,0 11,5 7,9 19,4 4,8 23,14 11,5 6,4 12", "1 20,8 3,3 8,11 24,6 7,8 21,4 14,13 2,12 19,9 24",
				"4 13,14 16,1 11,4 1,6 22,6 0,10 14,5 19,12 13,6 11",
				"14 4,13 21,13 24,9 20,1 14,12 18,3 8,6 24,14 16", "6 3,0 4,2 8,3 6,5 1,11 12,4 8,14 22,10 7,2 6,13 7",
				"3 1,6 21,6 23,10 15,0 20,13 10,9 2,14 4,9 4,1 15", "14 21,5 14,4 15,8 15,2 22,5 13,4 23,13 6,14 13",
				"1 12,14 3,4 18,1 17,6 8,4 20,14 11,4 4,5 23,10 0", "9 18,8 13,5 21,9 20,14 4,8 10,9 8,11 11,9 23,3 7",
				"11 16,12 15,10 2,3 11,13 22,7 3,11 1,10 3,9 8", "8 15,13 15,1 17,7 16,4 20,14 17,0 10,1 11,7 18",
				"1 3,5 7,3 20,12 23,12 12,5 24,3 15,3 23,10 24,2 9",
				"1 10,2 22,0 6,7 19,14 16,9 12,4 23,7 10,9 13,0 24", "11 0,5 5,0 9,5 15,5 11,8 14,13 6,12 17,2 21,7 2",
				"6 19,1 24,1 12,1 13,1 4,6 20,0 21,9 24,6 21,5 12" });
		System.out.println(flow);
		if (flow != 14) {
			System.out.println("incorret answer");
		}

	}

}
