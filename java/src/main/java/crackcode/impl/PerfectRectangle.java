package main.java.crackcode.impl;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/27/16.
 *
 * https://leetcode.com/problems/perfect-rectangle/
 *
 *
 */
public class PerfectRectangle {

    public boolean isRectangleCover(int[][] rectangles) {
        HashSet<String> point = new HashSet<>();

        int[][] d = {{0,1}, {2,1}, {2,3},{0,3}};
        int left, right, top, bottom;
        left = bottom = Integer.MAX_VALUE;
        right = top = Integer.MIN_VALUE;
        int sum=0;
        for(int[] r: rectangles){
            for(int i=0;i<d.length;i++){
                String p = r[d[i][0]] +" "+ r[d[i][1]];
                if(point.contains(p))
                    point.remove(p);
                else point.add(p);
            }
            sum += (r[2]-r[0])*(r[3]-r[1]);
            left=Math.min(left, r[0]);
            bottom=Math.min(bottom, r[1]);
            right=Math.max(right, r[2]);
            top=Math.max(top, r[3]);
        }

        if(point.size()>4) return false;
        String p = String.format("%d %d", left, bottom);
        if(p.contains(p))
            point.remove(p);
        else return false;

        p = String.format("%d %d", right, bottom);
        if(p.contains(p))
            point.remove(p);
        else return false;

        p = String.format("%d %d", right, top);
        if(p.contains(p))
            point.remove(p);
        else return false;

        p = String.format("%d %d", left, top);
        if(p.contains(p))
            point.remove(p);
        else return false;


        if (sum == (right-left)*(top-bottom))
            return true;
        return false;
    }

    @Test
    public void test(){
        int[][] r = {
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {3, 2, 4, 4},
                {1, 3, 2, 4},
                {2, 3, 3, 4}
        };

        assertEquals(true, isRectangleCover(r));

        int[][] r2 = {
            {1,1,3,3},
            {3,1,4,2},
            {1,3,2,4},
            {3,2,4,4}
        };
        assertEquals(false, isRectangleCover(r2));

        int[][] r3 = {
            {1,1,3,3},
            {3,1,4,2},
            {1,3,2,4},
            {2,2,4,4}
        };
        assertEquals(false, isRectangleCover(r3));
    }

}
