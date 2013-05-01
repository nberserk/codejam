package topcoder.graph.bfs;

/*
 * http://community.topcoder.com/stat?c=problem_statement&pm=2274&rd=5009
 */
public class BombMan {

	static class Node implements Comparable<Node> {
		int x, y;
		int time;
		int bomb;

		@Override
		public int compareTo(Node o) {
			if (this.time < o.time) {
				return -1;
			} else if (this.time > o.time) {
				return 1;
			}
			if (x < o.x) {
				return -1;
			} else if (x > o.x) {
				return 1;
			}
			if (y < o.y) {
				return -1;
			} else if (y > o.y) {
				return 1;
			}
			return 0;
		}

	}

	public static int shortestPath(String[] maze, int bombs) {
		char m = new char[maze]

		return -1;
	}

	public static void main(String[] args) {

	}


}
