package main.java.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 3/19/17.
 */
public class MinimumTimeDifference_539 {
    int diff(String first, String second ){
        String[] f = first.split(":");
        String[] s = second.split(":");
        int h = Integer.valueOf(s[0])-Integer.valueOf(f[0] );
        int m = Integer.valueOf(s[1])-Integer.valueOf(f[1] );

        int min = h*60 + m;
        //int cur = Math.abs(min - 60*24);
        //min = Math.min(min, cur);
        return min;
    }
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int N = timePoints.size();
        int min = Integer.MAX_VALUE;
        int cur;
        for(int i=0;i<N-1;i++){
            cur = diff(timePoints.get(i), timePoints.get(i+1) );
            min= Math.min(min, cur);
        }

        cur = diff(timePoints.get(N-1), timePoints.get(0) );
        if(cur<0) cur +=24*60;
        min=Math.min(min,cur);

        return min;
    }

    @Test
    public void test(){
        ArrayList<String> in = new ArrayList<>();
        in.add("23:59");
        in.add("00:00");
        assertEquals(1, findMinDifference(in));

    }


}
