package codejam2013.r1a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * https://code.google.com/codejam/contest/2418487/dashboard#s=p0
 */

public class Bullseye {
	BufferedWriter mWriter;
	int mCurrentProblem;

	public void solve(String inFile, String outFile) {
		BufferedReader reader = null;
		// long start = System.currentTimeMillis();

		try {
			reader = new BufferedReader(new FileReader(inFile));
			mWriter = new BufferedWriter(new FileWriter(outFile));

			String str = reader.readLine();
			int problemCount = Integer.parseInt(str);
			for (int i = 1; i <= problemCount; i++) {
				mCurrentProblem = i;

				String[] v = reader.readLine().split(" ");
				long r = Long.parseLong(v[0]);
				long t = Long.parseLong(v[1]);
				// parse a problem
				// solveSmallProblem();

				long c = solveLargeProblem(r, t);
				writeSolution(String.format("%d", c));
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				reader.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
			try {
				mWriter.flush();
				mWriter.close();
			} catch (Exception e2) {
				System.out.println(e2);

			}
		}

		// long elapsedTimeMillis = System.currentTimeMillis()-start;
		// float elapsedTimeSec = elapsedTimeMillis/1000F;
		// System.out.println("elapsed time(sec) :"+ elapsedTimeSec);
	}

	private long solveLargeProblem(long r, long t) {


		long lo = 1, hi = 2;
		while (isValid(r, t, hi)) {
			lo = hi;
			hi *= 2;
		}

		while (lo < hi) {
			if (hi - lo == 1) {
				break;
			}
			long mid = (hi + lo) / 2;
			if (isValid(r, t, mid)) {
				lo = mid;
				// lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		long out = lo;
		if (isValid(r, t, hi)) {
			out = hi;
		}

		return out;
	}

	private boolean isValid(long r, long t, long mid) {
		return mid * (2 * r + 2 * mid - 1) <= t;
	}

	private void solveSmallProblem() {

	}

	public void writeSolution(String s) {
		String outStr = "Case #" + mCurrentProblem + ": " + s + "\n";
		print(outStr);
		try {
			mWriter.write(outStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void print(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {

		// if ((Long.MAX_VALUE > 2000000000000000000l)) {
		// print("");
		// }

		Bullseye b = new Bullseye();
		String dir = "./src/codejam2013/r1a/";
		// String fn = "A-small-practice.in";
		String fn = "A-large-practice.in";

		b.solve(dir + fn, dir + fn.replace(".in", ".out"));
	}

}
