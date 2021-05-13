package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LC307 {

    int[] tree;
    int[] n;
    int N;

    public void init(int[] nums) {
        N = nums.length+1;
        tree = new int[N];
        n = new int[N-1];
        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
        //    n[i] = nums[i];
        }
    }

    public void update(int i, int val) {
        int delta = val- n[i];
        n[i]=val;
        i++;

        while(i<N){
            tree[i] += delta;
            i+= i&(-i);
        }
    }

    int sum(int i){
        i++;
        int sum =0;
        while(i>0){
            sum += tree[i];
            i-=i & (-i);
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        return sum(j) - sum(i-1);
    }


    @Test
    public void test(){
        LC307 r = new LC307();
        r.init(new int[]{1,3,5});

        assertEquals(9, r.sumRange(0, 2));
    }
}
