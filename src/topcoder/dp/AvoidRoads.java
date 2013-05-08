package topcoder.dp;

/*
 * http://community.topcoder.com/stat?c=problem_statement&pm=1889&rd=4709
 */
public class AvoidRoads {


	public long numWays(int width, int height, String[] bad) {

		long count = 0;

		int i = width;
		int j = height;
		count = ways(i - 1, j) + ways(i, j - 1);

		return count;
	}

	private int ways(int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		}

		// use cache

		return 0;
	}

	public static void main(String[] args) {



	}

}
