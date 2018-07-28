package leetcode;

import leetcode.common.Interval;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeeFreeTime_759 {


    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        ArrayList<Interval> all = new ArrayList<>();
        for (List<Interval> l : schedule){
            all.addAll(l);
        }

        List<Interval> merge = mergeInterval(all);

        List<Interval> ret = new ArrayList<>();
        for (int i = 0; i < merge.size() - 1; i++) {
            ret.add(new Interval(merge.get(i).end, merge.get(i+1).start));
        }
        return ret;
    }

    private List<Interval> mergeInterval(ArrayList<Interval> merged) {
        Collections.sort(merged, (a,b)-> a.start==b.start?a.end-b.end:a.start-b.start );

        List<Interval> ret = new ArrayList<>();
        Interval prev = merged.get(0);
        for (int i = 1; i < merged.size(); i++) {
            Interval cur = merged.get(i);
            if (prev.end >= cur.start){
                prev.end = Math.max(prev.end, cur.end);
            }else if(prev.end < cur.start){
                ret.add(prev);
                prev = cur;
            }else{
                assert false;
            }
        }
        ret.add(prev);
        return ret;
    }


    @org.junit.Test
    public void test(){

        ArrayList<Interval> a = new ArrayList<>();
        a.add(new Interval(1,2));
        a.add(new Interval(5,6));
        a.add(new Interval(1,3));

        assertEquals("[[1,3], [5,6]]", mergeInterval(a).toString());

        ArrayList<Interval> b = new ArrayList<>();
        b.add(new Interval(1,2));
        b.add(new Interval(5,6));
        b.add(new Interval(1,5));
        assertEquals("[[1,6]]", mergeInterval(b).toString());

//        assertEquals("steps", shortestCompletingWord("1s3 PSt",new String[]{"step", "steps", "stripe", "stepple"} ));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }

    @Test
    public void example1(){
        ArrayList<Interval> a = new ArrayList<>();
        a.add(new Interval(1,2));
        a.add(new Interval(5,6));
        a.add(new Interval(1,3));
        a.add(new Interval(4,10));

        assertEquals("[[1,3], [4,10]]", mergeInterval(a).toString());
        List<List<Interval>> t = new ArrayList<>();
        t.add(a);
        assertEquals("[[3,4]]", employeeFreeTime(t).toString());
    }

    @Test
    public void example2(){
        ArrayList<Interval> a = new ArrayList<>();
        a.add(new Interval(1,3));
        a.add(new Interval(6,7));
        a.add(new Interval(2,4));
        a.add(new Interval(2,5));
        a.add(new Interval(9,12));

        assertEquals("[[1,5], [6,7], [9,12]]", mergeInterval(a).toString());
        List<List<Interval>> t = new ArrayList<>();
        t.add(a);
        assertEquals("[[5,6], [7,9]]", employeeFreeTime(t).toString());
    }
}
