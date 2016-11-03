package crackcode.tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 11. 1..
 *
 * https://leetcode.com/problems/range-sum-query-2d-mutable/
 * 
 *
 Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

 Range Sum Query 2D
 The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

 Example:
 Given matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]

 sumRegion(2, 1, 4, 3) -> 8
 update(3, 2, 2)
 sumRegion(2, 1, 4, 3) -> 10
 Note:
 The matrix is only modifiable by the update function.
 You may assume the number of calls to update and sumRegion function is distributed evenly.
 You may assume that row1 ≤ row2 and col1 ≤ col2.

 *
 * last 1 bit 만큼 뒤로 합을 가지고 있음.
 * when update
 * 7:  111 -> 7
 *    1000 -> 8
 * when sum:
 * 3 : 11 -> 3
 *     10 -> 2
 */
public class RangeSumQuery2D {
    int[][] m;
    int[][] tree;
    int row, col, size;

    public void init(int[][] matrix) {
        row = matrix.length;
        if (row > 0)
            col = matrix[0].length;
        size = col + 1;
        m = new int[row][col];
        tree = new int[row + 1][col + 1];
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                update(y, x, matrix[y][x]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int delta = val - m[row][col];
        int i = col+1;
        while(i<size){
            tree[row][i] += delta;
            i+= i& (-i);
        }
        m[row][col] = val;
    }

    public int sum (int row, int col){
        int sum=0;
        int i= col+1;
        while(i>0){
            sum += tree[row][i];
            i-=(i&(-i));
        }
        return sum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int s = 0;
        for (int y = row1; y <= row2; y++) {
            s+= sum(y, col2);
            s-= sum(y,col1-1);
        }
        return s;
    }


    @Test
    public void test(){
        int[][] m = {{3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        RangeSumQuery2D rsq = new RangeSumQuery2D();
        rsq.init(m);
        assertEquals(8, rsq.sumRegion(2, 1, 4, 3));
        rsq.update(3,2,2);
        assertEquals(10, rsq.sumRegion(2, 1, 4, 3));
    }
}
