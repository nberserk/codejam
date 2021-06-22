package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class LP123 {
    // find max from the start to i -> A
    // find max from the i to end  -> B
    // find max(A(i) + B(i+1))
    public int maxProfit(int[] prices) {
        int N = prices.length;
        if(N<2) return 0;

        int maxProfit = 0;
        int[] fromStart = new int[N]; // 0,0,2,0,0,3,1,4
        int[] toEnd = new int[N];     // 2,2,0,4,4,1,3,0
        int min=prices[0];
        for(int i=1;i<N;i++){
            int cur = prices[i] - min;
            fromStart[i] = Math.max(cur, fromStart[i-1]);
            maxProfit = Math.max(maxProfit, cur);
            min=Math.min(min, prices[i]);
        }
        int max = prices[N-1];
        for(int i=N-2;i>=0;i--){
            int profit = max - prices[i];
            toEnd[i] = Math.max(profit, toEnd[i+1]);
            max=Math.max(max, prices[i]);
        }


        for(int i=0;i<N-1;i++){
            int profit = fromStart[i] + toEnd[i+1];
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }

    @Test
    public void test() {
        Assert.assertEquals(6, maxProfit(new int[]{3,3,5,0,0,3,1,4}));
        Assert.assertEquals(4, maxProfit(new int[]{1,2,3,4,5}));
        Assert.assertEquals(7, maxProfit(new int[]{3,2,6,5,0,3}));
        Assert.assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
        Assert.assertEquals(0, maxProfit(new int[]{1}));
    }
}
