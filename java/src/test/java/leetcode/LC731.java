package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

// tree,
public class LC731 {

    TreeMap<Integer, Integer> map = new TreeMap<>();
    public boolean book_tle_why(int start, int end) {

        map.putIfAbsent(start, 0);
        map.putIfAbsent(end, 0);
        map.put(start, map.get(start)+1);
        map.put(end, map.get(end)-1);


        int cross=0;
        for(int cur: map.keySet()){
            if(cur>end) break;
            cross+=map.get(cur);
            if(cross>=3) {
                map.put(start, map.get(start)-1);
                map.put(end, map.get(end)+1);
                return false;
            }
        }

        return true;
    }

    int[] overlapRange(int[] a, int[] b){
        int left = Math.max(a[0], b[0]);
        int right = Math.min(a[1], b[1]);
        return new int[]{left, right};
    }

    ArrayList<int[]> points = new ArrayList<>();
    public boolean book(int start, int end) {

        int[] cur = new int[]{start,end};
        ArrayList<int[]> overlap = new ArrayList<>();
        for (int[] range: points){
            int[] o = overlapRange(range, cur);
            if(o[0]<o[1]){

                for (int[] or: overlap) {
                    int[] o2 = overlapRange(o, or);
                    if(o2[0]<o2[1]) return false;
                }
                overlap.add(o);
            }
        }

        points.add(cur);
        return true;
    }

    @org.junit.Test
    public void test(){

        LC731 cal = new LC731();
        assertEquals(true, cal.book(10, 20)); // returns true
        assertEquals(true, cal.book(50, 60)); // returns true
        assertEquals(true, cal.book(10, 40)); // returns true
        assertEquals(false, cal.book(5, 15)); // returns false
        assertEquals(true, cal.book(5, 10)); // returns true
        assertEquals(true, cal.book(25, 55)); //

        //assertEquals("bcde", );
    }

    @Test
    public void test2(){

        LC731 cal = new LC731();
        assertEquals(true, cal.book(24, 40));
        assertEquals(true, cal.book(43, 50));
        assertEquals(true, cal.book(27, 43));
        assertEquals(true, cal.book(5, 21));
        assertEquals(false, cal.book(30, 40));
        assertEquals(false, cal.book(14, 29));
        assertEquals(true, cal.book(3, 19));
        assertEquals(false, cal.book(3, 14));
        assertEquals(false, cal.book(25, 39));
        assertEquals(false, cal.book(6, 19));
    }

}
