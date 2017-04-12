package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 16/02/2017.
 */
public class RemoveBoxes_546 {


    int find(int[]boxes, int start, int end, int[][] dp){
        int N = boxes.length;
        if(end<start) return 0;
        else if(start==end)
            return 1;
        if(dp[start][end]!=0)
            return dp[start][end];

        int ret =0;
        for (int i = start; i <= end; i++) {
            int e = i+1;// exclusive
            while(e<=end && boxes[e]== boxes[i]) e++;
            int len = e-i;
            ret = Math.max(ret, len*len + find(boxes, start,i-1,dp) + find(boxes, e, end, dp) );

            i=e;
        }

        dp[start][end] =ret;
        System.out.println(String.format("%d,%d=%d",start,end,ret));
        return ret;
    }


    public int removeBoxes(int[] boxes) {

        int N = boxes.length;
        if(N==0) return 0;


        int[][] dp = new int[N][N];

        return find(boxes, 0, N-1, dp);

    }



    @Test
    public void test(){
        assertEquals(23, removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));
//        assertEquals(987, largestPalindrome(2));
//        assertEquals(475, largestPalindrome(8));
    }
}
