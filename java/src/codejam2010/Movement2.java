package codejam2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


class ValueComparator implements Comparator<Pair> {

    Map<Pair, Integer> base;
    public ValueComparator(Map<Pair, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(Pair a, Pair b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}


public class Movement2 {
	
	//static int[] a = new int[100];	
	static boolean fVerbose = false;

	static Department[] fDept;
	static int fMinValue = Integer.MAX_VALUE;
	static ArrayList<Integer> fSol = new ArrayList<Integer>();
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
	
	public static void insert(Pair pair){
//		boolean smallAdded = fSol.contains(pair.small); 
//		boolean bigAdded = fSol.contains(pair.big);
//		if (fSol.isEmpty()) {
//			fSol.add(pair.small);
//			fSol.add(pair.big);			
//		}		
	}
	
	
	public static void solve(){
	
		ValueComparator bvc =  new ValueComparator(fCosts);	
		TreeMap<Pair, Integer> sortedCosts = new TreeMap<Pair, Integer>(bvc);
		sortedCosts.putAll(fCosts);
		System.out.println(sortedCosts);
		
		for (Pair pair : sortedCosts.keySet()) {
			if (fSol.isEmpty()) {
				fSol.add(pair.small);
				fSol.add(pair.big);			
			}else{
				
			}
			
		}
		
		
		//ArrayList<Integer> empty;
//		for (Pair pair : fSortedCosts) {
//			
//		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		long start = System.currentTimeMillis();
				
		try {
//			br = new BufferedReader(new FileReader("./data/2010/C-small-practice.in"));
//			bw = new BufferedWriter(new FileWriter("./data/2010/C-small.out"));
			br = new BufferedReader(new FileReader("d:/MOVEMENT_8.in"));
			bw = new BufferedWriter(new FileWriter("d:/MOVEMENT_8.out"));
			
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
			//solve();
			
//			long startAProblem = System.currentTimeMillis();
//			int cost = solve(a1, a2, b1, b2);
//			System.out.println("elapsed time for #"+i+"="+ (System.currentTimeMillis()-startAProblem)/1000F);
			
			ArrayList<Integer> temp = new ArrayList<Integer>();
			Random r = new Random();
			for (int i = 0; i < count; i++) {
				temp.add(new Integer(i+1)	);				
			}
			
			for (int i = 0; i < count; i++) {
				int index2 = r.nextInt(temp.size()	);
				fSol.add(temp.get(index2));
			}			
			
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
