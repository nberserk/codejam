package leetcode;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC639 {

    int MOD = (int)Math.pow(10,9)+7;

    long num(String s, int start, long[] dp){
        int N = s.length();
        if(start>N ) return 0;
        if(start==N ) return 1;
        if(dp[start]>0) return dp[start];

        long r= 0;
        if(s.charAt(start)=='*'){
            r += 9*num(s, start+1, dp);
            r %= MOD;

            int multi =1;
            if( start+1<N  ){
                if (s.charAt(start+1)=='*')
                    multi = 15;
                else if (s.charAt(start+1)-'0'<=6)
                    multi=2;
            }

            r += multi * num(s, start+2, dp);
            r%=MOD;
        }else if (s.charAt(start)>'0'){
            r+= num(s, start+1, dp);

            if(start+1<N){
                char next = s.charAt(start+1);
                if (s.charAt(start)=='1'){
                    if(next=='*')
                        r+= 9 * num(s, start+2, dp);
                    else r += num(s, start+2, dp);
                    r%=MOD;
                }
                if (s.charAt(start)=='2'){
                    if( next=='*')
                        r+= 6 * num(s, start+2, dp);
                    else if (s.charAt(start+1)-'0'<=6)
                        r +=  num(s, start+2, dp);
                }
            }

            r%=MOD;
        }

        dp[start] = r;
        //System.out.println(String.format("%d: %d", start, r));
        return r;
    }

    public int numDecodings_recursive(String s) {
        int N = s.length();
        long[] dp = new long[N];
        return (int)num(s, 0, dp);
    }

    public int numDecodings(String s) {
        int N = s.length();
        long[] dp = new long[N+2];
        dp[N] = 1;

        for (int i = N-1; i >=0 ; i--) {
            char c = s.charAt(i);
            long r = 0;
            if(c=='*'){
                r += 9*dp[i+1];
                if(i+1<N){
                    char next = s.charAt(i+1);
                    if(next=='*')
                        r+= 15* dp[i+2];// **, 11-19, 21-26
                    else if(next<'7')
                        r+= 2*dp[i+2]; // 1x, 2x
                    else r += dp[i+2]; //
                }
            }else if (c>'0'){
                r += dp[i+1];
                if(i+1<N){
                    char next = s.charAt(i+1);
                    if(c=='1'){
                        if(next=='*')
                            r+= 9* dp[i+2];
                        else r+= dp[i+2];
                    }else if(c =='2'){
                        if(next=='*')
                            r+= 6* dp[i+2];
                        else if (next < '7')
                            r+= dp[i+2];
                    }
                }
            }
            r%=MOD;
            dp[i] = r;
        }
        return (int)dp[0];
    }



    @org.junit.Test
    public void test(){
        assertEquals(404, numDecodings("*1*1*0"));
        assertEquals(404, numDecodings_recursive("*1*1*0"));

        assertEquals(18, numDecodings("1*"));
        assertEquals(9, numDecodings("*"));
        assertEquals(96, numDecodings("**"));

        assertEquals(18, numDecodings_recursive("1*"));
        assertEquals(9, numDecodings_recursive("*"));


    }
}
