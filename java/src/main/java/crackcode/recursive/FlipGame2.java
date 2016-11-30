package crackcode.recursive;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 9. 23..
 *
 * https://leetcode.com/problems/flip-game-ii/
 */
public class FlipGame2 {
    static boolean flip(boolean turn, char[] c){
        boolean ret = !turn;
        for(int i=1;i<c.length;i++){
            if(c[i-1]=='+' && c[i]=='+'){
                c[i-1]=c[i]='-';
                boolean r = flip(!turn, c);
                c[i-1]=c[i]='+';
                if(r==turn)
                    return r;
            }
        }

        return ret;
    }
    static public boolean canWin(String s) {
        if(s.length()<=1) return false;

        char[] c = s.toCharArray();
        //return flip(true, c);
        return flip2(c);
    }

    static boolean flip2(char[] c){
        for(int i=1;i<c.length;i++){
            if(c[i-1]=='+' && c[i]=='+'){
                c[i-1]=c[i]='-';
                if(!flip2(c)){
                    c[i-1]=c[i]='+';
                    return true;
                }

                c[i-1]=c[i]='+';
            }
        }
        return false;
    }

    @Test
    public void test(){

        String s = "++++";

        assertEquals(true, canWin(s));
        assertEquals(false, canWin("+++++"));

    }
}
