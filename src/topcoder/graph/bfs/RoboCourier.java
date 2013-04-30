package topcoder.graph.bfs;

import java.awt.Point;
import java.util.TreeSet;

public class RoboCourier {
	static class Node implements Comparable<Node> {
		int x, y;
		int direction, prevDirection;
		int time;
		int accel;
		Node parent;

		public Node(int x, int y, int direction, int time, int prevDirection, int accel) {
			this.x = x;
			this.y = y;
			this.direction = direction;
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

			return 0;
		}

		@Override
		public String toString() {
			return String.format("%d,%d,%d,%d", x, y, direction, time);
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
					cur.translate(mDirections[directionIndex].x, mDirections[directionIndex].y);
					m[cur.x][cur.y][6] = true;
					m[cur.x][cur.y][directionIndex] = true;
				} else if (c == 'L') {
					directionIndex += 5;
					directionIndex %= mDirections.length;
				} else if (c == 'R') {
					directionIndex += 1;
					directionIndex %= mDirections.length;
				}
			}
		}

		// search
		Point dest = cur;
		TreeSet<Node> opened = new TreeSet<Node>();
		opened.add(new Node(500, 500, 0, 0, -1, 0));

		boolean[][] v = new boolean[1000][1000];
		while (!opened.isEmpty()) {
			Node current = opened.pollFirst();

			if (v[current.x][current.y]) {
				continue;
			}

			v[current.x][current.y] = true;
			if (current.x == dest.x && current.y == dest.y) {
				Node next = current;
				while (next != null) {
					System.out.println(next);
					next = next.parent;
				}
				return current.time;
			}

			for (int i = 0; i < mDirections.length; i++) {
				int x = current.x;
				int y = current.y;
				x += mDirections[i].x;
				y += mDirections[i].y;

				if (m[x][y][6] && m[x][y][i]) {
					// change dir
					int time = 0;
					int accelCount = current.accel;
					if (current.direction == i) {
						// same direction
						accelCount++;
						if (accelCount == 1) {
							time += 4;
						} else {
							time += 2;
						}

					} else {
						accelCount = 0;
						int diff = Math.abs(current.direction - i);
						if (diff == 5 || diff == 1) {
							time += 3 + 4;
						} else if (diff == 2 || diff == 4) {
							time += 6 + 4;
						} else if (diff == 3) {
							time += 9 + 4;
						} else {
							System.out.println("can't be here");
						}
						// handle last movement
						if (current.accel > 0) {
							time += 2;
						}
					}
					Node node = new Node(x, y, i, current.time + time, current.direction, accelCount);
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

		time = timeToDeliver(new String[] { "FFFFFFFFFRRFFFFFFRRFFFFF", "FLLFFFFFFLLFFFFFFRRFFFF" });
		System.out.println(time);

	}

}
