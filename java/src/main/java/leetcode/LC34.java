package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class SearchForRange_34 {
    public int[] searchRange(int[] nums, int target) {
        int[] r = new int[]{-1,-1};
        if(nums.length==0) return r;

        int start = bs_start(nums, target);
        if(nums[start]!=target) return r;
        int end = bs_end(nums, target);

        r[0] = start;
        r[1] =end;
        return r;
    }

    int bs_end(int[] n, int t){
        int lo = 0; //0
        int hi = n.length-1; // 5
        while(lo<hi){
            int mid = (lo+hi+1)/2; // 4
            if (n[mid]<t){ // 8<=8
                lo=mid+1; // lo=3
            }else if(n[mid]>t)
                hi=mid-1;
            else lo=mid;
        }
        return lo;
    }

    int bs_start(int[] n, int t){
        int lo = 0;
        int hi = n.length-1;
        while(lo<hi){
            int mid = (lo+hi)/2;
            if (n[mid]<t){
                lo=mid+1;
            }else if (n[mid]>t)
                hi =mid-1;
            else
                hi=mid;

        }
        return lo;
    }


    @Test
    public void test(){

        assertEquals("[3, 4]", Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }
}
