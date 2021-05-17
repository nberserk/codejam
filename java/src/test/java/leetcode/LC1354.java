package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 */
public class LC1354 {
    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(target.length, (a, b)-> -a+b);
        int sum=0;//17
        for(int i: target){
            queue.add(i);
            sum+=i;
        }
        while(queue.peek()!=1) {
            int max = queue.poll(); // 9,3,5 -> 9 -> 5 -> 3
            sum -=max;
            if(sum>=max || sum < 1) return false;
            int next = max%sum;

            sum += next;
            if(next==0) next=sum;

            queue.add(next);         // 5,3,1  -> 3,1,1 -> 1,1,1
        }
        return true;

    }

    @Test
    public void test(){

        Assert.assertEquals(true, isPossible(new int[]{9,3,5}));
        Assert.assertEquals(true, isPossible(new int[]{8,5}));
        Assert.assertEquals(false, isPossible(new int[]{1,1,1,2}));
    }
}
