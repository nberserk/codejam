package codejam.y2009;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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


public class NumberGame {
	public static int TURN_A = 1;
	public static int TURN_B = 0;
	static int[] a = new int[100];
	static boolean fVerbose = false;
	static HashMap<Pair, Boolean> fCacheA = new HashMap<Pair, Boolean>(10000);
	static HashMap<Pair, Boolean> fCacheB = new HashMap<Pair, Boolean>(10000);
	
	public static boolean isAWin(int i, int j, int turn, int depth){ // turn 1 = A, turn 0 = B
		if (fVerbose) {
			StringBuilder prefixBuilder = new StringBuilder();		
			for (int k = 0; k < depth; k++) {
				prefixBuilder.append("  ");
			}
			System.out.println(prefixBuilder.toString() + "("+i +"-"+ j+ ")" );	
		}
		
		
		int nextTurn = turn == TURN_A ? TURN_B : TURN_A ;		
		int greater = Math.max(i, j);
		int smaller = Math.min(i, j);
		int mod = greater%smaller;
		
		if (mod == 0) {
			if (i == j) {
				if (turn == TURN_A) {
					return false;
				}else{
					return true;
				}
			}else{
				if (turn == TURN_A) {
					return true;
				}else{
					return false;
				}
			}
		}
		
		Pair pair = new Pair(smaller, greater);
		if (turn==TURN_A) {
			Boolean ret = fCacheA.get(pair); 
			if (ret != null ) {			
				return ret;
			}
		}else{
			Boolean ret = fCacheB.get(pair);
			if (ret != null ) {			
				return ret;
			}
		}
		
		int maxK = greater/smaller;
		boolean ret = true;
		boolean foundAWinPath = false;
		int start = maxK>2 ? maxK-1 : 1;
		for (int k = start; k <= maxK; k++) {
			int next = greater -k*smaller;
			if (next <=0) continue;
			boolean AWin = isAWin(next, smaller, nextTurn, depth+ 1);
			if (turn == TURN_A) {
				if (AWin) {
					foundAWinPath = true;
					break;
				}
			}else{ //turn b
				if (AWin== false) {
					ret = false;
					break;
				}
			}
		}
		
		if (turn == TURN_A && foundAWinPath == false) {
			ret = false;
		}
		
		
		
		if (turn==TURN_A) {
			if (greater < 1000) {
				fCacheA.put(pair, new Boolean(ret));	
			}				
		}else{
			if (greater < 1000) {
				fCacheB.put(pair,  new Boolean(ret));
			}
		}
		return ret;
	}
	
	
	public static int solve(int a1, int a2, int b1, int b2){
		if (fVerbose) {
			System.out.println(a1 + "<a<" + a2 + " , " + b1 + "<b<"+ b2);
		}
//		boolean ret = isAWin(5, 8, TURN_A);
//		ret = isAWin(2, 11, TURN_A);
//		ret = isAWin(2, 4, TURN_A);
		
//		int temp = 0;
//		for (int i = 0; i <=  1000000; i++) {
//			for (int j = 0; j <= 1000000; j++) {
//				temp++;
//			}
//		}	
		
		int count = 0;
		for (int i = a1; i <=  a2; i++) {
			for (int j = b1; j <= b2; j++) {
				if ( isAWin(i, j, TURN_A, 1) ){
					if (fVerbose) {
						System.out.println("(" + i + "," + j+ ") : success");
					}
					count ++;
				}
			}
		}		
		
		return count;
	}
	
	
	
	public static void main(String[] args) {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		long start = System.currentTimeMillis();
				
		try {
//			br = new BufferedReader(new FileReader("./data/2010/C-small-practice.in"));
//			bw = new BufferedWriter(new FileWriter("./data/2010/C-small.out"));
			br = new BufferedReader(new FileReader("./data/2010/C-large-practice.in"));
			bw = new BufferedWriter(new FileWriter("./data/2010/C-large.out"));
			
			String str = br.readLine();
//			String outStr = null;
//			long result = 0;
			int count = Integer.parseInt(str);			
			for (int i = 1; i <= count; i++) {
				//fCount = i;
				str = br.readLine();
				String[] arg1 = str.split(" ");
				int a1 = Integer.parseInt(arg1[0]);
				int a2 = Integer.parseInt(arg1[1]);
				int b1 = Integer.parseInt(arg1[2]);
				int b2 = Integer.parseInt(arg1[3]);								
				 
				long startAProblem = System.currentTimeMillis();
				int cost = solve(a1, a2, b1, b2);
				System.out.println("elapsed time for #"+i+"="+ (System.currentTimeMillis()-startAProblem)/1000F);
				
				String outStr = "Case #"+ i+": " + cost+ "\n";
				System.out.print(outStr+"\n");
				bw.write(outStr);		
		
			}
						
			
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
