package leetcode;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 12/4/16.
 *
 * https://leetcode.com/problems/maximal-square/
 *
 *
 */
public class LC85 {

    public int maximalSquare(char[][] matrix) {
        int row=matrix.length;
        if(row==0) return 0;
        int col = matrix[0].length;

        int r = 0;

        int[][] m = new int[row][col];
        for (int i = row-1; i >=0; i--) {
            for (int j = 0; j < col; j++) {
                int prev= i==row-1 ? 0 : m[i+1][j];
                if (matrix[i][j]=='1')
                    m[i][j] = prev +1;
                else
                    m[i][j] = 0;
            }
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < row; i++) {
            stack.clear();
            for (int j = 0; j <= col; j++) {
                int h = (j==col) ? 0: m[i][j];
                if(stack.isEmpty() || h >= m[i][stack.peek()])
                    stack.push(j);
                else{
                    int height = m[i][stack.pop()];
                    int width = stack.isEmpty() ? j: j-stack.peek()-1;
                    int area = width*height;
                    r = Math.max(r, area);
                    j--;
                }
            }
        }

        return r;
    }

    @Test
    public void test(){

        char[][] m = {
                "10100".toCharArray(),
                "10111".toCharArray(),
                "11111".toCharArray(),
                "10010".toCharArray()};
        assertEquals(6, maximalSquare(m) );

    }
}
