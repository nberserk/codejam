package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 *
 *
 */
public class LC487 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int zero=0;
        int l=0;
        int ret=-1;
        for (int h = 0; h < nums.length; h++) {
            if(nums[h]==0)
                zero++;
            while(zero>1){
                if(nums[l++]==0)
                    zero--;
            }
            ret=Math.max(ret, h-l+1);
        }

        return ret;
    }
    public int findMaxConsecutiveOnes_1st(int[] nums) {
        int N = nums.length;
        int ret=-1;
        int s=-1;
        int e=0;
        while(e<N){
            if(nums[e]==0)break;
            e++;
        }
        if(e==N) return N;
        e++;
        while(e<N){
            if(nums[e]==0)break;
            e++;
        }
        if(e==N) return N;
        while(true){
            ret=Math.max(ret, e-s-1);
            s++;
            while(s<N){
                if(nums[s]==0)break;
                s++;
            }
            e++;
            while(e<N){
                if(nums[e]==0)break;
                e++;
            }
            if(e==N){
                ret=Math.max(ret, N-s-1);
                break;
            }
        }
        return ret;
    }

    @Test
    public void test(){
        assertEquals(4, findMaxConsecutiveOnes(new int[]{1,0,1,1,0}));
    }
}
