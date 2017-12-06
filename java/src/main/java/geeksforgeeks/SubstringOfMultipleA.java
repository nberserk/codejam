package geeksforgeeks;

import static org.junit.Assert.assertEquals;

public class SubstringOfMultipleA {

    boolean match(String ref, String s, int si){
        for (int i = si; i < s.length(); i++) {
            if (s.charAt(i)!=ref.charAt(i-si))
                return false;
        }
        return true;
    }

    int times(String A, String B){
        int times=0;
        int i=0;
        for (i = 0; i < A.length(); i++) {
            if (match(B, A, i)){
                break;
            }
        }
        if(i==A.length()) return -1;
        times++;
        i=A.length()-i;
        int ai=0;
        for (; i < B.length(); i++) {
            if(B.charAt(i)!=A.charAt(ai))
                return -1;

            ai++;
            if(ai==A.length()){
                times++;
                ai=0;
            }
        }
        if(ai>0) times++;
        return times;
    }


    @org.junit.Test
    public void test(){
        assertEquals(2, times("abcd", "dabc"));
        assertEquals(-1, times("abcd", "dabcc"));
        assertEquals(3, times("abcd", "cdabcdab"));

//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
