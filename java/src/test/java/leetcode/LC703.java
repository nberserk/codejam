package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

public class LC703 {

    PriorityQueue<Integer> pq;
    int k;
    public void init(int k, int[] nums) {
        this.k=k;
        pq = new PriorityQueue<Integer>();
        for(int n:nums){
            _add(n);
        }
    }

    private void _add(int n){
        if(pq.size()>=k && pq.peek()>=n)
            return;
        if(pq.size()==k)
            pq.poll();
        pq.add(n);
    }

    public int add(int val) {
        _add(val);
        return pq.peek();
    }


    @Test
    public void test(){
        LC703 l = new LC703();
        l.init(3, new int[]{4,5,8,2});
        Assert.assertEquals(4, l.add(3));
        Assert.assertEquals(5, l.add(5));

    }
}
