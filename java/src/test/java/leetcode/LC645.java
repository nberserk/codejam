package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class LC645 {

    public int[] findErrorNums(int[] nums) {
        int N = nums.length;

        boolean[] set = new boolean[N+1];
        int[] r = new int[2];
        for (int i:nums){
            if (set[i]){
                r[0] = i;
            }
            set[i]=true;
        }

        for (int i = 1; i <= N; i++) {
            if(!set[i]){
                r[1]=i;
                break;
            }
        }

        return r;
    }


    @Test
    public void test(){
        assertEquals("[2, 3]", Arrays.toString((findErrorNums(new int[]{1, 2, 2, 4}))));
    }
}
