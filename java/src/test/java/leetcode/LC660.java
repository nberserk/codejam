package leetcode;

import static org.junit.Assert.assertEquals;

public class LC660 {


    public int newInteger(long n) {

        long count =8;
        int digit=1;
        while(n>count){
            n-=count;
            digit++;
            count*=9;
        }

        n--; //0based
        long cur = (int)Math.pow(10, digit-1);
        long multiple=1;
        while(n>0){
            long v = n%9;
            if(v>0) cur+=multiple*v;
            n/=9;
            multiple*=10;
        }
        return (int)cur;

    }

    @org.junit.Test
    public void test(){
        assertEquals(10, newInteger(800000000));
        assertEquals(1, newInteger(1));
        assertEquals(10, newInteger(9));
    }
}
