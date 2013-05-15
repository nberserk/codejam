package codejam2013.r1a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Energy {	
	BufferedWriter mWriter;
	int	mCurrentProblem;
	
	public void solve(String inFile, String outFile){	
		BufferedReader mReader = null;		
		long start = System.currentTimeMillis();
				
		try {
			mReader = new BufferedReader(new FileReader(inFile));
			mWriter = new BufferedWriter(new FileWriter(outFile));
			
			String str = mReader.readLine();
			int problemCount = Integer.parseInt(str);			
			for (int i = 1; i <= problemCount; i++) {
				mCurrentProblem = i;

				String[] v = mReader.readLine().split(" ");
				int E = Integer.parseInt(v[0]);
				int R = Integer.parseInt(v[1]);
				v = mReader.readLine().split(" ");
				int[] n = new int[v.length];
				for (int j = 0; j < n.length; j++) {
					n[j] = Integer.parseInt(v[j]);
				}

				// parse a problem
				long r = solveSmallProblem(E, R, n);
				String o = String.format("(%d,%d)-%s", E, R, Arrays.toString(n));
				print(o);
				writeSolution(String.format("%d", r));

			}
						
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				mReader.close();
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

		long elapsedTimeMillis = System.currentTimeMillis()-start;
		float elapsedTimeSec = elapsedTimeMillis/1000F;
		System.out.println("elapsed time(sec) :"+ elapsedTimeSec);
	}

	private long solveSmallProblem(int e, int r, int[] n) {

		long gain = 0;
		int max = Integer.MIN_VALUE;
		int delta = 0;
		for (int i = 0; i < n.length; i++) {
			if (r >= e) {
				gain += e * n[i];
			} else {
				int g = n[i] * r;
				int gmax = n[i] * e;
				if (gmax > max) {
					max = gmax;
					gain += gmax - delta;
					delta = gmax - g;
				} else {
					gain += g;
				}
			}

		}

		return gain;
	}

	public void writeSolution(String s){
		String outStr = "Case #"+ mCurrentProblem+": " +s + "\n";
		print(outStr);
		try {
			mWriter.write(outStr);
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}

	public static void print(String str){
		System.out.println(str);
	}

	public static void main(String[] args) {
		Energy b = new Energy();
		String dir = "./src/codejam2013/r1a/";
		String fn = "B-small-practice.in";
		// long r = b.solveAProblem(5, 2, new int[] { 1, 2 });
		// System.out.println(r);
		b.solve(dir + fn, dir + fn.replace(".in", ".out"));
	}
}
