package stop2013.finals;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * stopcoder 2013 final match
 */
public class Chip {
	BufferedWriter mWriter = null;
	private int[] matchOdd;
	private int[] matchEven;
	private int m;
	private int n;
	private ArrayList<Point> odd;
	private ArrayList<Point> even;
	//private Point[] cut;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Chip a = new Chip();
		try {
			// a.solution("d:/flag_1.in", "d:/flag_1.out");
			a.solution("./src/final2013/chip.in", "./src/final2013/chip.out");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void solution(String inputFilePath, String usrOutputFilePath)
			throws IOException {

		BufferedReader br = null;


		br = new BufferedReader(new FileReader(inputFilePath));
		mWriter = new BufferedWriter(new FileWriter(usrOutputFilePath));

		String str = br.readLine();
		int problemCount = Integer.parseInt(str);

		for (int i = 0; i < problemCount; i++) {
			// parse a problem
			String[] v = br.readLine().split(" ");
			m = Integer.parseInt(v[0]);
			n = Integer.parseInt(v[1]);
			int cutCount = Integer.parseInt(br.readLine());
			int[][] cut = new int[cutCount][2];
			for (int j = 0; j < cutCount; j++) {
				v = br.readLine().split(" ");
				
				int r = Integer.parseInt(v[0]);
				int c = Integer.parseInt(v[1]);
				cut[j][0] = c-1;
				cut[j][1] = r-1;
			}
			
			
			
			int matchCount = howMany(cut);			
			
			
			
			String out ="Case# " + (i+1)+ "\n"+matchCount+"\n";
			System.out.println(out);
			write(out);			

		}

		try {
			br.close();
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

	private int howMany(int[][] cut) {

		even = new ArrayList<Point>();
		odd = new ArrayList<Point>();
		
		for (int j = 0; j < m; j++) {
			for (int k = 0; k < n; k++) {
				if ((j+k)%2==0) {
					
					even.add(new Point(k,j));
				}else{
					odd.add(new Point(k,j));
				}
			}
		}
		
		// apply cut
		for (int i = 0; i < cut.length; i++) {
			if ((cut[i][0] + cut[i][1])%2==0) {
				even.remove(new Point(cut[i][0], cut[i][1]));
			}else{
				odd.remove(new Point(cut[i][0], cut[i][1]));
			}			
		}
		
		matchEven = new int[1250];
		matchOdd = new int[1250];
		Arrays.fill(matchEven, -1);
		Arrays.fill(matchOdd, -1);
		
		int matchCount = 0;
		for (int r = 0; r < even.size(); r++) {				
			if (dfs(r)) {
				matchCount++;
			}				
		}
		
		return matchCount;
	}

	private boolean dfs(int source) {		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = { 0, 0, -1, 1};
		
		ArrayList<Integer> q = new ArrayList<Integer>();
		
		int[] parent = new int[2000];
		Arrays.fill(parent, -1);	
		
		q.add(source);
		parent[source]= source;
		
		while (q.isEmpty()==false) {
			int evenIndex = q.get(0);
			q.remove(0);
			
			Point cur = even.get(evenIndex);			
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx<0 || ny<0 || nx >=n || ny>=m) {
					continue;
				}
				
				Point next = new Point(nx,ny);
				
//				// check cut
//				boolean blocked = false;
//				for (int j = 0; j < cut.length; j++) {
//					if (cur.equals(next)) {
//						blocked =true;
//						break;
//					}
//				}
//				if (blocked) {
//					continue;
//				}
				
				//
				
				int oddIndex = odd.indexOf(next);
				if (oddIndex==-1) {
					continue;
				}
				int nextEven = matchOdd[oddIndex];
				if (nextEven==-1) {
					// found match
					while(parent[evenIndex]!=evenIndex){
						
						int temp = matchEven[evenIndex];
						matchEven[evenIndex] = oddIndex;
						matchOdd[oddIndex] = evenIndex;						
						
						evenIndex = parent[evenIndex];
						oddIndex = temp;
					}
					
					matchEven[evenIndex] = oddIndex;
					matchOdd[oddIndex] = evenIndex;	
					return true;
				}else{
					if (parent[nextEven]==-1) {
						parent[nextEven] = evenIndex;
						q.add(nextEven);	
					}					
				}
			}
		}
		

		return false;
	}

	private void write(String str) {
		try {
			mWriter.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void print(String str) {
		System.out.println(str);
	}

}
