package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class RotateArray_189 {

    public int[] rotate(int[] nums, int k) {
        int N = nums.length;
        k =  k%N;
        int start, next, nextVal;
        int count=0;

        for (int i = 0; count < N; i++) {
            start = i;
            nextVal = nums[i];
            do{
                next = (start+k)%N;
                int temp = nums[next];
                nums[next] = nextVal;
                start=next;
                nextVal=temp;
                count++;
            }while (i!=start);
        }
        return nums;
    }

    @Test
    public void test(){
        assertEquals("[2, 3, 4, 1]", Arrays.toString(rotate(new int[]{1,2,3,4}, 3)));
        assertEquals("[3, 4, 1, 2]", Arrays.toString(rotate(new int[]{1, 2, 3, 4}, 2)));
    }
}
