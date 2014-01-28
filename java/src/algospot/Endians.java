package algospot;

import java.util.Scanner;

/*
 * http://algospot.com/judge/problem/read/ENDIANS
 */
public class Endians {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			long u = sc.nextLong();
			long p1 = (u & 0xff000000) >> 24;
			long p2 = (u & 0x00ff0000) >> 16;
			long p3 = (u & 0x0000ff00) >> 8;
			long p4 = (u & 0x000000ff);

			long r = p1 | (p2 << 8) | (p3 << 16) | (p4 << 24);
			System.out.println(r);
			// solve();
		}
	}

}
