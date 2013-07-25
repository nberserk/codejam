package codejam2013.r1a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GoodLuck {	
	BufferedWriter mWriter;
	int	mCurrentProblem;
	private int N;
	private int M;
	private int K;
	
	public void solve(String inFile, String outFile){	
		BufferedReader reader = null;		
		// long start = System.currentTimeMillis();
				
		try {
			reader = new BufferedReader(new FileReader(inFile));
			mWriter = new BufferedWriter(new FileWriter(outFile));
			
			String str = reader.readLine();

			String[] v = reader.readLine().split(" ");
			int R = Integer.parseInt(v[0]);
			N = Integer.parseInt(v[1]);
			M = Integer.parseInt(v[2]);
			K = Integer.parseInt(v[3]);

			writeSolution("Case #1:");
			for (int i = 1; i <= R; i++) {			
				mCurrentProblem = i;
				
				v = reader.readLine().split(" ");
				int[] n = new int[K];
				for (int j = 0; j < n.length; j++) {
					n[j] = Integer.parseInt(v[j]);
				}				

				// parse a problem
				int[] so = solveSmallProblem(n);
				for (int j = 0; j < so.length; j++) {
					if (so[j] == -1) {
						so[j] = 2;
					}
				}
				String out = String.format("%d%d%d", so[0], so[1], so[2]);
				// print(out);
				writeSolution(out);
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

	private void solveLargeProblem() {
		

	}

	private int[] solveSmallProblem(int[] n) {
		Arrays.sort(n);
		int[] r= new int[N];
		Arrays.fill(r, -1);
		
			
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (int i = n.length - 1; i >= 0; i--) {
			if (n[i]==1) {
				continue;
			}

			int temp = n[i];

			for (Integer integer : result) {
				if (temp % integer == 0) {
					temp = temp / integer;
				}
			}

			System.out.println(temp);
			while (temp > 1) {
				for (int j = M; j >= 2; j--) {
					if (temp % j == 0) {
						temp = temp / j;
						if (result.size() == N) {
							System.out.println("dddd");
						}
						result.add(j);
						break;
					}
				}
			}
		}
		
		// if (result.size() < 3) {
		// System.out.println("ddd");
		// }
		int[] out = new int[N];
		Arrays.fill(out, 2);
		for (int i = 0; i < result.size(); i++) {
			out[i] = result.get(i);
		}
		return out;
	}

	public void writeSolution(String s){
		String outStr = s + "\n";
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
		GoodLuck b = new GoodLuck();
		String dir = "./src/codejam2013/r1a/";
		String small = "C-small-practice-1.in";
		String large = "C-small-practice-2.in";

		// long r = b.solveAProblem(5, 2, new int[] { 1, 2 });
		// System.out.println(r);
		// b.solve(dir + small, dir + small.replace(".in", ".out"));
		b.solve(dir + large, dir + large.replace(".in", ".out"));
	}

}
