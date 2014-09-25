// from , http://www.careercup.com/question?id=5147801809846272

package crackcode;

import java.awt.Point;
import java.util.ArrayList;

public class LongestSequentialNumberInMatrix {

	public static void main(String[] args) {

		int[][] a = { { 1, 5, 9 }, { 2, 3, 8 }, { 4, 6, 7 } };

		int n = a.length;
		ArrayList<Point> max = null;
		boolean[][] visited = new boolean[n][n];

		ArrayList<Point> path = new ArrayList<Point>(n);
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				path.clear();
				dfs(a, visited, x, y, path);
				if (max == null || path.size() > max.size()) {
					max = new ArrayList<Point>(path);
				}
			}
		}

	}

	static void dfs(int[][] a, boolean[][] v, int x, int y,
			ArrayList<Point> path) {
		int N = a.length;

		Point cur = new Point(x,y);
		path.add(cur);
		v[y][x] = true;
		
		int[][] diff = {{-1,0}, {1,0}, {0,1}, {0,-1}};
		
		int nx,ny;
		for (int i = 0; i < 4; i++) {
			nx = x + diff[i][0];
			ny = y + diff[i][1];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (v[ny][nx])
				continue;
			if (a[ny][nx] - a[y][x] == 1) {
				dfs(a, v, nx, ny, path);
				return;
			}
		}
	}
		

}
