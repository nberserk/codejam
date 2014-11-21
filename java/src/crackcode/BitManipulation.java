package crackcode;

import codejam.lib.CheckUtil;

public class BitManipulation {

    private static int[] mA;

	public static void main(String[] args) {

		CheckUtil.check(0.11, Double.parseDouble(".11"));

		CheckUtil.check(1 << 30, getNextLargest(0b1));
		CheckUtil.check((1 << 30) | (1 << 29), getNextLargest(0b11));

		int n = 0b111111111;
		CheckUtil.check(0b111111011, clearBit(n, 2));
		CheckUtil.check(0b111110111, clearBit(n, 3));
		CheckUtil.check(0, getBit(n, 30));
		CheckUtil.check(1, getBit(n, 0));
		CheckUtil.check(1, getBit(n, 8));
		CheckUtil.check(0b1111111111, setBit(n, 9));

		CheckUtil.check("error", toBinary("17.72"));
		CheckUtil.check("11.01", toBinary("3.25"));

		int ret = updateBits(0b10000000000, 0b10101, 2, 6);
		System.out.println(String.format("%s", Integer.toBinaryString(ret)));

        int N = 8;
        mA = new int[N];
        mA[0] = 1;
        mA[1] = 2;
        mA[2] = 4;
        mA[3] = 5;
        mA[4] = 3;
        mA[5] = 6;
        mA[6] = 0;
        mA[7] = 8;
        int miss = findMissing(N);
        System.out.println(miss);
        
		int n2 = -1;
		n2 = (n2 & 0b100001);
		n2 = (n2 >>> 4);
		System.out.println(Integer.toBinaryString(n2));

		CheckUtil.check(32, numOf1Bit(-1));
		CheckUtil.check(1, numOf1Bit(1));
		CheckUtil.check(2, numOf1Bit(5));
		CheckUtil.check(0, numOf1Bit(0));

		// swapOddEvenBits
		CheckUtil.check(2, swapOddEvenBits(1));
		CheckUtil.check(10, swapOddEvenBits(5));
    }

	static int numOf1Bit(int n) {
		int count = 0;
		int mask = 1;
		for (int i = 0; i < 32; i++) {
			if ((mask & n) > 0)
				count++;
			n = n >>> 1;
			if (n == 0)
				break;
		}
		return count;
	}

    static int fetchBit(int i, int j) {
        return mA[i] & (1 << j);
    }

    static int findMissing(int n) {
        boolean[] filtered = new boolean[n];
        int ret = 0;

        int max = 0, cur = n;
        while (cur > 0) {
            cur = cur >> 1;
            max++;
        }

        for (int i = 0; i < max; i++) {
            int count0 = 0;
            int count1 = 0;
            for (int j = 0; j < n; j++) {
                if (filtered[j]) {
                    continue;
                }

                if (fetchBit(j, i) > 0) {
                    count1++;
                } else {
                    count0++;
                }
            }
            boolean is1Seletect = false;
            if (count0 > count1) {
                ret = ret | (1 << i);
                is1Seletect = true;
            }
            for (int j = 0; j < n; j++) {
                if (filtered[j]) {
                    continue;
                }
                if (is1Seletect && fetchBit(j, i) >0) {
                    filtered[j]=true;
                } else if (!is1Seletect && fetchBit(j, i) == 0) {
                    filtered[j] = true;
                }
            }
        }
        return ret;
	}

	static int getBit(int n, int idx) {
		return (n & (1 << idx)) == 0 ? 0 : 1;
	}

	static int clearBit(int n, int idx) {

		return (n & ~(1 << idx));
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

	static int swapOddEvenBits(int x) {
		return ((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1);
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
