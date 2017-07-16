package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ZeroFlip {

    public int zeroflip(int[] nums, int m) {
        int max = Integer.MIN_VALUE;
        int N = nums.length;

        int lo=0;
        int hi=0;
        for (int i=0;i<N ;i++){
            if(nums[i]==0 ){
                if(m==0) break;
                m--;
            }
            hi=i;
        }

        while(true){
            int len = hi-lo+1;
            System.out.println("len="+len +" "+lo+","+hi);
            if(len>max){
                max = len;
            }

            if(hi==N-1) break;

            for (int i = lo; i < N; i++) {
                if(nums[i]==0){
                    m++;
                    lo=i+1;
                    break;
                }
            }

            hi++;
            for ( ; hi < N; hi++) {
                if(nums[hi]==0){
                    if(m>0) m--;
                    else{
                        hi--;
                        break;
                    }
                }
            }
            if(hi>=N) hi = N-1;
        }

        return max;
    }

    @Test
    public void test(){
        assertEquals(8, (zeroflip(new int[]{1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1}, 2)));
    }
}
