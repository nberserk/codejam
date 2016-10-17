package crackcode.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 10. 12..
 *
 * https://leetcode.com/problems/wiggle-sort-ii/
 *
 */
public class WiggleSort2 {
    public void wiggleSort(int[] nums) {
        int N = nums.length;
        boolean even = N%2==0 ? true: false;
        double median;
        if(even) {
            median = QuickSelect.quickselect(nums, nums.length / 2 -1);
            median += QuickSelect.quickselect(nums, nums.length / 2 );
            median/=2.0;
        }else
            median = QuickSelect.quickselect(nums, nums.length / 2);

        Stack<Integer> low = new Stack<>();
        Stack<Integer> high = new Stack<>();
        for (int i = 0; i < N; i++) {
            if ((double)nums[i]<median)
                low.push(nums[i]);
            else if ((double)nums[i]>median )
                high.push(nums[i]);
        }

        int i=0;
        while(i<N){
            if(low.size()>0)
                nums[i] = low.pop();
            else nums[i] = (int)median;
            i+=2;
        }
        i= N-1;
        if(!even) i--;
        while(i>0){
            if(high.size()>0)
                nums[i] = high.pop();
            else nums[i] = (int)median;
            i-=2;
        }
        System.out.println(Arrays.toString(nums));
    }

    boolean isWiggle(int[] a){
        for (int i = 0; i < a.length-1; i++) {
            if((i&1)==0){
                if (a[i]>=a[i+1])
                    return false;
            }else{
                if (a[i]<=a[i+1])
                    return false;
            }
        }
        return true;
    }

    @Test
    public void test(){
        int[] a = {1, 5, 1, 1, 6, 4};

        wiggleSort(a);
        assertEquals(true, isWiggle(a));

        int[] a2={1, 3, 2, 2, 3, 1};
        wiggleSort(a2);
        assertEquals(true, isWiggle(a2));
    }
}
