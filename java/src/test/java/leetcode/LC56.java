package leetcode;

import leetcode.common.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LC56 {

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, (me, o) ->me.start==o.start?me.end-o.end:me.start - o.start);

        List<Interval> result = new ArrayList<>();
        if(intervals.size()==0) return result;
        Interval prev=intervals.get(0);
        for(int i=1;i<intervals.size();i++){
            if(intervals.get(i).start>prev.end){
                result.add(prev);
                prev=intervals.get(i);
            }else{
                prev.end=Math.max(prev.end, intervals.get(i).end);
            }
        }
        if(prev!=null)
            result.add(prev);

        return result;
    }

    @org.junit.Test
    public void test() {
        List<Interval> in = new ArrayList<>();
        in.add(new Interval(1,3));
        in.add(new Interval(2,6));
        in.add(new Interval(8,10));
        in.add(new Interval(15,18));
        List<Interval> merged = merge(in);
        assertEquals(3, merged.size());
        assertEquals("[1,6]", merged.get(0).toString());
        assertEquals("[8,10]", merged.get(1).toString());
        assertEquals("[15,18]", merged.get(2).toString());
    }
}
