package topcoder.dp;

public class ZigZag {

	/**
	 * dp, http://community.topcoder.com/tc?module=ProblemDetail&rd=4493&pm=1259
	 * 
	 */

	public static int longestZigZag(int[] sequence) {
		int N = sequence.length;
		if (N <= 1) {
			return 1;
		}
		int[] l = new int[sequence.length];

		int index = 0;
		for (int i = 0; i < l.length - 1; i++) {
			int diff = sequence[i] - sequence[i + 1];
			if (index == 0 && diff != 0) {
				l[index] = diff;
				index++;
			}
			if (index > 0 && l[index - 1] * diff < 0) {
				l[index] = diff;
				index++;
			}
		}

		return index + 1;
	}

	public static void main(String[] args) {
		
		int r = ZigZag.longestZigZag(new int[] { 1, 7, 4, 9, 2, 5 });
		System.out.println(r);

		r = ZigZag.longestZigZag(new int[] { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 });
		System.out.println(r);

		r = ZigZag.longestZigZag(new int[] { 44 });
		System.out.println(r);

		r = ZigZag.longestZigZag(new int[] { 1, 1 });
		System.out.println(r);

		r = ZigZag.longestZigZag(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
		System.out.println(r);

		r = ZigZag.longestZigZag(new int[] { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 });
		System.out.println(r);

		r = ZigZag.longestZigZag(new int[] { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 600, 947, 978, 46, 100,
				953, 670, 862, 568, 188, 67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 477, 245, 413, 109, 659, 401,
				483, 308, 609, 120, 249, 22, 176, 279, 23, 22, 617, 462, 459, 244 });
		System.out.println(r);

	}

}
