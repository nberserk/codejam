package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 * https://leetcode.com/problems/coin-change-2/
 *
 */
public class LC518 {
    int _change(int amount, int[] coins, int[][] cache, int start) {
        if(amount==0) return 1;
        if(amount<0) return 0;
        if(cache[amount][start]!=-1) return cache[amount][start];

        int c=0;
        for(int i=start;i<coins.length;i++){
            c+= _change(amount-coins[i], coins, cache, i);
        }
        //System.out.println(amount+":"+c+","+start);
        cache[amount][start]=c;
        return c;
    }
    public int change(int amount, int[] coins) {
        if(coins.length==0){
            if(amount==0) return 1;
            return 0;
        }
        int[][] dp = new int[amount+1][coins.length];
        for(int i=0;i<amount+1;i++)
            Arrays.fill(dp[i], -1);

        return _change(amount, coins, dp, 0);
    }

    @Test
    public void test(){
        assertEquals(4, change(5, new int[]{1, 2, 5}));
        assertEquals(0, change(3, new int[]{2}));
        assertEquals(1, change(10, new int[]{10}));

    }
}
