package leetcode;

import static org.junit.Assert.assertEquals;

public class FourKeysKeyboard_651 {

    public int maxA(int N) {

        if(N<=5) return N;
        int[] n = new int[N+1];
        n[1]=1;n[2]=2;n[3]=3;n[4]=4;n[5]=5;
        for(int i=6;i<=N;i++){
            n[i]=n[i-1]+1;
            for (int j = 3; j <= i-3; j++) {
                n[i]=Math.max(n[i], n[j]*(i-j-1));
            }
        }
        return n[N];
    }

    public int maxA_1(int N) {
        if(N<=5) return N;
        int[] n = new int[N+1];
        n[1]=1;n[2]=2;n[3]=3;n[4]=4;n[5]=5;
        for(int i=6;i<=N;i++){
            n[i] = Math.max(n[i-5]*4, n[i-4]*3);
        }
        return n[N];
    }

    @org.junit.Test
    public void test(){
        assertEquals(20, maxA(10));
        assertEquals(27, maxA(11));
    }
}
