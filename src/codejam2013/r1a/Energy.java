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
	private int mE;
	private int mR;
	private int[] mActivivites;
	
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
				mE = E;
				mR = R;
				mActivivites = n;
				long r = solveSmallProblem(0, n.length - 1, E, 0);
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

	/*
	 * se : start energy after recovered, ee : end energy after recovery
	 */
	private long solveSmallProblem(int s, int e, int se, int ee) {

		if (s==e) {
			int canMake = se + mR;
			long r = 0;
			if (canMake >= ee) {
				r = (canMake - ee) * mActivivites[s];
			}
			print(String.format("%d-%d(%d-%d):%d", s, e, se, ee, r));
			return r;
		}

		// find max
		int max = Integer.MIN_VALUE;
		int maxi = -1;
		for (int i = s; i <= e; i++) {
			if (mActivivites[i] > max) {
				max = mActivivites[i];
				maxi = i;
			}
		}
		
		//
		int required = ee - se;
		int step = maxi - s;

		if (required <= step * mR) {
			// maxE=mE;
			long r = mE * mActivivites[maxi];
			if (maxi != s) {
				r += solveSmallProblem(s, maxi - 1, se, mE);
			}
			if (maxi != e) {
				r += solveSmallProblem(maxi + 1, e, mR, ee);
			}
			print(String.format("%d-%d(%d-%d):%d", s, e, se, ee, r));
			return r;
		}else{
			long r = (se + step * mR) * mActivivites[maxi];
			if (maxi != e) {
				r += solveSmallProblem(maxi + 1, e, mR, ee);
			}
			print(String.format("%d-%d(%d-%d):%d", s, e, se, ee, r));
			return r;
		}
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
