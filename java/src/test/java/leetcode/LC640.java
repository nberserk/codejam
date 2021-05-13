package leetcode;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC640 {


    int[] parse(String s){
        int[] r = new int[2];

        int i=0;
        int N = s.length();
        int v=0;
        boolean x=false;
        int sign = 1;
        while(i<N){
            char c = s.charAt(i++);

            if(c=='+' || c=='-'){
                if(x){
                    if (v==0){
                        if(i-3>=0 && s.charAt(i-3)=='0')
                            v=0;
                        else
                            v=1;
                    }
                    r[0] += sign * v;
                }else{
                    r[1] += sign*v;
                }
                sign = c=='+' ? 1: -1;
                v=0;
                x=false;
            }else if (c=='x')
                x=true;
            else{
                // digit
                v=v*10 + c-'0';
            }
        }

        if(x){
            if (v==0){
                if(i-2>=0 && s.charAt(i-2)=='0')
                    v=0;
                else
                    v=1;
            }
            r[0] += sign * v;
        }else{
            r[1] += sign*v;
        }
        return r;
    }

    int[] parseAdvanced(String s){
        String[] token = s.split("(?=[-,+])");
        int[] r = new int[2];
        for (String t : token){
            if(t.equals("x") || t.equals("+x")) r[0] +=1;
            else if(t.equals("-x")) r[0] +=-1;
            else if(t.contains("x")) r[0] += Integer.parseInt(t.substring(0, t.length()-1));
            else r[1] += Integer.parseInt(t);

        }

        return r;
    }

    public String solveEquation(String equation) {
        String[] s = equation.split("=");
        int[] left = parse(s[0]);
        int[] right = parse(s[1]);
        if(left[0] == right[0] && left[1] == right[1])
            return "Infinite solutions";
        else if (left[0] == right[0])
            return "No solution";
        else {
            int l = left[0] - right[0];
            int r = right[1] - left[1];
            return "x="+String.valueOf( (int)r/l);
        }
    }


    @org.junit.Test
    public void test(){
        assertEquals("[13, 2]", Arrays.toString(parseAdvanced("12x+5-3+x")));

        assertEquals("Infinite solutions", solveEquation("0x+0x=0x"));
        assertEquals("Infinite solutions", solveEquation("x=x"));
        assertEquals("Infinite solutions", solveEquation("0x=0"));
        assertEquals("[2, 2]", Arrays.toString(parse("x+5-3+x")) );
        assertEquals("[13, 2]", Arrays.toString(parse("x+5-3+12x")) );

        assertEquals("[0, 0]", Arrays.toString(parse("0x")));
        assertEquals("x=2", solveEquation("x+5-3+x=6+x-2"));
        assertEquals("x=-1", solveEquation("2x+3x-6x=x+2"));
    }
}
