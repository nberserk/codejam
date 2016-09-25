package main.java.crackcode.impl;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 9/25/16.
 */
public class Fraction {

    public String fractionToDecimal(int numer, int denomi) {


        long nu = numer;
        long deno = denomi;
        if(nu<0 && deno<0){
            nu=-nu;
            deno=-deno;
        }
        boolean minus=false;
        if(nu*deno<0) minus=true;
        if(minus){
            if(nu<0)    nu=-nu;
            else deno=-deno;
        }

        StringBuilder sb = new StringBuilder();
        long temp = nu/deno;
        sb.append(temp);
        nu = nu%deno;
        if(nu>0)
            sb.append(".");
        ArrayList<Long> history = new ArrayList<Long>();

        while(nu>0){
            nu*=10;
            if(history.contains(nu) ){
                // time to exit
                int idx = history.indexOf(nu);
                int back = history.size() - idx; // 0:len,
                String asis = sb.toString();
                String prefix = asis.substring(0, asis.length()-back );
                String after = asis.substring(asis.length()-back );
                return (minus ? "-" : "" ) + prefix+"(" + after + ")";
            }
            history.add(nu);
            long cur = nu/deno;
            nu=nu%deno;
            sb.append(cur);
        }
        //System.out.println(minus);
        return (minus ? "-" : "" ) +sb.toString();
    }

    @Test
    public void test(){
        String r = fractionToDecimal(-1, -2147483648);
        assertEquals("0.0000000004656612873077392578125", r);

        String r1 = fractionToDecimal(-2147483648, 1);
        assertEquals("-2147483648", r1);

        r = fractionToDecimal(7, -12);
        assertEquals("-0.58(3)", r);


    }
}
