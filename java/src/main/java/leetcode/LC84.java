package leetcode;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 12. 29..
 *
 * from: https://leetcode.com/problems/largest-rectangle-in-histogram/
 * 
 */
public class LargestRectangleHistogram_84 {

    int largestRectangleArea(int[] heights) {
        int max = Integer.MIN_VALUE;
        int N = heights.length;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<=N;i++){ // interate until N,
            int h = i==N?0:heights[i];
            if(stack.isEmpty() || h >= heights[stack.peek()])
                stack.push(i);
            else{
                h=heights[stack.pop()];
                int width = stack.isEmpty() ? i : i-stack.peek()-1;
                max=Math.max(max, h*width);
                i--;
            }
        }
        return max;
    }


    @Test
    public void test() {

        assertEquals(10, largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));

    }


}
