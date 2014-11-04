package crackcode;

import codejam.lib.CheckUtil;

// http://www.careercup.com/question?id=15556758
public class ProbabilityAlive {

	public static void main(String[] args) {

		CheckUtil.check(0.0, probabilityOfAlive(0, 0, 1, 1));
		CheckUtil.check(0.5, probabilityOfAlive(0, 0, 2, 1));
		CheckUtil.check(1.0, probabilityOfAlive(2, 2, 5, 2));

	}

	static double probabilityOfAlive(int x, int y, int n, int step) {
		if (x < 0 || y < 0 || x >= n || y >= n)
			return 0.0;
		if (step == 0)
			return 1.0;

		double alive = 0.0;
		alive += 0.25 * probabilityOfAlive(x - 1, y, n, step - 1);
		alive += 0.25 * probabilityOfAlive(x + 1, y, n, step - 1);
		alive += 0.25 * probabilityOfAlive(x, y - 1, n, step - 1);
		alive += 0.25 * probabilityOfAlive(x, y + 1, n, step - 1);

		return alive;
	}
}
