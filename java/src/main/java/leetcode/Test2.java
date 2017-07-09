package leetcode;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class Test2 {


    int[] parse(String s){
        int[] r = new int[2];

        int i=0;
        int N = s.length();
        while(i<N){
            char c = s.charAt(i++);
            int v=0;
            boolean plus=true;
            if(c=='+'){
                c = s.charAt(i++);
            }else if (c=='-'){
                plus=false;
                c = s.charAt(i++);
            }else{
                v = c-'0';
            }

            while(i<N && Character.isDigit(s.charAt(i))){
                v= v*10 + s.charAt(i++)-'0';
            }
            if(s.charAt(i)=='x'){
                if(v==0)v++;
                r[0] += v;
            }else{
                if(!plus) v=-v;
                r[1] +=v;
            }
        }

        return r;
    }

    public String solveEquation(String equation) {
        String[] s = equation.split("=");
        int[] left = parse(s[0]);
        int[] right = parse(s[1]);

        return "";
    }


    @org.junit.Test
    public void test(){
        assertEquals("[2,2]", Arrays.toString(parse("x+5-3+x")) );
        //"=6+x-2"
//        assertEquals(167, );
    }
}
