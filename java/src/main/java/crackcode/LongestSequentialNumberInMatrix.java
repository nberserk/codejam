// from , http://www.careercup.com/question?id=5147801809846272

package crackcode;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

public class LongestSequentialNumberInMatrix {

	public static void main(String[] args) {

		int[][] a = { { 1, 5, 9 }, { 2, 3, 8 }, { 4, 6, 7 } };

		int n = a.length;

		boolean[][] visited = new boolean[n][n];

        LinkedList<Integer> max = null, cur = null;
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {

                cur = dfs(a, visited, x, y);
                if (cur == null)
                    continue;
                if (max == null || cur.size() > max.size()) {
                    max = new LinkedList<Integer>(cur);
				}
			}
		}

        System.out.println(max.toString());
	}

    static LinkedList<Integer> dfs(int[][] a, boolean[][] v, int x, int y) {
        if (v[y][x]) {
            return null;
        }
		int N = a.length;

        LinkedList<Integer> path = new LinkedList<Integer>();

		int[][] diff = {{-1,0}, {1,0}, {0,1}, {0,-1}};
        ArrayList<Point> q = new ArrayList<Point>();
        Point cur = new Point(x, y);
        q.add(cur);

        while (!q.isEmpty()){
            cur = q.get(0);
            q.remove(0);
            v[cur.y][cur.x] = true;
            int curV = a[cur.y][cur.x];

            if (path.size() == 0 || curV < path.getFirst()) {
                path.addFirst(curV);
            } else {
                path.addLast(curV);
            }

            int nx,ny;
            for (int i = 0; i < 4; i++) {
                nx = cur.x + diff[i][0];
                ny = cur.y + diff[i][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                if (v[ny][nx])
                    continue;
                int d = a[ny][nx] - a[cur.y][cur.x];
                if (d == 1) { // increasing
                    Point np = new Point(nx, ny);
                    q.add(np);
                } else if (d == -1) {
                    Point np = new Point(nx, ny);
                    q.add(np);
                }
            }
        }

        return path;
	}


}
