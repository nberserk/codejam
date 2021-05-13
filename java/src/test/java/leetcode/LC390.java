package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LC390 {

    public int lastRemaining(int n) {
        int start = 1;
        int end=n;
        boolean fromStart=true;
        int multiply = 1;
        while(start<end){
            int nth = 2+(end-start-1)/multiply;//9,4
            if(fromStart){
                start = start+multiply; // 2
                if(nth%2==1)
                    end = end-multiply;  //8
            }else{
                end = end-multiply;//6
                if(nth%2==1)
                    start = start+multiply;//2
            }

            fromStart=!fromStart;
            multiply*=2;//4
        }
        return start;
    }

    @Test
    public void test(){
        assertEquals(6, lastRemaining(9));
        assertEquals(54, lastRemaining(100));
    }
}
