package codejam2013.r1c;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Osmos {	
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

				// parse a problem
				String[] v = mReader.readLine().split(" ");
				int s = Integer.parseInt(v[0]);
				v = mReader.readLine().split(" ");

				int[] num = new int[v.length];
				for (int j = 0; j < v.length; j++) {
					num[j] = Integer.parseInt(v[j]);
				}

				Arrays.sort(num);

				int c = solveAProblem(s, num);
				String out = String.format("%d", c);
				writeSolution(out);
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

	private int solveAProblem(int s, int[] num) {
		int count = 0;

		print(s + "-" + Arrays.toString(num));
		for (int i = 0; i < num.length; i++) {

			int n = num[i];
			if (s > n) {
				s += n;
			} else {
				if (s == 1) {
					count++;
					continue;
				}
				int add = 0;
				int t = s;
				while (t <= n) {
					t += t - 1;
					add++;
				}
				int remove = num.length - (i);
				if (add > remove) {
					count += remove;
				} else {
					count += add;
					s = t;
				}
			}
		}

		return count;
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
		Osmos o = new Osmos();
		String dir = "./src/codejam2013/r1a/";
		// o.solve(dir + "A-ex.in", dir + "A-ex.out");
		o.solve(dir + "A-small-attempt2.in", dir + "A-small-attempt2.out");

	}

}
