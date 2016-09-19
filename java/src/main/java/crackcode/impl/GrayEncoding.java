package main.java.crackcode.impl;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

	/**
	 * return n bit gray code
	 * @param k
	 * @return
	 */
	public static List<Integer> grayEncode(int n){
		if(n<=0) return null;
		List<Integer> r = new ArrayList<>();
        r.add(0);
        r.add(1);

        for (int i = 2; i <= n; i++) {
            int size = r.size();
            for (int j = 0; j < size; j++) {
                int c = (1<<(i-1)) | r.get(size-1-j);
                r.add(c);
            }
        }
        return r;
	}

    @Test
    public void testGrayEncode(){
        List<Integer> r = grayEncode(1);
        assertEquals(0, (int)r.get(0));
        assertEquals(1, (int)r.get(1));
        r = grayEncode(2);
        assertEquals(3, (int)r.get(2));
        assertEquals(2, (int)r.get(3));
    }

	public static String grayEncodeRecursive(int n, int digit) {
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
	public void testGrayEncodeRecursive() {
		assertEquals("0", grayEncodeRecursive(0, 1));
		assertEquals("1", grayEncodeRecursive(1, 1));

		int digit = 2;
		assertEquals("00", grayEncodeRecursive(0, digit));
		assertEquals("01", grayEncodeRecursive(1, digit));
		assertEquals("11", grayEncodeRecursive(2, digit));
		assertEquals("10", grayEncodeRecursive(3, digit));

		digit = 3;
		assertEquals("010", grayEncodeRecursive(3, digit));
		assertEquals("110", grayEncodeRecursive(4, digit));
		assertEquals("111", grayEncodeRecursive(5, digit));
		assertEquals("100", grayEncodeRecursive(7, digit));

		assertEquals("1011", grayEncodeRecursive(13, 4));
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
