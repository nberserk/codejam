package codejam.lib.dp;

public class Parenthesization {

	public static void main(String[] args) {
		Parenthesization p = new Parenthesization();
		Matrix[] m = new Matrix[] { new Matrix(2, 3), new Matrix(3, 4),
				new Matrix(4, 5), new Matrix(5, 6) };

		p.solve(m);

	}

	private void print(String s) {
		System.out.println(s);
	}

	private void solve(Matrix[] m) {

		int result = findMin(m, 0, m.length-1);
		System.out.println(result);
	}

	/*
	 * start, end : index based
	 */
	private int findMin(Matrix[] m, int start, int end) {
		if (start==end) {
			return 0;
		}
		if (end-start==1) {
			return m[start].row*m[end].col*m[start].col; 
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = start; i < end; i++) {
			int cur = findMin(m, start, i);
			cur += m[start].row * m[i].col * m[end].col;
			cur += findMin(m, i + 1, end);
			if (cur < min) {
				min = cur;
			}
		}
		print(start + "-" + end + ":" + min);
		return min;
	}

	static class Matrix {
		int row;
		int col;

		public Matrix(int r, int c) {
			row = r;
			col = c;
		}
	}
}
