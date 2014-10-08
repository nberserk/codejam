package crackcode;

public class Palindrome {

    public static void main(String[] args) {

        CheckUtil.check(0, minInsertionToMakePalindrome("aba".toCharArray(), 0, 2));
        CheckUtil.check(1, minInsertionToMakePalindrome("ab".toCharArray(), 0, 1));
        CheckUtil.check(2, minInsertionToMakePalindrome("abc".toCharArray(), 0, 2));

        CheckUtil.check(3, minInsertionToMakePalindrome("abcd".toCharArray(), 0, 3));
        CheckUtil.check(2, minInsertionToMakePalindrome("abccbaff".toCharArray(), 0, 7));
        CheckUtil.check(2, minInsertionToMakePalindrome_Table("abccbaff".toCharArray(), 0, 7));
        // CheckUtil.check(0, minInsertionToMakePalindrome("abc".toCharArray(), 0, 2));
        // CheckUtil.check(0, minInsertionToMakePalindrome("abc".toCharArray(), 0, 2));
    }

    static int minInsertionToMakePalindrome(char[] a, int start, int end) {
        if (end - start == 0) {
            return 0;
        } else if (end - start == 1) {
            if (a[start] == a[end])
                return 0;
            return 1;
        }

        int ret;
        if (a[start] == a[end])
            ret = minInsertionToMakePalindrome(a, start + 1, end - 1);
        else {
            ret = 1 + minInsertionToMakePalindrome(a, start + 1, end);
            ret = Math.min(ret, 1 + minInsertionToMakePalindrome(a, start, end - 1));
        }
        return ret;
    }

    static int minInsertionToMakePalindrome_Table(char[] a, int start, int end) {
        int n = a.length;
        int[][] t = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            t[0][0] = 0;
            t[i][i + 1] = a[i] == a[i + 1] ? 0 : 1;
        }

        for (int c = 2; c < n; c++) {
            for (int s = 0; s < n - c; s++) {
                int e = s + c;
                int r = Integer.MAX_VALUE;
                if (a[s] == a[e]) {
                    t[s][e] = t[s + 1][e - 1];
                } else {
                    t[s][e] = Math.min(1 + t[s + 1][e], 1 + t[s][e - 1]);
                }
            }
        }

        return t[0][n - 1];
    }

}
