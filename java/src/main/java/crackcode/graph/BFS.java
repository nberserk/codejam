package crackcode.graph;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import codejam.lib.CheckUtil;

public class BFS {


	public static void main(String[] args) {
		char[][] m = { { '0', '0', '0' }, { 'B', 'G', 'G' }, { 'B', '0', '0' } };

		findMinStepToGuard(m);
		CheckUtil.check('2', m[0][0]);
		CheckUtil.check('1', m[0][1]);
		CheckUtil.check('1', m[0][2]);

		char[][] m2 = { { '0', '0', '0', 'G' }, { 'B', 'G', 'G', '0' }, { 'B', 'B', '0', '0' }, { '0', '0', '0', '0' } };
		findMinStepToGuard(m2);
		CheckUtil.check('4', m2[3][0]);
	}

	// http://www.careercup.com/question?id=4716965625069568
	static void findMinStepToGuard(char[][] m) {
		int N = m.length;
		Queue<Point> q = new LinkedList<Point>();
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (m[y][x] == 'G') {
					q.add(new Point(x, y));
				}
			}
		}

		while (q.size() > 0) {
			Point cur = q.poll();

			int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
			for (int i = 0; i < d.length; i++) {
				int nx = cur.x + d[i][0];
				int ny = cur.y + d[i][1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				if (m[ny][nx] == 'B' || m[ny][nx] == 'G')
					continue;

				if (m[ny][nx] == '0') {
					m[ny][nx] = (char) (m[cur.y][cur.x] == 'G' ? '1' : m[cur.y][cur.x] + 1);
					q.add(new Point(nx, ny));
				}
			}
		}

	}

}
