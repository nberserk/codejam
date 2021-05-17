package codejam.y2013;

import java.io.BufferedReader;
import java.io.IOException;

import codejam.lib.CodejamBase;

public class Hedgemony extends CodejamBase {

	
	private void doSolve(double[] heights) {
		
		for (int i = 1; i < heights.length-1; i++) {
			double average = (heights[i-1] + heights[i+1])/2.0;
			heights[i] =  average < heights[i] ? average: heights[i] ;
			print("d,"+heights[i]);
			
		}
		
		//print(Arrays.toString(heights));
		String out = String.format("%.06f", heights[heights.length-2]	);
		print(out);
		writeSolution(out);		
	}


	@Override
	public void parseAProblem(BufferedReader reader) {
		try {
			int N = Integer.parseInt(reader.readLine());
			String[] height = reader.readLine().split(" ");
			double[]	heights = new double[height.length];
			
			
			for (int i = 0; i < N; i++) {
				heights[i] = (double)Integer.parseInt(height[i]);
			}
		
			//doSolve(new double[] {1, 2, 2, 2, 2, 2});
			
			doSolve(heights);
			
			
			
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				
	}

	
	public static void main(String[] args) {
		Hedgemony h = new Hedgemony();
		//h.solve("./src/codejam2013/data/A-small-practice.in", "./src/codejam2013/data/A-small.out");
		h.load("./src/codejam2013/data/A-large-practice.in", "./src/codejam2013/data/A-large.out");
	}
}
