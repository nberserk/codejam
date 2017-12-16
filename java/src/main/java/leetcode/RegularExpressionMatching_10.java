package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/2/16.
 *
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * idea:
 * if dp[i][j] is true,  str[i-1] pattern[j-1] matched
 *
 */
public class RegularExpressionMatching_10 {

    boolean matchRecursive(char[] s, char[] p, int si, int pi, int[][] dp){
        int S = s.length;
        int P=p.length;
        if(pi==P && si==S) return true;
        if(pi==P) return false;
        if(si==S){
            if(pi+1<P&&p[pi+1]=='*')
                return matchRecursive(s,p,si,pi+2,dp);
            return false;
        }

        if(dp[si][pi]!=-1) return dp[si][pi]==1? true:false;
        boolean ret=false;
        if(pi+1<P&&p[pi+1]=='*'){
            ret = matchRecursive(s,p, si, pi+2,dp);
            if(!ret && (p[pi]==s[si] || p[pi]=='.'))
                ret = matchRecursive(s,p,si+1,pi,dp);
        }else{
            if(p[pi]==s[si] || p[pi]=='.')
                ret = matchRecursive(s,p,si+1,pi+1,dp);
            else
                ret=false;
        }

        dp[si][pi]=ret?1:0;
        return ret;
    }


    public boolean isMatch(String s, String p) {
        int plen = p.length();
        int slen = s.length();
        int[][] dp =new int[slen][plen];
        for(int[] d: dp)
            Arrays.fill(d,-1);
        return matchRecursive(s.toCharArray(), p.toCharArray(), 0, 0,dp );
    }

    boolean matchIterative(String str, String pattern){
        int S = str.length();
        int P = pattern.length();

        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();

        boolean[][] dp = new boolean[S+1][P+1];
        for (int i = 2; i <=P ; i++) {
            if ( p[i-1]=='*' )
                dp[0][i]= dp[0][i-2];
        }
        dp[0][0] =true;
        for (int i = 1; i <= S; i++) {
            for (int j = 1; j <= P; j++) {
                if (p[j-1]=='*'){
                    dp[i][j] = dp[i][j-2] || ( (s[i-1]==p[j-2] || p[j-2]=='.') && dp[i-1][j] );
                }else if( s[i-1]==p[j-1] || p[j-1]=='.')
                    dp[i][j] = dp[i-1][j-1] ;
            }
        }

        return dp[S][P];
    }

    @Test
    public void test(){
        assertEquals(true, isMatch("aa", "a*"));
        assertEquals(true, isMatch("aab", "c*a*b"));
        assertEquals(true, isMatch("bbbba", ".*a*a"));
        assertEquals(true, isMatch("ab", ".*"));
        assertEquals(true, isMatch("aab", ".*"));

//        assertEquals(true, matchIterative("aa", "a*"));
//        assertEquals(true, matchIterative("aab", "c*a*b"));
//        assertEquals(true, matchIterative("bbbba", ".*a*a"));
//        assertEquals(true, matchIterative("ab", ".*"));
//        assertEquals(true, matchIterative("aab", ".*"));

        int a = 66;
        String cc = "aa" +(char) (a+'a');
        System.out.println(cc);
    }
}
