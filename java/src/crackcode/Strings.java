package crackcode;

public class Strings {

	public static void main(String[] args) {

		CheckUtil.check(445, toInt("445"));
		CheckUtil.check(-335, toInt("-335"));

		CheckUtil.check("789", toString(789));
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
