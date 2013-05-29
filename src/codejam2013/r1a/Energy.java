package codejam2013.r1a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/*
 * small problem ok , large not
 */
public class Energy {	
	BufferedWriter mWriter;
	int	mCurrentProblem;
	private long mE;
	private long mR;
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
				long E = Long.parseLong(v[0]);
				long R = Long.parseLong(v[1]);
				v = mReader.readLine().split(" ");
				int[] n = new int[v.length];
				for (int j = 0; j < n.length; j++) {
					n[j] = Integer.parseInt(v[j]);
				}

				// parse a problem
				mE = E;
				mR = R;
				mActivivites = n;
				String o = String.format("(%d,%d)-%s", E, R, Arrays.toString(n));
				print(o);
				long r = solveSmallProblem(0, n.length - 1, E, R);

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
	private long solveSmallProblem(int s, int e, long se, long ee) {

		if (s==e) {
			long r = (se + mR - ee) * mActivivites[s];
			print(String.format("%d-%d(%d-%d):%d", s, e, se, ee, r));
			return r;
		}

		// find max
		long max = Integer.MIN_VALUE;
		int maxi = -1;
		for (int i = s; i <= e; i++) {
			if (mActivivites[i] > max) {
				max = mActivivites[i];
				maxi = i;
			}
		}
		
		
		//
		
		long preStep = maxi - s;
		long postStep = e - maxi;
		
		// pre
		long newse = se + preStep * mR;
		if (newse>=mE) {
			newse=mE;
		}
		
		long newee = ee - (postStep * mR);
		if (newee < mR) {
			newee = mR;
		}

		long gain = (newse + mR - newee) * (long) mActivivites[maxi];
		if (newse < 0 || newee < 0 || gain < 0) {
			System.out.println("ddd");
		}

		if (preStep!=0) {
			gain += solveSmallProblem(s, maxi-1, se, newse);			
		}
		if (postStep!=0) {
			gain += solveSmallProblem(maxi+1, e, newee, ee);
		}
		
		// post
		print(String.format("%d-%d(%d-%d):%d", s, e, se, ee, gain));
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
		String small = "B-small-practice.in";
		String large = "B-large-practice.in";
		// b.solve(dir + small, dir + small.replace(".in", ".out"));
		b.solve(dir + large, dir + large.replace(".in", ".out"));
	}
}
