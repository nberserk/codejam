package codejam2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Pair{
	public Integer small, big;
	Pair(int small, int big){
		this.small = new Integer(small);
		this.big = new Integer(big);
	}
	
	@Override
	public int hashCode() {	return small.hashCode() ^ big.hashCode(); }

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Pair)) return false;
		Pair pair = (Pair) o;
		return this.small.equals(pair.small) &&	this.big.equals(pair.big) ;
	}
}

class Department{
	int name;
	HashMap<Integer, Integer> info = new HashMap<Integer, Integer>();
	Department(int n){
		name = n;
	}	
	
	
}



public class Movement {
	
	//static int[] a = new int[100];	
	static boolean fVerbose = false;

	static Department[] fDept;
	static int fMinValue = Integer.MAX_VALUE;
	static ArrayList<Integer> fSol;
	static HashMap<Pair, Integer> fCosts = new HashMap<Pair, Integer>(100);
	//ArrayList<Department> fArray = new ArrayList<Department>();
	
	
	public static int calc(ArrayList<Integer> in){
		int cost = 0;
		for (Pair pair : fCosts.keySet()) {
			int left = in.indexOf(	pair.small);
			int right = in.indexOf(pair.big);
			
			cost += Math.abs(left-right) * fCosts.get(pair);
		}		
		return cost ;
	}
	
	
	public static void solve(ArrayList<Integer> in , ArrayList<Integer> rest){
	
		if (rest.size() ==0) {
			//System.out.println(in);
			int cost = calc(in);
			if (fVerbose) {
				System.out.println(in+ "cost=" + cost);
			}
			if (cost < fMinValue) {
				fMinValue = cost;
				fSol = (ArrayList)in.clone();
				System.out.println(fSol+ "cost=" + cost);				
			}
		}else{
			
			for (Integer i : rest) {
				ArrayList<Integer> nextIn = (ArrayList)in.clone();
				nextIn.add(i);
				ArrayList<Integer> nextRest = (ArrayList)rest.clone();
				nextRest.remove(i);
				solve(nextIn, nextRest);
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		long start = System.currentTimeMillis();
				
		try {
//			br = new BufferedReader(new FileReader("./data/2010/C-small-practice.in"));
//			bw = new BufferedWriter(new FileWriter("./data/2010/C-small.out"));
			br = new BufferedReader(new FileReader("d:/MOVEMENT_4.in"));
			bw = new BufferedWriter(new FileWriter("d:/MOVEMENT_4.out"));
			
			String str = br.readLine();
//			String outStr = null;
//			long result = 0;
			int count = Integer.parseInt(str);
			//fcountDepartment = count -1;
			fDept = new Department[count-1];
			int index=0;
			ArrayList<Integer> rest = new ArrayList<Integer>(count);
			for (int i = 1; i <= count-1; i++) {
				rest.add(new Integer(i));
				index = i-1;
				str = br.readLine();
				String[] arg1 = str.split(" ");
				
				int dept = Integer.parseInt(arg1[0]);				
				int relCount = Integer.parseInt(arg1[1]);
				for (int j = 0; j < relCount; j++) {
					int dept2 = Integer.parseInt(arg1[j+2]);
					int cost = Integer.parseInt(arg1[j+3]);
					
					int small = Math.min(dept, dept2);
					int big = Math.max(dept, dept2);
					
					fCosts.put(new Pair(small, big), cost);
				}			
				
			}
			
			ArrayList<Integer> in = new ArrayList<Integer>();
			rest.add(new Integer(count));			
			solve(in, rest);
			
//			long startAProblem = System.currentTimeMillis();
//			int cost = solve(a1, a2, b1, b2);
//			System.out.println("elapsed time for #"+i+"="+ (System.currentTimeMillis()-startAProblem)/1000F);
//			
			String outStr = "";
			for (int i = 0; i < fSol.size(); i++) {
				outStr += fSol.get(i) + " ";
			}
			
			System.out.println(outStr+"\n");
			bw.write(outStr);
						
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				br.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
			try {
				bw.flush();
				bw.close();
			} catch (Exception e2) {
				System.out.println(e2);

			}
		}

		long elapsedTimeMillis = System.currentTimeMillis()-start;
		float elapsedTimeSec = elapsedTimeMillis/1000F;
		System.out.println("elapsed time(sec) :"+ elapsedTimeSec);

		
	}

}
