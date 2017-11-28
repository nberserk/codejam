package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class MultiplyStrings_43 {

    public String multiply(String num1, String num2) {
        List<Integer> ret = new ArrayList<>();
        int N1 = num1.length();
        int N2 = num2.length();
        for (int i = 0; i < N1+N2; i++) {
            ret.add(0);
        }

        for (int i = 0; i < N2; i++) {
            int v1 = num2.charAt(N2-1-i)-'0';
            for (int j = 0; j < N1; j++) {
                int base = i+j;
                int v2 =num1.charAt(N1-1-j)-'0' ;
                int v = v1*v2;
                ret.set(base, ret.get(base)+v);
            }
        }

        for (int i = 0; i < ret.size(); i++) {
            int v = ret.get(i);
            int carry=v/10;
            ret.set(i, v%10);
            if(carry>0)
                ret.set(i+1, ret.get(i+1)+carry);
        }


        String r = "";
        int N = ret.size();
        boolean firstZero=true;
        for (int i = N-1; i >=0; i--) {
            if(ret.get(i)==0 && firstZero){
                continue;
            }else
                firstZero=false;
            r = r + String.valueOf(ret.get(i));
        }
        if(r.length()==0) r="0";
        return r;
    }


    @Test
    public void test(){
        assertEquals("324", multiply("18", "18"));
        assertEquals("9801", multiply("99", "99"));
        assertEquals("0", multiply("0", "0"));
    }
}
