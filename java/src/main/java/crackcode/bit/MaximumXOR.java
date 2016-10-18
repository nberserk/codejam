package main.java.crackcode.bit;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/17/16.
 *
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class MaximumXOR {


    public int findMaximumXOR(int[] nums) {
        int N = nums.length;
        int mask=0;
        int max=0;
        for(int i=30;i>=0;i--){
            mask |= (1<<i);
            Set<Integer> set = new HashSet<>(); // prefix
            for(int n:nums){
                set.add(n&mask);
            }

            // now find out if there are two prefix with different i-th bit
            // if there is, the new max should be current max with one 1 bit at i-th position, which is candidate
            // and the two prefix, say A and B, satisfies:
            // A ^ B = candidate
            // so we also have A ^ candidate = B or B ^ candidate = A
            // thus we can use this method to find out if such A and B exists in the set
            int temp = max | (1<<i);
            for(int prefix: set){
                if(set.contains(prefix^temp) ){
                    max=temp;
                    break;
                }
            }
        }
        return max;
    }

    @Test
    public void test(){
        int[] a = {3, 10, 5, 25, 2, 8};
        assertEquals(28,findMaximumXOR(a));
    }
}
