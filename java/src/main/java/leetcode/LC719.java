package leetcode;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FindKthSmallestPairDistance_719 {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int N =nums.length;

        int lo=0;
        int hi=nums[N-1]-nums[0];
        while(lo<hi){
            int m = (lo+hi)/2;
            int c = countSmallerPair(nums, m);
            if(c==k){
                hi=m;
            }else if(c>k)
                hi=m;
            else
                lo=m+1;
        }

        return lo;
    }


    int upperBound(int[] n, int target, int start){
        int lo=start;
        int hi=n.length-1;

        while(lo<hi){
            int m = (lo+hi+1)/2;
            if(n[m]<=target)
                lo=m;
            else
                hi=m-1;
        }
        if(n[lo]<=target) return lo;
        return -1;
    }

    private int countSmallerPair(int[] nums, int target) {
        int c = 0;
        for (int i = 0; i < nums.length-1; i++) {
            int dest = target+nums[i];
            int pos = upperBound(nums, dest, i + 1);

            if(pos==-1 || pos<=i)
                continue;
            c += pos-i;
        }
        return c;
    }

    @org.junit.Test
    public void test(){
        assertEquals(2, smallestDistancePair(new int[]{9,10,7,10,6,1,5,4,9,8}, 18));
        assertEquals(3, smallestDistancePair(new int[]{1,2,4,9, 13}, 3));
        assertEquals(0, smallestDistancePair(new int[]{1,3,1}, 1));
    }
}
