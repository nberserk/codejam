package crackcode;

import java.util.ArrayList;
import java.util.HashSet;

public class Denominator {

	public static void main(String[] args) {
		divide(22, 7);
		divide(1, 3);
		divide(2, 4);
	}

	static void divide(int n, int denominator) {
		int real = n / denominator;
		int remain = n % denominator;
		HashSet<Integer> appeared = new HashSet<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (remain > 0) {
			remain *= 10;
			int cur = remain / denominator;
			remain = remain % denominator;
			if (appeared.contains(remain)) {
				break;
			}
			appeared.add(remain);
			result.add(cur);
		}
		
		System.out.println(real + ".(" + result.toString() + ")");
	}

}
