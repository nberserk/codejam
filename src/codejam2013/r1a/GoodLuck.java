package codejam2013.r1a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
			reader.readLine(); // read 1
			String[] v = reader.readLine().split(" ");
			int R = Integer.parseInt(v[0]);
			N = Integer.parseInt(v[1]);
			M = Integer.parseInt(v[2]);
			K = Integer.parseInt(v[3]);
			for (int i = 1; i <= R; i++) {			
				mCurrentProblem = i;
				
				v = reader.readLine().split(" ");
				int[] n = new int[K];
				for (int j = 0; j < n.length; j++) {
					n[j] = Integer.parseInt(v[j]);
				}				

				// parse a problem
				solveSmallProblem(n);
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

	private void solveSmallProblem(int[] n) {
		Arrays.sort(n);
		int[] r= new int[N];
		Arrays.fill(r, -1);
		
		HashSet<Integer> candidates = new HashSet<Integer>();
		
		for (int i = 0; i < n.length; i++) {
			if (n[i]==1) {
				continue;
			}
			for (int j = 2; j < M; j++) {
				if (n[i]%j == 0 ) {
					candidates.add(j);					
				}
			}
		}
		
		
				
		for (int i = 0; i < n.length; i++) {
			if (n[i]==-1) {
				continue;				
			}
		
			
			
		}

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
		String dir = "./src/codejam2013/r1c/";
		String small = "B-small-practice.in";
		String large = "B-large-practice.in";

		// long r = b.solveAProblem(5, 2, new int[] { 1, 2 });
		// System.out.println(r);
		b.solve(dir + small, dir + small.replace(".in", ".out"));
		b.solve(dir + large, dir + large.replace(".in", ".out"));
	}

}
