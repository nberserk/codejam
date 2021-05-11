package leetcode;

import static org.junit.Assert.assertEquals;

public class BitwiseAndOfNumbersRange_201 {

    public int rangeBitwiseAnd(int m, int n) {
        int r=0;
        int th=30; // not 31, you should be wared
        while(th>=0){
            int mask = 1<<th;
            if( (m&mask) != (n&mask) )
                break;
            if((m&mask)>0)
                r|= mask;
            th--;
        }

        return r;
    }


    @org.junit.Test
    public void test(){
        assertEquals(4, rangeBitwiseAnd(5,7));
        assertEquals(1, rangeBitwiseAnd(1,1));
    }
}
