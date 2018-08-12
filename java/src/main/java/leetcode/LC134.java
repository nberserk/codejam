package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 *
 *
 */
public class LC134 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int N=gas.length;
        int sum=0;
        boolean plus=false;
        int max = Integer.MIN_VALUE;
        int ret = 0;
        for(int i=gas.length-1;i>=0;i--){
            gas[i]-=cost[i];
            sum+=gas[i];
            if(sum>max){
                max=sum;
                ret=i;
            }
        }
        if(sum<0) return -1;

        return ret;
    }

    @Test
    public void test(){

        assertEquals(3, canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
        assertEquals(-1, canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));



    }
}
