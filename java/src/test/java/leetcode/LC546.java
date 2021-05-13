package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 16/02/2017.
 */
public class LC546 {


    // wonderful explnation
    // https://discuss.leetcode.com/topic/84687/java-top-down-and-bottom-up-dp-solutions

    // T(i,j,k) = T(i+1, m-1,0) + T(m, j, k+1) where i+1 <= m <= j

    int sub(int[] boxes, int start, int end,int k, int[][][] dp){
        int N = boxes.length;
        if(end<start) return 0;
        else if(start==end)
            return (k+1)*(k+1);
        if(dp[start][end][k]!=0)
            return dp[start][end][k];

        int ret = (k+1)*(k+1) + sub(boxes, start+1, end, 0, dp);
        for (int i = start+1; i <= end; i++) {
            if(boxes[start] == boxes[i]){
                ret = Math.max(ret, sub(boxes, start+1,i-1,0,dp) + sub(boxes, i, end, k+1, dp) );
            }
        }

        dp[start][end][k] = ret;
        System.out.println(String.format("%d,%d,%d=%d",start,end,k, ret));
        return ret;
    }

    public int removeBoxes(int[] boxes) {
        int N = boxes.length;
        if(N==0) return 0;

        int[][][] T = new int[N][N][N];

//        for (int i = 0; i < N; i++) {
//            for (int k = 0; k < N; k++) {
//                T[i][i][k]= (k+1)*(k+1);
//            }
//        }
//
//        for (int l=1;l<N;l++){
//            for (int j = ; j < N; j++) {
//
//            }
//        }

        return sub(boxes, 0, N-1, 0, T);
    }

    @Test
    public void test(){
        assertEquals(23, removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));
    }
}
