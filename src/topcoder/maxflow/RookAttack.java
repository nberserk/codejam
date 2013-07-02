package topcoder.maxflow;

import java.util.ArrayList;
import java.util.Arrays;


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

	int howMany(int rows, int cols, String[] cutouts) {
		
		
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
			String[] temp = cutouts[i].split(" ");
			int r = Integer.parseInt(temp[0]);
			int c = Integer.parseInt(temp[1]);
			m[r][c + rows] = 0;
		}
		
		
		

		// search
		int totalFlow = 0;
		while (true) {
			int flow = bfs(m, size);
			if (flow == 0) {
				break;
			}
			totalFlow += flow;
		}

		return totalFlow;

	}

	private int bfs(int[][] m, int size) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node(0));
		
		boolean[] v = new boolean[size];
		Arrays.fill(v, false);


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

	public static void main(String[] args) {
		RookAttack r = new RookAttack();
		int flow;
		// flow = r.howMany(8, 8, new String[] {});
		// System.out.println(flow);
		//
		// flow = r.howMany(2, 2, new String[] { "0 0", "0 1", "1 1", "1 0" });
		// System.out.println(flow);
		//
		// flow = r.howMany(3, 3, new String[] { "0 0", "1 0", "1 1", "2 0",
		// "2 1", "2 2" });
		// System.out.println(flow);

		flow = r.howMany(3, 3, new String[] { "0 0", "1 2", "2 2" });
		System.out.println(flow);

	}

}
