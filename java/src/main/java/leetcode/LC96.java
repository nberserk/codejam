package main.java.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/6/17.
 *
 * https://leetcode.com/problems/unique-binary-search-trees/?tab=Description
 *
 * idea:
 * it's BST. so smaller go to left, larger go to right,
 * for each root from 1th .. nth, find numTree(left), numTree(right)
 * C(5) = C(4) + C(3) + C(4)
 * C(n+1) = C(1)*C(n) + C(1)*C(n-1) + C(2)*C(n-2) ... C(n)*C(1)
 */
public class UniqueBinarySearchTrees_96 {

    public int numTrees(int n) {
        if(n<=1) return 1;
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        dp[2]=2;


        for(int i=3;i<=n;i++){
            int c=0;
            for(int j=0;j<i;j++){
                int lc = Math.max(j, 0); // left count
                int rc = i-j-1;
                c+= dp[lc] * dp[rc];
            }
            dp[i]=c;
        }

        return dp[n];
    }

    @Test
    public void test(){
        assertEquals(5, numTrees(3));
    }

}
