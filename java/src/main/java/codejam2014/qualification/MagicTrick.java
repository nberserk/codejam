package codejam2014.qualification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MagicTrick {	
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

				int row = Integer.parseInt(reader.readLine());
				int[] deck = new int[4];
				for (int j = 0; j < 4; j++) {
					String[] v = reader.readLine().split(" ");
					if (j != row - 1) {
						continue;
					}
					for (int k = 0; k < v.length; k++) {
						deck[k] = Integer.parseInt(v[k]);
					}
				}
				int row2 = Integer.parseInt(reader.readLine());
				int[] deck2 = new int[4];
				for (int j = 0; j < 4; j++) {
					String[] v = reader.readLine().split(" ");
					if (j != row2 - 1) {
						continue;
					}
					for (int k = 0; k < v.length; k++) {
						deck2[k] = Integer.parseInt(v[k]);
					}
				}
				
				// parse a problem
				solveSmallProblem(deck, deck2);
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

	private void solveSmallProblem(int[] deck, int[] deck2) {
		int matchCount = 0;
		int sol = 0;
		for (int i = 0; i < deck.length; i++) {
			for (int j = 0; j < deck2.length; j++) {
				if (deck[i] == deck2[j]) {
					matchCount++;
					sol = deck[i];
					break;
				}
			}
		}

		if (matchCount == 1) {
			writeSolution(sol + "");
		} else if (matchCount == 0) {
			writeSolution("Volunteer cheated!");
		} else {
			writeSolution("Bad magician!");
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
		MagicTrick b = new MagicTrick();
		String dir = "./src/codejam2014/qualification/";
		String small = "A-small-attempt1.in";

		// b.solveSmallProblem(new int[] { 5, 6, 7, 8 }, new int[] { 9, 10, 7,
		// 12 });
		// System.out.println(r);
		b.solve(dir + small, dir + small.replace(".in", ".out"));
		// b.solve(dir + large, dir + large.replace(".in", ".out"));
	}

}
