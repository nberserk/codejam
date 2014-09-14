package crackcode;

public class BitManipulation {

	static void check(int expected, int actual) {
		if (expected != actual) {
			System.out.println("check failed: expected:" + expected
					+ ", actual:" + actual);
		}
	}

	public static void main(String[] args) {

		check(1 << 30, getNextLargest(0b1));
		check((1 << 30) | (1 << 29), getNextLargest(0b11));

		int n = 0b111111111;
		check(0b111111011, clearBit(n, 2));
		check(0, getBit(n, 30));
		check(1, getBit(n, 0));
		check(1, getBit(n, 8));
		check(0b1111111111, setBit(n, 9));


		String bin = toBinary("17.72");
		System.out.println(bin);

		int ret = updateBits(0b10000000000, 0b10101, 2, 6);
		System.out.println(String.format("%s", Integer.toBinaryString(ret)));

	}
	
	static int getBit(int n, int idx) {
		return (n & (1 << idx)) == 0 ? 0 : 1;
	}

	static int clearBit(int n, int idx) {
		int full = ~0;

		int low = (1 << idx) - 1;
		int high = full << (idx + 1);
		int mask = low | high;
		// System.out.println(Integer.toBinaryString(mask));
		return (n & mask);
	}

	static int setBit(int n, int idx) {
		return n | (1 << idx);
	}

	static int getNextLargest(int n) {
		if (n == 0 || n == -1) {
			return -1;
		}
		int r = n;
		int destIdx = 30;
		while (getBit(r, destIdx) == 1) {
			destIdx--;
			if (destIdx < 0) {
				break;
			}
		}
		if (destIdx == -1) {
			return -1;
		}

		for (int i = 0; i < 30; i++) {
			if (i >= destIdx) {
				break;
			}
			if (getBit(n, i) == 1) {
				r = clearBit(r, i);
				r = setBit(r, destIdx--);
				System.out.println(Integer.toBinaryString(r));
				while (getBit(r, destIdx) == 1) {
					destIdx--;
					if (destIdx < 0)
						break;
				}
			}
		}
		return r;
	}
	
	static int updateBits(int N, int M, int i, int j) {
		int mask = ~0;
		int left = mask >> (31 - i);
		int right = mask << j;
		int mask2 = left | right;
		
		return N & mask2 | (M << i);
	}

	static String toBinary(String decimal) {
		int ipart = Integer
				.parseInt(decimal.substring(0, decimal.indexOf('.')));
		double dpart = Double.parseDouble(decimal.substring(
				decimal.indexOf('.'), decimal.length()));

		String ipartS = "";
		while (ipart > 0) {
			int r = ipart % 2;
			ipart /= 2;
			ipartS = r + ipartS;
		}

		String dpartS = "";
		while (true) {
			if (dpartS.length() > 30) {
				return "error";
			}
			dpart *= 2;
			if (dpart == 1) {
				dpartS += "1";
				break;
			}

			if (dpart > 1) {
				dpart -= 1;
				dpartS += "1";
			} else {
				dpartS += "0";
			}
		}

		return ipartS + "." + dpartS;
	}

}
