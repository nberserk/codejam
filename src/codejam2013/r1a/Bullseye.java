package codejam2013.r1a;

import java.io.BufferedReader;
import java.io.IOException;

import codejam.lib.CodejamBase;

public class Bullseye extends CodejamBase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bullseye b = new Bullseye();
		String dir = "./src/codejam2013/r1a/";
		b.solve(dir + "A-ex.in", dir + "A-ex.out");
	}
	
	@Override
	public void parseAProblem(BufferedReader reader) {
		try {
			String[] values = reader.readLine().split(" ");
			double r = Double.parseDouble(values[0]);
			double t = Double.parseDouble(values[1]);

			r = 10000000000000000.0;
			t = 1000000000000000000.0;

			double lo = 1;
			double hi = Math.pow(10, 19);
			// hi = 100;
			
			double ar = 2 * r + 1;

			while (lo < hi) {

				double mid = (lo + hi) / 2;
				double s = mid * (2 * ar + (mid - 1) * 4) / 2;// - sr;
				if (s > t) {
					hi = mid;
				} else {
					lo = mid;
				}

				if (Math.abs(hi - lo) < 0.0000001) {
					break;
				}
			}
			int count = (int) Math.round(lo);
			if (count - lo >= 0.000001) {
				count -= 1;
			}
			String out = String.format("%d, %f", count, lo);
			writeSolution(out);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
