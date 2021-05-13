package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 *
 * Given a contiguous sequence of numbers in which each number repeats thrice, there is exactly one missing number. Find the missing number.

 eg: 11122333 : Missing number 2
 11122233344455666: Missing number 5

 */
public class FindMissingNumber {

    int findMissingNumber(int[] n){
        int N = n.length;

        int lo=0;
        int hi=N/3;
        while(lo<hi){
            int m = (lo+hi)/2;

            if(m*3+2>=N )
                return n[m*3];
            else if (n[m*3]==n[m*3+1] && n[m*3]==n[m*3+2])
                lo=m+1;
            else hi=m;

        }
        return n[lo*3];
    }


    @Test
    public void test(){
        assertEquals(2, findMissingNumber(new int[]{1,1,1,2,2,3,3,3}));
        assertEquals(3, findMissingNumber(new int[]{1,1,1,2,2,2,3,3}));
        assertEquals(4, findMissingNumber(new int[]{1,1,1,2,2,2,3,3,3,4,4}));
        assertEquals(5, findMissingNumber(new int[]{1,1,1,2,2,2,3,3,3,4,4,4,5,5,6,6,6}));

    }
}
