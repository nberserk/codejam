package leetcode;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class KSimilarity_854 {

    int[] cache;

    int sub(char[] A, char[] B, int start){
        int N = A.length;
        if(start==N-1)return 0;
//        if (cache[start]!= Integer.MAX_VALUE)
//            return cache[start];

        char dest = B[start];
        char src = A[start];
        if(src==dest)
            return sub(A, B, start+1);

        int ret = Integer.MAX_VALUE;
        for (int i = start+1; i < N; i++) {
            if (A[i]==dest && A[i]!=B[i]){
                char t = src;
                A[start]=A[i];
                A[i]=t;
                ret = Math.min(ret, 1+sub(A,B,start+1));
                A[i]=A[start];
                A[start]=src;
            }
        }
        return ret;
    }

    public int kSimilarity(String A, String B) {

        cache = new int[A.length()];
        Arrays.fill(cache, Integer.MAX_VALUE);

        return sub(A.toCharArray(),B.toCharArray(), 0);
    }



    @org.junit.Test
    public void test(){
        assertEquals(1, kSimilarity("ab", "ba"));
        assertEquals(2, kSimilarity("abc", "bca"));
        assertEquals(2, kSimilarity("aabc", "abca"));
        assertEquals(12, kSimilarity("cdebcdeadedaaaebfbcf", "baaddacfedebefdabecc"));
    }
}
