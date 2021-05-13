package leetcode;

import static org.junit.Assert.assertEquals;

public class LC738 {
    public int monotoneIncreasingDigits(int N) {
        String s = String.valueOf(N);
        if(s.length()==1) return N;
        char[] ch = s.toCharArray();
        for (int i = s.length()-2; i >=0 ; i--) {
            if (ch[i]>ch[i+1]){
                ch[i]--;
                ch[i+1]='9';
            }
        }

        boolean meetNine=false;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i]=='9')
                meetNine=true;
            if (meetNine)
                ch[i]='9';
        }

        return Integer.valueOf(String.valueOf(ch));
    }


    @org.junit.Test
    public void test(){
        assertEquals(299, monotoneIncreasingDigits(332));
        assertEquals(9, monotoneIncreasingDigits(10));
        assertEquals(229, monotoneIncreasingDigits(232));
        assertEquals(1234, monotoneIncreasingDigits(1234));
    }
}
