package codejam.lib;

public class CloestPoints {

	class Point {
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		int x, y;
	}

	public static void main(String[] args) {
		CloestPoints p = new CloestPoints();
		int[][] pt = { { 2, 3 }, { 12, 30 }, { 40, 50 }, { 5, 1 }, { 12, 10 },
				{ 3, 4 } };
		p.solve(pt);

	}

	private void solve(int[][] pt) {


	}

}
