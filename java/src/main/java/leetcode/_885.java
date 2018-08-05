package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class _885 {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int ret=0;
        int lo=0;
        int hi=people.length-1;
        while(lo<hi){
            if(people[hi]+people[lo]<=limit){
                ret++;
                hi--; lo++;
            }else{
                ret++;
                hi--;
            }
        }
        if(lo==hi){
            ret++;
        }

        return ret;
    }

    @Test
    public void test(){
        assertEquals(4, numRescueBoats(new int[]{3,5,3,4}, 5));
    }
}
