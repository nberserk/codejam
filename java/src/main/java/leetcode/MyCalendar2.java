package leetcode;

import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class MyCalendar2 {

    TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();

    public boolean book(int start, int end) {
        LinkedList<int[]> pt =new LinkedList<>();
        if(map.containsKey(start)){
            TreeSet<Integer> ends = map.get(start);
            if(ends.size()>1) return false;

            Integer low = map.lowerKey(start);
            if(low!=null){
                pt.add(new int[]{low, map.get(low).last()});
            }
            pt.add(new int[]{start, ends.first()});

            Integer high = map.higherKey(start);
            if(high!=null){
                pt.add(new int[]{high, map.get(high).first()});
            }
        }else{

            Integer low = map.lowerKey(start);
            if(low!=null){
                for(int t: map.get(low)){
                    pt.add(new int[]{low, t});
                }

                if(pt.size()<2){
                    low = map.lowerKey(low);
                    if(low!=null){
                        pt.addFirst(new int[]{low, map.get(low).last()});
                    }
                }
            }

            Integer high = map.higherKey(start);
            if(high!=null){
                for(int t: map.get(high)){
                    pt.add(new int[]{high, t});
                }

                int size = map.get(high).size();
                if(size<2){
                    high = map.higherKey(high);
                    if(high!=null){
                        pt.add(new int[]{high, map.get(high).first() });
                    }
                }
            }
        }


        for (int i = 0; i < pt.size() - 1; i++) {
            int left  = Math.max( start, Math.max(pt.get(i)[0], pt.get(i+1)[0]));
            int right = Math.min(end, Math.min(pt.get(i)[1], pt.get(i+1)[1]));
            if(left<right) return false;
        }

        map.putIfAbsent(start, new TreeSet<>());
        map.get(start).add(end);
        return true;
    }

    @org.junit.Test
    public void test(){

        MyCalendar2 cal = new MyCalendar2();
        assertEquals(true, cal.book(10, 20)); // returns true
        assertEquals(true, cal.book(50, 60)); // returns true
        assertEquals(true, cal.book(10, 40)); // returns true
        assertEquals(false, cal.book(5, 15)); // returns false
        assertEquals(true, cal.book(5, 10)); // returns true
        assertEquals(true, cal.book(25, 55)); //

        //assertEquals("bcde", );
    }
}
