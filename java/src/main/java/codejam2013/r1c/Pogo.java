package codejam2013.r1c;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

public class Pogo {
	static class Node implements Comparable<Node> {
		int x, y;
		int diff;
		Node p;
		int weight;
		String action;

		public Node(int x, int y, int w, String a, Node p) {
			this.x = x;
			this.y = y;
			diff = Math.abs(dx - x) + Math.abs(dy - y);
			this.p = p;
			this.weight = w;
			this.action = a;
		}

		@Override
		public String toString() {

			return String.format("(%d,%d)-%d,%s", x, y, weight, action);
		}

		@Override
		public int compareTo(Node o) {
			if (weight < o.weight) {
				return -1;
			} else if (weight > o.weight) {
				return 1;
			}
			if (diff < o.diff) {
				return -1;
			} else if (diff > o.diff) {
				return 1;
			}

			if (x < o.x) {
				return -1;
			} else if (x > o.x) {
				return 1;
			}

			if (y < o.y) {
				return -1;
			} else if (y > o.y) {
				return 1;
			}

			return 0;
		}
	}

	BufferedWriter mWriter;
	int	mCurrentProblem;
	public static int dx, dy;
	
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
				int x = Integer.parseInt(v[0]);
				int y = Integer.parseInt(v[1]);
				x = 1000000;
				y = 100000;
				String out = solveAProblem(x, y);
				// print(out);
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

	private String solveAProblem(int x, int y) {
		dx = x;
		dy = y;
		TreeSet<Node> opened = new TreeSet<Node>();
		opened.add(new Node(0,0, 1, "", null));
		while (!opened.isEmpty()) {
			Node c = opened.pollFirst();
			if (c.x == dx && c.y == dy) {
				StringBuilder sb = new StringBuilder();
				Node cur = c;

				while (cur != null) {

					sb.append(cur.action);
					cur = cur.p;
				}
				return sb.reverse().toString();
			}
			
			// east
			int nx = c.x + c.weight;
			opened.add(new Node(nx, c.y, c.weight + 1, "E", c));

			// west
			nx = c.x - c.weight;
			opened.add(new Node(nx, c.y, c.weight + 1, "W", c));

			// north
			int ny = c.y + c.weight;
			opened.add(new Node(c.x, ny, c.weight + 1, "N", c));

			ny = c.y - c.weight;
			opened.add(new Node(c.x, ny, c.weight + 1, "S", c));
		}

		return "";
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
		Pogo b = new Pogo();
		String dir = "./src/codejam2013/r1b/";
		String fn = "B-small-attempt1.in";
		// long r = b.solveAProblem(5, 2, new int[] { 1, 2 });
		// System.out.println(r);
		b.solve(dir + fn, dir + fn.replace(".in", ".out"));
	}

}
