package codejam.lib.dp;

public class KnapSack {
	public static void main(String[] args) {

        solve(50, new int[] { 10, 20, 30 }, new int[] { 60, 100, 120 });
        solve(100, new int[] { 10, 20, 30, 40, 20 }, new int[] { 60, 100, 120,
                10, 110 });

	}

    public static void solve(int S, int[] size, int[] value) {
        int maxValue = solveTable(S, size, value);
        System.out.println(maxValue + " <- using table");

        maxValue = solveRecursive(S, size, value, 0);
        System.out.println(maxValue + " <- using recursive");
    }

	public static int solveTable(int S, int[] size, int[] value) {
		int n = size.length + 1;
		int t[][] = new int[n][S + 1];

		// prefix based
		for (int i = 0; i < S; i++) {
			t[n - 1][i] = 0;
		}
		for (int i = 0; i < n; i++) {
			t[i][0] = 0;
		}

		for (int i = n - 2; i >= 0; i--) {
			for (int j = S; j >= 0; j--) {
				t[i][j] = t[i + 1][j];
				if (j - size[i] >= 0) {
					t[i][j] = Math.max(t[i][j], t[i + 1][j - size[i]]
							+ value[i]);
				}
                // System.out.println(i + "," + j + "=" + t[i][j]);
			}
		}

		return t[0][S];
	}

    public static int solveRecursive(int S, int[] size, int[] value, int i) {
        int cost = 0;
        if (i >= size.length) {
            return 0;
        }

        cost = solveRecursive(S, size, value, i + 1);
        if (S >= size[i]) {
            cost = Math.max(cost,
                    solveRecursive(S - size[i], size, value, i + 1) + value[i]);
        }

        return cost;
	}

}
