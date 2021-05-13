package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC806 {
    public int[] numberOfLines(int[] widths, String S) {
        int lines=1;
        int width=0;
        for (char c: S.toCharArray()){
            width += widths[c-'a'];
            if(width>100){
                lines++;
                width=widths[c-'a'];
            }
        }
        return new int[]{lines, width};
    }

    @Test
    public void test(){
        assertEquals("[3, 60]", Arrays.toString(numberOfLines(
                new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                "abcdefghijklmnopqrstuvwxyz"))
        );
    }
}
