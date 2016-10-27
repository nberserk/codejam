package main.java.crackcode.dp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/26/16.
 *
 * https://leetcode.com/problems/wildcard-matching/
 *
 *
 */
public class WildcardMatching {

    public boolean isMatchRecursive(String string, String pattern){
        pattern = pattern.replaceAll("\\*\\*", "\\*");
        int[][] dp = new int[string.length()+1][pattern.length()+1];
        return helper(string.toCharArray(),0, pattern.toCharArray(), 0, dp);
    }

    private boolean helper(char[] s, int si, char[] p, int pi, int[][] dp) {
        int S = s.length;
        int P = p.length;
        if(si==S && pi==P) return true;
        if(pi==P) return false;
        if(dp[si][pi]!=0) return dp[si][pi]==1;

        boolean ret =false;
        if(si==S){
            if(p[pi]=='*')
                ret = helper(s, si, p, pi+1, dp);
            else ret = false;
        }else{
            if(p[pi]=='*')
                ret = helper(s, si+1,p,pi,dp) || helper(s, si,p, pi+1, dp);
            else if (p[pi]=='?' || p[pi]==s[si])
                ret = helper(s, si+1, p, pi+1, dp);
        }

        dp[si][pi]= ret? 1: 2;
        return ret;
    }


    /**
     * dp[i][j] : if ~s[i-1] and ~p[j-1] matches, true. false otherwise
     *
     * dp[i][j] = if p[j-1]='*', dp[i][j-1] || dp[i-1][j] ( skip star)
     *            if p[j-1]='?' or p[j-1]=s[i-1], dp[i-1][j-1]
     *
     */

    public boolean isMatch(String string, String pattern) {
        pattern = pattern.replaceAll("\\*\\*", "\\*");

        char[]s = string.toCharArray();
        char[]p = pattern.toCharArray();
        int sn = s.length;
        int pn = p.length;
        boolean[][] dp = new boolean[sn+1][pn+1];
        dp[0][0]=true; // before start

        for (int i = 1; i <= pn; i++) {
            if(p[i-1]=='*')
                dp[0][i]=true;
            else
                break;
        }
//        for (int i = 1; i <= pn; i++) {
//            dp[i][0] =false;
//        }

        for (int i = 1; i <= sn; i++) {
            for (int j = 1; j <= pn; j++) {
                if (p[j-1]=='?' || p[j-1]==s[i-1])
                    dp[i][j] = dp[i-1][j-1];
                else if(p[j-1]=='*'){
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
            }
        }

        return dp[sn][pn];
    }

    @Test(timeout = 1000)
    public void testRecursive(){
        assertEquals(false, isMatchRecursive("ab", "*a"));
        assertEquals(true, isMatchRecursive("aa", "*"));
        assertEquals(true, isMatchRecursive("ab", "?*"));
        assertEquals(false, isMatchRecursive("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb"
                ,"**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
    }

    @Test(timeout=1000)
    public void test(){
        //assertEquals("a**a", "a****a".replaceAll("\\*\\*", "\\*"));
        assertEquals(false, isMatch("ab", "*a"));
        assertEquals(true, isMatch("aa", "*"));
        assertEquals(true, isMatch("ab", "?*"));
        assertEquals(false, isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb"
                , "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));


    }
}
