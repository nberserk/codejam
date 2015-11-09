package topcoder.graph.bfs;

import java.awt.Point;
import java.util.TreeSet;

public class RoboCourier {
	static class Node implements Comparable<Node> {
		int x, y;
		int direction;
		int time;
		int accel;
		String action;
		Node parent;

		public Node(int x, int y, int direction, int time, String actions,
				int accel) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.action = actions;
			this.time = time;
			this.accel = accel;
		}

		@Override
		public int compareTo(Node o) {
			if (this.time < o.time) {
				return -1;
			} else if (this.time > o.time) {
				return 1;
			}

			if (this.x < o.x) {
				return -1;
			} else if (this.x > o.x) {
				return 1;
			}
			if (this.y < o.y) {
				return -1;
			} else if (this.y > o.y) {
				return 1;
			}

			return 0;
		}

		@Override
		public String toString() {
			return String.format("%d,%d,%d,%d,%s", x, y, direction, time,
					action);
		}
	}

	public static Point[] mDirections = new Point[] { new Point(0, 2), new Point(1, 1), new Point(1, -1),
			new Point(0, -2), new Point(-1, -1), new Point(-1, 1) };

	public static int timeToDeliver(String[] paths) {
		boolean[][][] m = new boolean[1000][1000][7]; // map 0-5:connected path
														// to another, 6 :
														// enabled by scout

		// generate map
		Point cur = new Point(500,500);
		m[cur.x][cur.y][6] = true;
		
		int directionIndex = 0;
		for (String path : paths) {
			for (int i = 0; i < path.length(); i++) {
				char c = path.charAt(i);
				if (c == 'F') {
					m[cur.x][cur.y][6] = true;
					m[cur.x][cur.y][directionIndex]=true;					
					cur.translate(mDirections[directionIndex].x, mDirections[directionIndex].y);
					m[cur.x][cur.y][6] = true;
					int oppositeDir = (directionIndex + 3) % mDirections.length;
					m[cur.x][cur.y][oppositeDir] = true;
				} else if (c == 'L') {
					directionIndex += 5;
					directionIndex %= mDirections.length;
				} else if (c == 'R') {
					directionIndex += 1;
					directionIndex %= mDirections.length;
				}
			}
		}

		// boolean[] ref = m[500][506];
		// for (int j = 530; j >= 480; j--) {
		// for (int i = 480; i < 530; i++) {
		// if (i == 500 && j == 500) {
		// System.out.print("S");
		// } else if (i == cur.x && j == cur.y) {
		// System.out.print("E");
		// } else {
		// System.out.print(m[i][j][6] == true ? "O" : ".");
		// }
		//
		// }
		// System.out.println("");
		// }

		// search
		Point dest = cur;
		TreeSet<Node> opened = new TreeSet<Node>();
		opened.add(new Node(500, 500, 0, 0, "", -1));

		boolean[][] v = new boolean[1000][1000];
		while (!opened.isEmpty()) {
			Node current = opened.pollFirst();

			if (v[current.x][current.y]) {
				continue;
			}

			v[current.x][current.y] = true;
			// System.out.println(current);

			if (current.x == dest.x && current.y == dest.y) {
				if (current.accel == 1) {
					current.time += 2;
				}
				// System.out.println("solution::::");
				// Node next = current;
				// while (next != null) {
				// System.out.println(next);
				// next = next.parent;
				// }
				return current.time;
			}

			for (int i = 0; i < mDirections.length; i++) {
				int xnext = current.x;
				int ynext = current.y;
				xnext += mDirections[i].x;
				ynext += mDirections[i].y;
				String action = null;

				if (m[xnext][ynext][6] && m[current.x][current.y][i]) {
					// change dir
					int time = 0;
					int accel = 0;
					if (current.direction == i) {
						// same direction
						if (current.parent == null) {
							time += 4;
						} else {
							time += 2;
							accel = 1;
						}
						action = "F";

					} else {
						int diff = Math.abs(current.direction - i);
						if (diff == 5 || diff == 1) {
							time += 3 + 4;
							if (current.direction - i > 0) {
								action = "LF";
							} else {
								action = "RF";
							}
						} else if (diff == 2 || diff == 4) {
							time += 6 + 4;
							if (current.direction - i > 0) {
								action = "LLF";
							} else {
								action = "RRF";
							}
						} else if (diff == 3) {
							time += 9 + 4;
							if (current.direction - i > 0) {
								action = "LLLF";
							} else {
								action = "RRRF";
							}
						} else {
							System.out.println("can't be here");
						}
						// handle last movement
						if (current.accel == 1) {
							time += 2;
						}
					}
					Node node = new Node(xnext, ynext, i, current.time + time,
							action, accel);
					node.parent = current;
					opened.add(node);
				}

			}

		}

		return -1;

	}

	public static void main(String[] args) {

		int time = timeToDeliver(new String[] { "FRRFLLFLLFRRFLF" });
		System.out.println(time);

		time = timeToDeliver(new String[] { "RFLLF" });
		System.out.println(time);

		time = timeToDeliver(new String[] { "FLFRRFRFRRFLLFRRF" });
		System.out.println(time);

		time = timeToDeliver(new String[] { "FFFFFFFFFRRFFFFFFRRFFFFF",
				"FLLFFFFFFLLFFFFFFRRFFFF" });
		System.out.println(time);

		time = timeToDeliver(new String[] {
				"RFLLFLFLFRFRRFFFRFFRFFRRFLFFRLRRFFLFFLFLLFRFLFLRFF",
				"RFFLFLFFRFFLLFLLFRFRFLRLFLRRFLRFLFFLFFFLFLFFRLFRLF",
				"LLFLFLRLRRFLFLFRLFRF" });
		System.out.println(time);

		time = timeToDeliver(new String[] {
				"LLFLFRLRRLRFFLRRRRFFFLRFFRRRLLFLFLLRLRFFLFRRFFFLFL",
				"RLFFRRLRLRRFFFLLLRFRLLRFFLFRLFRRFRRRFRLRLRLFFLLFLF",
				"FRFLRFRRLLLRFFRRRLRFLFRRFLFFRLFLFLFRLLLLFRLLRFLLLF",
				"FFLFRFRRFLLFFLLLFFRLLFLRRFRLFFFRRFFFLLRFFLRFRRRLLR",
				"FFFRRLLFLLRLFRRLRLLFFFLFLRFFRLRLLFLRLFFLLFFLLFFFRR",
				"LRFRRFLRRLRRLRFFFLLLLRRLRFFLFRFFRLLRFLFRRFLFLFFLFR",
				"RFRRLRRFLFFFLLRFLFRRFRFLRLRLLLLFLFFFLFRLLRFRLFRLFR",
				"LLFLFRLFFFFFFFRRLRLRLLRFLRLRRRRRRRRLFLFLFLRFLFRLFF",
				"RLFRRLLRRRRFFFRRRLLLLRRLFFLLLLLRFFFFRFRRLRRRFFFLLF",
				"FFFFLRRLRFLLRRLRLRFRRRRLFLLRFLRRFFFRFRLFFRLLFFRRLL" });
		System.out.println(time);
	}

}
