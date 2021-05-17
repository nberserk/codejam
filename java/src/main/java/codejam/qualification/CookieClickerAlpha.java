package codejam.qualification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CookieClickerAlpha {	
	BufferedWriter mWriter;
	int	mCurrentProblem;
	
	public void solve(String inFile, String outFile){	
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
				double C = Double.parseDouble(v[0]);
				double F = Double.parseDouble(v[1]);
				double X = Double.parseDouble(v[2]);
				
				// parse a problem
				solveSmallProblem(C, F, X);
				// solveLargeProblem();
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


	private double solveSmallProblem(double c, double f, double x) {
		int low = 0;
		int high = (int) (x / 2.0);

		int mid;
		double gainFarm, costFarm;
		while (low < high) {
			mid = (low + high) / 2;
			gainFarm = x / (2.0 + mid * f) - x / (2.0 + (mid + 1.0) * f);
			costFarm = c / (2.0 + mid * f);
			if (gainFarm >= costFarm) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}

		int farm = low;
		print("farm=" + farm);

		double time = getTime(c, f, x, farm);
		// if (farm > 0) {
		double time2 = getTime(c, f, x, farm + 1);
			if (time2 < time) {
				time = time2;
			}
		// }

		// double time2 = getTime(c, f, x, farm + 1);
		// double time3 = getTime(c, f, x, farm + 2);
		String format = "0.0000000";
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		writeSolution(df.format(time));
		return time;
	}

	private double getTime(double c, double f, double x, int farm) {
		double time = 0;
		for (int i = 0; i < farm; i++) {
			time += c / (2 + i * f);
		}
		time += x / (2 + farm * f);
		return time;
	}


	private void solveLargeProblem() {
	}

	public void writeSolution(String s){
		String outStr = "Case #"+ mCurrentProblem+": " +s + "\n";
		print(outStr);
		if (mWriter != null) {
			try {
				mWriter.write(outStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void print(String str){
		System.out.println(str);
	}

	public static void main(String[] args) {
		CookieClickerAlpha b = new CookieClickerAlpha();
		String dir = "./src/codejam2014/qualification/";
		String small = "B-small-attempt3.in";

		// b.solveSmallProblem(30.0, 1.0, 2.0);
		// b.solveSmallProblem(30.0, 2.0, 100.0);
		// b.solveSmallProblem(30.5, 3.14159, 1999.1999);
		// b.solveSmallProblem(500.0, 4.0, 2000.0);

		// System.out.println(r);
		b.solve(dir + small, dir + small.replace(".in", ".out"));
		// b.solve(dir + large, dir + large.replace(".in", ".out"));
	}

}
