package crackcode;

import java.util.Arrays;

import codejam.lib.CheckUtil;

public class Strings {

	public static void main(String[] args) {

		CheckUtil.check(445, toInt("445"));
		CheckUtil.check(-335, toInt("-335"));

		CheckUtil.check("789", toString(789));
		char[] s = "hello there!".toCharArray();
		CheckUtil.check("!ereht olleh".toCharArray(), reverseString(s));

		char[] s2 = "aaaaaaaaaaaaaaaaa".toCharArray();
		removeDuplicate(s2);
		System.out.println(Arrays.toString(s2));

		// sub string
		String raw = "abc";
		String r2 = raw.substring(0, 3);
		String r3 = new String(s2);
		System.out.println(r2);

		String org = " do or do not, ";
		CheckUtil.check(" not, do or do ", reverseWord(org));
	}

	static void removeDuplicate(char[] in) {
		if (in.length < 2) {
			return;
		}

		int i, j;
		int tail = 1;
		for (i = 1; i < in.length; i++) {
			for (j = 0; j < 1; j++) {
				if (in[i] == in[j])
					break;
			}

			if (j == tail) {
				in[tail] = in[i];
				tail++;
			}
		}

		in[tail] = 0;
	}

	static char[] reverseString(char[] in) {
		if (in == null) {
			return in;
		}

		int i = 0;
		int j = in.length - 1;

		while (i < j) {
			swap(in, i, j);
			i++;
			j--;
		}
		return in;
	}

	static String reverseWord(String s) {
		StringBuilder sb = new StringBuilder(s.length());

		int start = -1;
		int end = -1;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (c == ' ') {
				if (start != -1 && end != -1) {
					sb.append(s.substring(start, end + 1));
					start = end = -1;
				}
				sb.append(c);
			} else if (end == -1) {
				end = i;
			} else {
				start = i;
				if (i == 0) {
					sb.append(s.substring(start, end + 1));
				}
			}
		}
		return sb.toString();
	}

	private static void swap(char[] c, int i, int j) {
		char t = c[i];
		c[i] = c[j];
		c[j] = t;
	}

	static String toString(int n) {
		StringBuilder sb = new StringBuilder();
		boolean isMinus = false;
		if (n < 0) {
			isMinus = true;
			n *= -1;
		} else if (n == 0)
			return "0";

		while (n > 0) {
			int mod = n % 10;			
			sb.append((char) ('0' + mod));
			n /= 10;
		}
		sb.reverse();

		return isMinus ? "-" + sb.toString() : sb.toString();
	}

	static int toInt(String s) {
		int ret = 0;
		boolean isMinus = false;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '-') {
				isMinus = true;
			} else {
				ret *= 10;
				ret += c - '0';
			}
		}

		if (isMinus)
			ret *= -1;
		return ret;
	}

}
