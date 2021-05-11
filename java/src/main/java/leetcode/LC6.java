package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 16/02/2017.
 */
public class ZigZagConversion_6 {

    public String convert(String s, int numRows) {
        if(numRows==1) return s;

        String[] rows = new String[numRows];
        for(int j=0;j<numRows;j++)
            rows[j]="";
        int dir = 0;
        int i=0;
        int r =0;
        int N = s.length();
        while(i<N){
            rows[r] = rows[r]+ s.charAt(i);
            i++;
            if(dir==0){
                if(r==numRows-1) {
                    dir=1;
                    r--;
                }else r++;
            }else{
                if(r==0){
                    dir=0;
                    r++;
                }else r--;
            }

        }

        String ret="";
        for(String t: rows){
            ret+=t;
        }
        return ret;

    }

    public String convert_2(String s, int numRows) {
        if(numRows==1) return s;

        String[] rows = new String[numRows];
        for(int j=0;j<numRows;j++)
            rows[j]="";

        int i=0;
        int N = s.length();
        while(i<N){
            for (int j = 0; j < numRows && i<N; j++) {
                rows[j] = rows[j]+ s.charAt(i++);
            }

            for (int j = numRows-2; j > 0 && i<N; j--) {
                rows[j] = rows[j]+ s.charAt(i++);
            }
        }

        String ret="";
        for(String t: rows){
            ret+=t;
        }
        return ret;
    }


    @Test
    public void test(){
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
        assertEquals("PAYPALISHIRING", convert("PAYPALISHIRING", 1));
        assertEquals("PYAIHRNAPLSIIG", convert("PAYPALISHIRING", 2));

        assertEquals("PAHNAPLSIIGYIR", convert_2("PAYPALISHIRING", 3));
        assertEquals("PAYPALISHIRING", convert_2("PAYPALISHIRING", 1));
        assertEquals("PYAIHRNAPLSIIG", convert_2("PAYPALISHIRING", 2));

    }
}
