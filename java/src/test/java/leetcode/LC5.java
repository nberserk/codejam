package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 09/02/2017.
 */
public class LC5 {

    public String longestPalindrome(String s) {
        int N = s.length();
        if(N==0) return "";

        int max = 1;
        int fs=0,fe=1;
        String ret=s.substring(0,1);
        boolean [][] dp = new boolean[N][N];

        for(int i=0;i<N;i++)
            dp[i][i]=true;

        for(int l=1;l<N;l++){
            for(int st=0;st<N-l;st++){
                int e = st+l;
                if(s.charAt(st) ==s.charAt(e)){
                    if(st+1>e-1)
                        dp[st][e] = true;
                    else dp[st][e] = dp[st+1][e-1];
                }else
                    dp[st][e] = false;
                if(dp[st][e] && e-st+1>max){
                    max = e-st+1;
                    fs=st;
                    fe=e+1;

                }
            }
        }
        ret = s.substring(fs, fe);
        return ret;
    }


    @Test
    public void test(){
        assertEquals("bab", longestPalindrome("babad"));
        assertEquals("bb", longestPalindrome("cbbd"));
    }
}
