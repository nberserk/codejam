package codejam2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class Rotate {
	
	static char[][] fArray = new char[50][50];
	static boolean fVerbose = true;
	
	private static void print(int N, char[][] array){
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println("\n");
		}
	}
	
	public static String solve(int N, int K){
		if (fVerbose) {
			print(N, fArray);
		}
		
		// rotate
		char[][] rotated = new char[N][N];
		for (int i = 0; i < N; i++) {			
			for (int j = 0; j < N; j++) {
				rotated[i][j] = fArray[N-j-1][i];				
			}
		}
		
		System.out.println("after rotate");
		print(N, rotated);
		
		// applying gravity
		for (int j = 0; j < N ; j++) {		 
			
			int desti = N-1;
			for (int i = N-1; i >= 0; i--) {	 
				char v = rotated[i][j]; 
				if (v == '.') {
					continue;
				}
				if(v == 'B'){
					if (i != desti) {
						rotated[desti][j] = v;
						rotated[i][j] = '.';
					}
					desti -=1;
				}
				if(v == 'R'){
					if (i != desti) {
						rotated[desti][j] = v;
						rotated[i][j] = '.';
					}
					desti -=1;
				}
				
			}
		}
		
		System.out.println("after gravity");
		print(N, rotated);
		
		//find winner
		boolean match_b = false;
		boolean match_r = false;
		for (int i = 0; i < N; i++) {			
			for (int j = 0; j < N; j++) {
				char v = rotated[i][j];
				if (v == '.') {
					continue;
				}
				if (v == 'B' && !match_b ) {
					
				}
				
			}
		}
		
		
		
		return "Neither";
	}
	
	
	
	public static void main(String[] args) {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		long start = System.currentTimeMillis();
				
		try {
			br = new BufferedReader(new FileReader("./data/2010/A-small-practice.in"));
			bw = new BufferedWriter(new FileWriter("./data/2010/A-small.out"));
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
				int N = Integer.parseInt(arg1[0]);
				int K = Integer.parseInt(arg1[1]);
				
				
				for (int j = 0; j < N; j++) {
					String line = br.readLine();
					if (line.length() != N){
						System.out.println("oops");
					}
					fArray[j] = line.toCharArray();
				}
				long startAProblem = System.currentTimeMillis(); 
				String winner = solve(N, K);
				System.out.println("elapsed time for #"+i+"="+ (System.currentTimeMillis()-startAProblem)/1000F);
				
				String outStr = "Case #"+ i+": " + winner + "\n";
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
