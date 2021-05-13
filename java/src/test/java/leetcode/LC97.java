package leetcode;

import org.junit.Assert;


public class LC97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int N = s1.length();
        int M = s2.length();

        if(s3.length()!= N+M) return false;

        int MN = Math.max(N,M)+1;

        boolean[][] cache = new boolean[N+1][M+1];//1:not possible, 2:possible

        for (int n = 0; n <=N ; n++) {
            for (int m = 0; m <= M; m++) {
                if (n==0 && m==0)
                    cache[n][m]=true;
                else{
                    boolean v = false;
                    if (n>0 && s1.charAt(n-1)==s3.charAt(n+m-1)){
                        v=cache[n-1][m];
                    }
                    if(v==false && m>0 && s2.charAt(m-1)==s3.charAt(n+m-1)){
                        v=cache[n][m-1];
                    }
                    cache[n][m]=v;
                }
            }
        }
        return cache[N][M];
    }

    @org.junit.Test
    public void test2(){
        Assert.assertEquals(false, isInterleave("a", "b", "a"));
        Assert.assertEquals(true, isInterleave("ab", "cd", "acbd"));
        Assert.assertEquals(true, isInterleave("bcc", "dbbca", "dbbcbcac"));
        Assert.assertEquals(false, isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }
}
