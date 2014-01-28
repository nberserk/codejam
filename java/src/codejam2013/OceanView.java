package codejam2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import codejam.lib.BinarySearch;
import codejam.lib.BinarySearch.IValidator;
import codejam.lib.CodejamBase;

/*
 * longest increasing subsequence
 * https://code.google.com/codejam/contest/2334486/dashboard#s=p2
 */
public class OceanView extends CodejamBase{
	
	public static void main(String[] args) {
		OceanView ocean = new OceanView();		
		String dir = "./input/codejam2013/";
		ocean.solve(dir + "C-large-practice.in", dir + "C-large.out");
		// ocean.solve(dir + "/C-small-practice.in", dir + "C-small.out");
	}

	@Override
	public void parseAProblem(BufferedReader mReader) {
		int N;
		try {
			N = Integer.parseInt(mReader.readLine());			
			
			int[] heights = new int[N];
			String[] values = mReader.readLine().split(" ");
			for (int i = 0; i < N; i++) {				
				heights[i] = Integer.parseInt(values[i]);				
			}

			// int step = doSolve(heights);
			// Integer[] result = longestIncreasingSubsequence(new int[] { 2, 6,
			// 3, 4, 1, 2, 9, 5, 8 });
			int[] result = longestIncreasingSubsequence(heights);

			int removeCount = heights.length - result.length;
			print(Arrays.toString(result));

			writeSolution(String.format("%d", removeCount));
//			doSolve(new State(new int[]{4, 3, 2, 1}));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int[] longestIncreasingSubsequence(final int[] x) {
		final int[] m = new int[x.length+1];
		int[] prev = new int[x.length];
		m[0] = 0;
		
		int L = 0;
		for (int i = 0; i < x.length; i++) {
			int largestWithinL = 0; // biggest value such that x[m[j]]<x[i]			
			
			final int xi = x[i];
			largestWithinL = BinarySearch.binarySearchBiggestSatisfyingCondition(0, L, new IValidator() {				
				@Override
				public boolean validate(int index) {
					if (x[m[index]] < xi) {
						return true;
					}
					return false;
				}
			});
			if (largestWithinL==-1) {
				largestWithinL = 0;
			}
			
			//brute force
//			print(String.valueOf(largestWithinL));
//			largestWithinL = 0;
//			for (int k = 0; k <= L;k++) {				 
//				if (x[m[k]] < x[i]) {
//					largestWithinL = Math.max(largestWithinL, k);
//				}
//			}
//			print(String.valueOf(largestWithinL));
			//Arrays.binarySearch(a, key)
			
			
			
			prev[i] = m[largestWithinL];  		//save previous to reconstruct the solution later
			if (largestWithinL==L || x[i] < x[m[largestWithinL+1]] ) {				
				m[largestWithinL+1] = i;		// overwrite existing one or insert the end of m
				L = Math.max(L, largestWithinL+1);
			}
			print(i+"-p, "+Arrays.toString(prev));
			print(i+"-m, "+Arrays.toString(m));
			StringBuilder sb = new StringBuilder();
			sb.append("---------");
			for (int l = 1; l <= L; l++) {
				sb.append(x[m[l]]+", ");
			}
			print(sb.toString());			
			print("L="+L);
			print("\n");
		}		
		
		//print(Arrays.toString(p));
		print("solution");
		int[] sol = new int[L];
		int index = m[L];
		for (int i = L - 1; i >= 0; i--) {
			sol[i] = x[index];
			index = prev[index];
		}
		// ArrayList<Integer> sol = new ArrayList<Integer>(x.length);
		// int index = m[L];
		// for (int i = 0; i < L ; i++) {
		// sol.add(x[index]);
		// index = prev[index];
		// }
		// Collections.reverse(sol);
		// print(sol.toString());
		return sol;
	}

}
