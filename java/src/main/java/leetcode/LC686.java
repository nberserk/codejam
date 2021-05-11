package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class RepeatedStringMatch_686 {

    boolean check(String p, String t, int offset){
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != t.charAt(offset+i)) return false;
        }
        return true;
    }

    public int repeatedStringMatch(String pattern, String text) {

        String org_pattern=pattern;
        StringBuilder sb = new StringBuilder();
        int r=0;
        int multiple = (text.length()-1)/pattern.length() + 1; // why?
        for (int i = 0; i <= multiple; i++) {
            sb.append(pattern);
            r++;
        }

        pattern = text;
        text=sb.toString();

        int MOD=100001;
        int base=30;
        int p=0,t=0;

        int P = pattern.length();
        int T = text.length();
        int power=1;

        for (int i = 0; i < P; i++) {
            p = (base*p +pattern.charAt(i))%MOD;
            t = (base*t + text.charAt(i))%MOD;
            if(i!=0)
                power=(power*base)%MOD;
        }

        for (int i = 0; i <= T-P; i++){
            if (p==t){
                if(check(pattern, text, i)){
                    int remain = T-(i+P);
                    if(remain>= org_pattern.length())
                        r--;
                    return r;
                }
            }
            if(i<T-P){
                t=(base*(t-text.charAt(i)*power) + text.charAt(i+P))%MOD;
                if(t<0)
                    t+=MOD;
            }
        }

        return -1;
    }


    @Test
    public void test(){
        assertEquals(1, repeatedStringMatch("aa", "a"));
        assertEquals(3, repeatedStringMatch("abcd", "cdabcdab"));
        assertEquals(2, repeatedStringMatch("abcd", "abcdabcd"));
        assertEquals(2, repeatedStringMatch("abababaaba", "aabaaba"));


    }
}
