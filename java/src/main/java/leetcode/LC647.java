package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PalindromeSubstrings_647 {


    public int countSubstrings(String s) {
        int N = s.length();
        if(N==0) return 0;
        int r =N;
        char[] ch = s.toCharArray();
        boolean[][] dp = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] =true;
        }

        //even
        for (int len = 1; len < N; len+=2) {
            boolean found=false;
            for (int i = 0; i < N - len; i++) {
                if(ch[i]==ch[i+len] && (len==1 || dp[i+1][i+len-1]) ){
                    dp[i][i+len]=true;
                    r++;
                    found=true;
                }
            }
            if (!found)break;
        }

        //odd
        for (int len = 2; len < N; len+=2) {
            boolean found=false;
            for (int i = 0; i < N - len; i++) {
                if(ch[i]==ch[i+len] && dp[i+1][i+len-1] ){
                    dp[i][i+len]=true;
                    r++;
                    found=true;
                }
            }
            if (!found)break;
        }
        return r;
    }



    @Test
    public void test(){
        assertEquals(3, countSubstrings("abc"));
        assertEquals(6, countSubstrings("aaa"));
    }
}
