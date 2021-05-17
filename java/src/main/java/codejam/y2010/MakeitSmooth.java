package codejam.y2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class MakeitSmooth {
	
	static int[] a = new int[100];
	static boolean fVerbose = true;
	
	
	public static int solve(int d, int i, int m, int n){
		int cost = 0;
		int prev = 0;
		for (int j = 0; j < n; j++) {
			if (j==0) {
				prev = a[j];
				continue;
			}
			if ( Math.abs(a[j] - prev) > m ) {
				// delete
			}
			
		}
		
		
		
		return cost;
	}
	
	
	
	public static void main(String[] args) {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		long start = System.currentTimeMillis();
				
		try {
			br = new BufferedReader(new FileReader("./data/2010/B-small-practice.in"));
			bw = new BufferedWriter(new FileWriter("./data/2010/B-small.out"));
//			br = new BufferedReader(new FileReader("./data/C-large-practice.in"));
//			bw = new BufferedWriter(new FileWriter("./data/C-large.out"));
			
			String str = br.readLine();
//			String outStr = null;
//			long result = 0;
			int count = Integer.parseInt(str);			
			for (int i = 1; i <= count; i++) {
				//fCount = i;
				str = br.readLine();
				String[] arg1 = str.split(" ");
				int D = Integer.parseInt(arg1[0]);
				int I = Integer.parseInt(arg1[1]);
				int M = Integer.parseInt(arg1[2]);
				int N = Integer.parseInt(arg1[3]);
								
				String line = br.readLine();
				arg1 = str.split(" ");
				for (int j = 0; j < N; j++) {
					a[j] = Integer.parseInt(arg1[j]);					
				}
				
				long startAProblem = System.currentTimeMillis(); 
				int cost = solve(D, I, M, N);
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
