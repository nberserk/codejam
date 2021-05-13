package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class LC867 {

    public int primePalindrome(int N) {
        if (N == 1 || N == 2) return 2;
        if (N % 2 == 0) N++;

        while (true) {
            if (isPalindrome(N) && isPrime(N)) {
                return N;
            }
            N += 2;
        }
    }

    private boolean isPalindrome(int n) {
        int n1 = 0, i = n;
        while (i > n1) {
            n1 = n1 * 10 + (i % 10);
            i /= 10;
        }
        return n1 == i || i == n1 / 10;
    }

//    private boolean isPalindrome(int i) {
//        String v = String.valueOf(i);
//        int s = 0;
//        int e = v.length() - 1;
//        while (s < e) {
//            if (v.charAt(s) != v.charAt(e)) return false;
//            s++;
//            e--;
//        }
//        return true;
//    }

    private boolean isPrime(int i) {
        int to = (int) Math.sqrt(i);
        for (int j = 3; j <= to; j++) {
            if (i % j == 0) return false;
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertEquals(5, primePalindrome(5));
        Assert.assertEquals(100030001, primePalindrome(100000000));
    }
}
