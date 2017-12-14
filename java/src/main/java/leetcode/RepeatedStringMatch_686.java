package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class RepeatedStringMatch_686 {

    boolean check(String p, String t, int ti){
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != t.charAt(ti+i)) return false;
        }
        return true;
    }

    public int repeatedStringMatch(String pattern, String text) {

        StringBuilder sb = new StringBuilder();
        int r=0;
        while(sb.length()<=text.length()){
            sb.append(pattern);
            r++;
        }
        pattern = text;
        text=sb.toString();

        int MOD=10001;
        int d=256;
        int p=0,t=0;

        int P = pattern.length();
        int T = text.length();
        int power=1;

        for (int i = 0; i < P; i++) {
            p = (d*p +pattern.charAt(i))%MOD;
            t = (d*t + text.charAt(i))%MOD;
            power=(power*d)%MOD;
        }




        for (int i = 0; i <= T-P; i++){
            if (p==t){
                if(check(pattern, text, i))
                    return r;
            }
            if(i<T-P){
                t=(d*(t-text.charAt(i)*power) + text.charAt(i+P))%MOD;
                if(t<0)
                    t+=MOD;
            }
        }

        return -1;
    }


    @Test
    public void test(){

        assertEquals(3, repeatedStringMatch("abcd", "cdabcdab"));
    }
}
