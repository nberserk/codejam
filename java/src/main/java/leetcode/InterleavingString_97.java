package leetcode;

import org.junit.Assert;

import java.util.Arrays;


public class InterleavingString_97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int N = s1.length();
        int M = s2.length();

        if(s3.length()!= N+M) return false;

        int MN = Math.max(N,M)+1;

        int[][] cache = new int[MN][MN];//1:not possible, 2:possible
        Arrays.fill(cache[N], 1);
        for (int i = 0; i < N; i++) {
            cache[i][M]=1;
        }
        cache[N][M]=2;

        int T=N+M;
        for (int l = T-1; l >=0; l--) {
            for (int n = 0; n <= l; n++) {
                int m = l-n;
                if(n>N || m>M) {
                    //cache[n][m]=1;
                    continue;
                }
                int v = 1;
                if(n<N&& s1.charAt(n)==s3.charAt(l)){
                    v = cache[n+1][m];
                }

                if(v==1 && m<M && s2.charAt(m)==s3.charAt(l)){
                    v = cache[n][m+1];
                }
                cache[n][m] = v;
            }
        }

        return cache[0][0]==2;
    }

    @org.junit.Test
    public void test2(){
        Assert.assertEquals(false, isInterleave("a", "b", "a"));
        Assert.assertEquals(true, isInterleave("ab", "cd", "acbd"));
        Assert.assertEquals(true, isInterleave("bcc", "dbbca", "dbbcbcac"));
        Assert.assertEquals(false, isInterleave("aabcc", "dbbca", "aadbbbaccc"));

    }
}
