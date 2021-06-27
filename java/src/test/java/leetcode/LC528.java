package leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 16/02/2017.
 */
public class LC528 {

    int[] sorted;
    int sum=0;

    // create sortedArry to be used for binary search
    // sorted[i] = weightedSum from the start
    // sorted[0] = 0, sorted[1]= 1
    public void Solution(int[] w) {
        int length = w.length;

        sum=0;
        sorted = new int[length];
        for(int i=0;i<length;i++){
            sorted[i]=sum;
            sum+=w[i];
        }
    }

    int find(int target){
        int left = 0;
        int right = sorted.length-1;
        while(left<right){
            int mid = (left+right+1)/2;
            if(sorted[mid]==target)
                return mid;
            else if(sorted[mid]>target)
                right = mid-1;
            else
                left=mid;
        }
        return left;
    }

    public int pickIndex() {
        int target = (int)(Math.random()*sum);
        int index = find(target);
        return index;
    }


    @Test
    public void test(){
        LC528 lc = new LC528();
        lc.Solution(new int[]{1});
        Assert.assertEquals(0, lc.pickIndex());
        Assert.assertEquals(0, lc.pickIndex());

        lc.Solution(new int[]{1,3});

        System.out.println(lc.pickIndex());
        System.out.println(lc.pickIndex());
        System.out.println(lc.pickIndex());
        System.out.println(lc.pickIndex());
        System.out.println(lc.pickIndex());
        System.out.println(lc.pickIndex());
        System.out.println(lc.pickIndex());
        System.out.println(lc.pickIndex());
    }
}
