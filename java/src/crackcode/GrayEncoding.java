package crackcode;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

public class GrayEncoding extends TestCase {
	
	
	
	public static int smallestN(int n) {
		int r = 0;
		while (true) {
			if ((1 << r) > n)
				break;
			r++;
		}
		return r;
	}

	public static String grayEncode(int n, int digit) {
		// int digit = smallestN(n);
		ArrayList<Integer> a = new ArrayList<Integer>();
		encode(digit, n, a);

		int size = a.size();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(a.get(i));
		}
		return sb.toString();
	}

	public static void encode(int col, int row, ArrayList<Integer> out) {
		if (col == 1) {
			if (row == 0)
				out.add(0);
			else
				out.add(1);
			return;
		}

		int half = (1 << (col - 1));
		if (row < half) {
			out.add(0);
			encode(col - 1, row, out);
		} else {
			out.add(1);
			encode(col - 1, (1 << col) - 1 - row, out);
		}
	}
	
	@Test
	public void testGrayEncode() {
		assertEquals("0", grayEncode(0, 1));
		assertEquals("1", grayEncode(1, 1));

		int digit = 2;
		assertEquals("00", grayEncode(0, digit));
		assertEquals("01", grayEncode(1, digit));
		assertEquals("11", grayEncode(2, digit));
		assertEquals("10", grayEncode(3, digit));

		digit = 3;
		assertEquals("010", grayEncode(3, digit));
		assertEquals("110", grayEncode(4, digit));
		assertEquals("111", grayEncode(5, digit));
		assertEquals("100", grayEncode(7, digit));

		assertEquals("1011", grayEncode(13, 4));
	}
	
	@Test
	public  void testSmallestN() {
		assertEquals(1, smallestN(1));
		assertEquals(1, smallestN(1));
		assertEquals(2, smallestN(2));
		assertEquals(3, smallestN(7));
		assertEquals(4, smallestN(8));
	}
	
	public static void main(String[] args) {
		
	}
	
	

}
