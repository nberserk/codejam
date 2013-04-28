package codejam2013.r1a;

import java.io.BufferedReader;
import java.io.IOException;

import codejam.lib.BinarySearch;
import codejam.lib.CodejamBase;

public class Bullseye extends CodejamBase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void parseAProblem(BufferedReader reader) {
		try {
			String[] values = reader.readLine().split(" ");
			int r = Integer.parseInt(values[0]);
			int t = Integer.parseInt(values[0]);
			
			BinarySearch.binarySearchBiggestSatisfyingCondition(lo, hi, validator)
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
