package codejam2013.r1a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * Small ok, Large not ok
 */
public class GoodLuck {	
	BufferedWriter mWriter;
	int	mCurrentProblem;
	private int N;
	private int M;
	private int K;
	private int mTarget;
	
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
				long[] n = new long[K];
				for (int j = 0; j < n.length; j++) {
					n[j] = Long.parseLong(v[j]);
				}				

				// parse a problem
				//int[] so = solveSmallProblem(n);
				int[] so = solveLargeProblem(n);
			
				Arrays.sort(so);
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < so.length; j++) {
					sb.append(so[j]);
				}
				//sb.append("\n");
				String out = sb.toString();
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

	private int[] solveLargeProblem(long[] n) {
		Arrays.sort(n);
		
		ArrayList<Integer> r = new ArrayList<Integer>();
		
				
		Node[] nodes = new Node[N];

		// big number first
		for (int i = n.length - 1; i >= 0; i--) {
			Node node = new Node();
			nodes[i] = node;
			node.n = n[i];
			if (n[i]==1) {
				continue;
			}

			long temp = n[i];
			// System.out.println(temp + ":");

			// divide by r
			for (int j = r.size()-1; j >= 0; j--) {
				int cur = r.get(j);
				if (temp%cur==0) {
					temp /= cur;
					node.add(cur);
				}
				if (temp==1) {
					break;
				}			
			}
						
			if (temp==1) {
				continue;
			}
			
			// 
			while (temp>1) {
				for (int j = M; j >= 2; j--) {
					if (temp % j == 0) {
						temp = temp / j;
						node.add(j);
						if (r.size() < N) {
							r.add(j);
							Collections.sort(r);
						}else{
							//
							node.mExtras.add(j);
							// System.out.println(r.toString());
						}	
						break;
					}
				}//for						
			}//while

			System.out.println(node);
		}

		print(nodes, r);
		ArrayList<Integer> processedExtras= new ArrayList<Integer>();
		while (hasExtra(nodes)) {
			for (Node node : nodes) {
				if (node.mExtras.size() == 0) {
					continue;
				}

				int e= node.mExtras.get(0);
				if (tryResolve(nodes, r, e)) {
					break;
				} else {
					System.out.println("oops");
				}
			}// for node
		}
		
		int[] out = new int[N];
		Arrays.fill(out, 2);
		for (int i = 0; i < r.size(); i++) {
			out[i] = r.get(i);
		}
		return out;
	}

	private boolean hasExtra(Node[] nodes) {
		for (Node node : nodes) {
			if (node.mExtras.size() > 0)
				return true;
		}
		return false;
	}

	private boolean tryResolve(Node[] nodes, ArrayList<Integer> r, int e) {

		// try split
		for (int r2 : r) {
			// can replace r2 with e, r2/e
			if (r2 % e == 0 && r.contains(r2 / e)) {
				// check validity for each node
				boolean valid= true;
				for (Node node : nodes) {
					if (node.mCandidates.contains(r2)) {
						if (node.mCandidates.size() >= N) {
							valid= false;
							break;
						}
					}
				}// for

				if (valid) {
					int idx= r.indexOf(r2);
					r.remove(idx);
					r.add(e);
					
					
					for (Node node : nodes) {
						if (node.mCandidates.contains(r2)) {
							node.del(r2);

							node.add(e);
							node.add(r2 / e);
						}
						if (node.mExtras.contains(e)) {
							node.mExtras.remove(node.mExtras.indexOf(e));
						}
					}
					print(nodes, r);
					return true;
				}

			}
		}

		// try merge
		for (int i= 2; i <= M; i++) {
			int n= i * e;
			if (n > M) {
				break;
			}

			if (!r.contains(n)) {
				continue;
			}

			int nCount= 0; //
			int eCount= 0; //

			r.remove(r.indexOf(e));
			r.add(i);

		}

		return false;
	}

	private void print(Node[] nodes, ArrayList<Integer> r) {
		System.out.println("--");
		for (Node node : nodes) {
			System.out.println(node);
		}
		System.out.println(r);
	}

	private int[] solveSmallProblem(long[] n) {
		Arrays.sort(n);		
		
		int[] out = new int[N];
		Arrays.fill(out, 10);
		int index = 0;
		
		// big number first
		for (int i = n.length - 1; i >= 0; i--) {
			if (n[i]==1) {
				continue;
			}

			long temp = n[i];
			System.out.println(temp + ":");

			//
			for (int j = N-1; j >=0 ; j--) {
				if (out[j]==10) {
					continue;
				}
				if (temp % out[j] == 0) {
					temp = temp / out[j];
					if (temp==1) {
						break;
					}
				}
			}
			
			if (temp==1) {
				continue;
			}

			System.out.print(temp + "->");
			while (temp > 1) {
				for (int j = M; j >= 2; j--) {
					if (temp % j == 0) {
						temp = temp / j;
						
						if (index >= out.length) {
							check();
						}else{
							out[index++] = j;
							Arrays.sort(out);	
						}
						break;
					}
				}
				if (temp==1) {
					break;
				}
			}
			System.out.println(temp);
			System.out.println(Arrays.toString(out));
		}
		
		// if (result.size() < 3) {
		// System.out.println("ddd");
		// }
		
		// fill 2 at empty slot
		for (int i = 0; i < out.length;i++) {
			if (out[i]==10) {
				out[i] = 2;
			}			
		}
		return out;
	}

	private void check() {
		System.out.println("check");
		
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
//		 b.solve(dir + small, dir + small.replace(".in", ".out"));
		b.solve(dir + large, dir + large.replace(".in", ".out"));
	}

	static class Node{	
		public long n;
		public ArrayList<Integer> mCandidates = new ArrayList<Integer>();
		public ArrayList<Integer> mExtras = new ArrayList<Integer>();
		public int[] mFrequency= new int[10];
		@Override
		public String toString() {
			return "" + mCandidates.toString() + ", extras: "
					+ mExtras.toString();
		}

		public void add(int cur) {
			mCandidates.add(cur);
			mFrequency[cur]++;
		}

		public void del(int cur) {
			mCandidates.remove(mCandidates.indexOf(cur));
			mFrequency[cur]--;
		}

	}
}
