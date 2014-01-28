package codejam2013.r1c;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

public class GreatWall {	
	static class Attack implements Comparable<Attack> {
		int day;
		int tribe;
		int l, r;
		int s;

		public Attack(int d, int t, int l, int r, int s) {
			day = d;
			tribe = t;
			this.l = l;
			this.r = r;
			this.s = s;
		}

		@Override
		public String toString() {

			return String.format("day %d, [%d,%d], with %d strength", day,
					l, r, s);
		}

		@Override
		public int compareTo(Attack o) {
			if (day < o.day) {
				return -1;
			} else if (day > o.day) {
				return 1;
			}

			if (tribe < o.tribe) {
				return -1;
			} else if (tribe > o.tribe) {
				return 1;
			}

			return 0;
		}

	}
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

				int n = Integer.parseInt(mReader.readLine());
				int[][] s = new int[n][8];
				for (int j = 0; j < n; j++) {
					String[] v = mReader.readLine().split(" ");
					s[j][0] = Integer.parseInt(v[0]);
					s[j][1] = Integer.parseInt(v[1]);
					s[j][2] = Integer.parseInt(v[2]);
					s[j][3] = Integer.parseInt(v[3]);
					s[j][4] = Integer.parseInt(v[4]);
					s[j][5] = Integer.parseInt(v[5]);
					s[j][6] = Integer.parseInt(v[6]);
					s[j][7] = Integer.parseInt(v[7]);
				}
				// parse a problem
				int out = solveAProblem(s);
				writeSolution(String.format("%d", out));
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

	private int solveAProblem(int[][] s) {
		int count = 0;

		TreeSet<Attack> attacks = new TreeSet<Attack>();

		for (int i = 0; i < s.length; i++) {
			int d = s[i][0];
			int n = s[i][1];
			int l = s[i][2] + 10000000;
			int r = s[i][3] + 10000000;
			// int l = s[i][2] + 1000;
			// int r = s[i][3] + 1000;
			int strength = s[i][4];
			for (int j = 0; j < n; j++) {
				Attack a = new Attack(d, i, l, r, strength);
				attacks.add(a);

				d += s[i][5];
				l += s[i][6];
				r += s[i][6];
				strength += s[i][7];
			}
		}

		
		int[] w = new int[100000000];
		Attack[] att = attacks.toArray(new Attack[attacks.size()]);
		for (int i = 0; i < att.length;i++) {
			Attack a = att[i];
			boolean multipleAttack = false;
			if (i + 1 < att.length && att[i + 1].day == a.day) {
				multipleAttack = true;
			}
			HashMap<Integer,Integer> newHeight = new HashMap<Integer, Integer>(); // column, new height			
			boolean attacked = false;
			
			for (int j = a.l; j < a.r; j++) {
				if (w[j] < a.s) {
					attacked = true;
					if (multipleAttack) {
						newHeight.put(j, a.s);
					} else {
						w[j] = a.s;
					}
				}
			}			
			
			if (attacked) {
				count++;
			}			
			
			if (multipleAttack == false) {
				continue;
			}

			while(i+1<att.length){
				if (att[i+1].day != a.day) {
					break;
				}
				i++;
				a = att[i];
				attacked = false;
				for (int j = a.l; j < a.r; j++) {
					if (w[j] < a.s) {
						attacked = true;
						Integer p = newHeight.get(j);
						if (p == null || p < a.s) {
							newHeight.put(j, a.s);
						}
					}
				}
				if (attacked) {
					count++;
				}
			}
			
			// restore wall
			for (Integer col : newHeight.keySet()) {
				Integer h = newHeight.get(col);
				w[col] = h;
			}
		}




		return count;
	}

	private int solveASmallProblem(int[][] s) {
		int count = 0;

		TreeSet<Attack> attacks = new TreeSet<Attack>();

		for (int i = 0; i < s.length; i++) {
			int d = s[i][0];
			int n = s[i][1];
			// int l = s[i][2] + 1000000;
			// int r = s[i][3] + 1000000;
			int l = s[i][2] + 1000;
			int r = s[i][3] + 1000;
			int strength = s[i][4];
			for (int j = 0; j < n; j++) {
				Attack a = new Attack(d, i, l, r, strength);
				attacks.add(a);

				d += s[i][5];
				l += s[i][6];
				r += s[i][6];
				strength += s[i][7];
			}
		}

		int[] w = new int[1000000];
		Attack[] att = attacks.toArray(new Attack[attacks.size()]);
		for (int i = 0; i < att.length; i++) {
			Attack a = att[i];
			boolean multipleAttack = false;
			if (i + 1 < att.length && att[i + 1].day == a.day) {
				multipleAttack = true;
			}
			HashMap<Integer, Integer> newHeight = new HashMap<Integer, Integer>(); // column,
																					// new
																					// height
			boolean attacked = false;

			for (int j = a.l; j < a.r; j++) {
				if (w[j] < a.s) {
					attacked = true;
					if (multipleAttack) {
						newHeight.put(j, a.s);
					} else {
						w[j] = a.s;
					}
				}
			}

			if (attacked) {
				count++;
			}

			if (multipleAttack == false) {
				continue;
			}

			while (i + 1 < att.length) {
				if (att[i + 1].day != a.day) {
					break;
				}
				i++;
				a = att[i];
				attacked = false;
				for (int j = a.l; j < a.r; j++) {
					if (w[j] < a.s) {
						attacked = true;
						Integer p = newHeight.get(j);
						if (p == null || p < a.s) {
							newHeight.put(j, a.s);
						}
					}
				}
				if (attacked) {
					count++;
				}
			}

			// restore wall
			for (Integer col : newHeight.keySet()) {
				Integer h = newHeight.get(col);
				w[col] = h;
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
		GreatWall b = new GreatWall();
		String dir = "./src/codejam2013/r1c/";
		String fn = "C-large-practice.in";
		// long r = b.solveAProblem(5, 2, new int[] { 1, 2 });
		// System.out.println(r);
		b.solve(dir + fn, dir + fn.replace(".in", ".out"));
	}

}
