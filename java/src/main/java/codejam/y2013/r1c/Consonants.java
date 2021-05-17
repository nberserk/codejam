package codejam.y2013.r1c;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * https://code.google.com/codejam/contest/2437488/dashboard#s=p0
 */
public class Consonants {	
	BufferedWriter mWriter;
	int	mCurrentProblem;
	
	public void solve(String inFile, String outFile){	
		BufferedReader reader = null;		
		long start = System.currentTimeMillis();
				
		try {
			reader = new BufferedReader(new FileReader(inFile));
			mWriter = new BufferedWriter(new FileWriter(outFile));
			
			String str = reader.readLine();
			int problemCount = Integer.parseInt(str);			
			for (int i = 1; i <= problemCount; i++) {
				mCurrentProblem = i;

				// parse a problem
				String[] v = reader.readLine().split(" ");
				int n = Integer.parseInt(v[1]);
				// long c = solveSmallProblem(v[0], n);
				long c = solveLargeProblem(v[0], n);

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

		long elapsedTimeMillis = System.currentTimeMillis()-start;
		float elapsedTimeSec = elapsedTimeMillis/1000F;
		System.out.println("elapsed time(sec) :"+ elapsedTimeSec);
	}

	private long solveLargeProblem(String s, int n) {
		long count = 0;
		int N = s.length();
		if (N > 1000) {
			System.out.println(N + "," + n);
		}
		boolean[] v = new boolean[N];
		for (int i = 0; i < v.length; i++) {
			switch (s.charAt(i)) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				v[i] = false;
				break;
			default:
				v[i] = true;
				break;
			}

		}
		// i start pos, j: end pos
		for (int i = 0; i < N; i++) {
			for (int j = i + n - 1; j < N; j++) {

				// if (j - i < n - 1) {
				// continue;
				// }

				int cc = 0; // # of consecutive consonants
				for (int k = i; k <= j; k++) {
					if (v[k]) {
						cc++;
						if (cc >= n) {
							break;
						}
					} else {
						cc = 0;
					}
				}
				if (cc >= n) {
					count += N - j;
					break;
				}

			}
		}

		return count;
	}

	private long solveSmallProblem(String s, int n) {
		long count = 0;
		int N = s.length();
		if (N > 1000) {
			System.out.println(N + "," + n);
		}
		boolean[] v = new boolean[N];
		for (int i = 0; i < v.length; i++) {
			switch (s.charAt(i)) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				v[i] = false;
				break;
			default:
				v[i] = true;
				break;
			}

		}
		// i start pos, j: end pos
		for (int i = 0; i < N; i++) {
			for (int j = i + n - 1; j < N; j++) {

				// if (j - i < n - 1) {
				// continue;
				// }

				int cc = 0; // # of consecutive consonants
				for (int k = i; k <= j; k++) {
					if (v[k]) {
						cc++;
						if (cc >= n) {
							break;
						}
					} else {
						cc = 0;
					}
				}
				if (cc >= n) {
					count += N - j;
					break;
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
		Consonants b = new Consonants();
		String dir = "./src/codejam2013/r1c/";
		String fn = "A-large-practice.in";
		// long r = b.solveAProblem(5, 2, new int[] { 1, 2 });
		// System.out.println(r);
		b.solve(dir + fn, dir + fn.replace(".in", ".out"));
	}

}
