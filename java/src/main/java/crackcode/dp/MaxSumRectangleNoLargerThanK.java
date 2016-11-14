package main.java.crackcode.dp;

import org.junit.Test;

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/14/16.
 *
 * https://leetcode.com/problems/max-sum-of-sub-matrix-no-larger-than-k/
 *
 * 
 */
public class MaxSumRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] m, int k) {
        int row = m.length;
        if(row==0) return 0;
        int col =m[0].length;

        int ret = Integer.MIN_VALUE;
        for(int l=0;l<col;l++){
            int[] rv = new int[row]; //row value
            for(int r=l;r<col;r++){
                for(int i=0;i<row;i++) rv[i]+=m[i][r];

                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int sum=0;
                for(int i=0;i<row;i++){
                    sum+=rv[i];
                    Integer candi = set.ceiling(sum-k);
                    set.add(sum);
                    if(candi==null) continue;
                    int test = sum-candi;
                    ret=Math.max(ret, test);
                    if(ret==k) return k;
                }

            }
        }

        return ret;
    }

    @Test
    public void test(){
        int[][] m = {{1,  0, 1},
                {0, -2, 3}};
        assertEquals(2, maxSumSubmatrix(m, 2));
    }
}
