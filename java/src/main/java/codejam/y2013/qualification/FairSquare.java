package codejam.y2013.qualification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;

public class FairSquare {	
	BufferedWriter mWriter;
	int	mCurrentProblem;
	BigInteger		mMax = BigInteger.ONE;
	HashSet<BigInteger>		mCache = new HashSet<BigInteger>();
	
	public FairSquare() {
		//mCache.add(BigInteger.ONE);
		
	}	
	
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
				parseAProblem(mReader);	
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
	
	public void parseAProblem(BufferedReader reader){
		String[] values=null;
		try {
			values = reader.readLine().split(" ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger a = new BigInteger(values[0]);
		BigInteger b = new BigInteger(values[1]);
		
		int count = doSolve(a, b);
		writeSolution(String.valueOf(count));
		
	}
	
	private void doCache(BigInteger a, BigInteger b) {
		if (mMax.compareTo(b) !=-1 ) {
			return;
		}
		
		doSolve0(mMax, b);
		mMax = b;
	}
	
	private int doSolve(BigInteger a, BigInteger b) {
		//doCache(a, b);
		
//		int count =0;
//		for (BigInteger current : mCache) {
//			int c = current.compareTo(a);
//			if (c == 0 || c==1) {
//				c = current.compareTo(b);
//				if (c==0 || c==-1) {
//					print("cache hit, "+ current.toString());
//					count++;
//				}
//			}			
//		}
		return doSolve0(a,b);
	}
	
	private int doSolve0(BigInteger a, BigInteger b) {		
		
		int count = 0;
		int length = a.toString().length();
		if (length%2==0) {
			length--;
		}
		length /=2;
		BigInteger start = new BigInteger("10");
		start = start.pow(length);
		BigInteger current = start.pow(2);
		while (current.compareTo(b) != 1) {
			
			if (current.compareTo(a) != -1) { // if not calculated
				String str = current.toString();
				if (isPalindrome(str) ) {
					//print(str);
					mCache.add(current);
					count++;
				}	
			}
			
			while (true) {
				start  = start.add(BigInteger.ONE);
				if (isPalindrome(start.toString())) {
					break;
				}
				if (start.compareTo(b)==1) {
					break;
				}
			}			
			current = start.pow(2);
		}		

		return count;
	}
	
	private static boolean isPalindrome(String str) {
		int length = str.length();
		
//		int center = length/2 +1;
//		if (length%2==1) {
//			center +=1;
//		}

		for (int i = 0; i < length/2; i++) {
			if (str.charAt(i) != str.charAt(length-1-i)) {
				return false;
			}
		}
		
		return true;		
	}

	private void cache(){
		BigInteger start = new BigInteger("1");	
	}

	public static void main(String[] args) {
		
//		boolean p = isPalindrome("121");
//		assert p == true;
//		
//		p = isPalindrome("11");
//		assert p == false;
//		p = isPalindrome("12111");
//		assert p == false;
				
		
		
		
		FairSquare fair = new FairSquare();
//		fair.doSolve(new BigInteger("1"),new BigInteger("1"));
		//fair.doSolve(new BigInteger("10"),new BigInteger("120"));
//		fair.doSolve(new BigInteger("100"),new BigInteger("1000"));
//		fair.solve("./src/codejam2013/qualification/C-large-1.in", "./src/codejam2013/qualification/C-large-1.out");
		fair.solve("./src/codejam2013/qualification/C-large-2.in", "./src/codejam2013/qualification/C-large-2.out");
	}
}
