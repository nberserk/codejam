package leetcode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeeFreeTime_759 {
    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> merge = schedule.get(0);
        for(int i=1;i<schedule.size();i++){
            merge = mergeInterval(merge, schedule.get(i));
        }


        return merge;
    }

    private List<Interval> mergeInterval(List<Interval> left, List<Interval> right) {
        int L=left.size();
        int R=right.size();
        int l=0;
        int r=0;
        List<Interval> ret = new ArrayList<>();
        while(l<L || r<R){
            if(r == R) {
                ret.add(left.get(l++));
            } else if(l==L){
                ret.add(right.get(r++));
            }else{
                Interval i=left.get(l);
                Interval i2 = right.get(r);
                boolean swap=false;
                if(i.start>i2.start || (i.start==i2.start && i.end>i2.end)){
                    Interval t = i;
                    i=i2;
                    i2=t;
                    swap=true;
                }
                if(i.end < i2.start){
                    ret.add(i);
                    if (swap) r++;
                    else l++;
                }else if(i.end >= i2.start){
                    i.end = Math.max(i.end, i2.end);
                    if (swap) l++;
                    else r++;
                }else if(i2.end <= i.end){
                    if(swap) l++;
                    else r++;
                }else if (i.end <= i2.end){
                    if(swap) r++;
                    else l++;
                }
            }
        }

        //List<Interval> f = new ArrayList<>();
        return ret;
    }


    @org.junit.Test
    public void test(){

        assertEquals("[[1,3], [5,6]]", mergeInterval(com.sun.tools.javac.util.List.of(new Interval(1,2), new Interval(5,6)),
                com.sun.tools.javac.util.List.of(new Interval(1,3))).toString());
        assertEquals("[[1,3], [5,6]]", mergeInterval(com.sun.tools.javac.util.List.of(new Interval(1,2), new Interval(5,6)),
                com.sun.tools.javac.util.List.of(new Interval(1,5))).toString());

//        assertEquals("steps", shortestCompletingWord("1s3 PSt",new String[]{"step", "steps", "stripe", "stepple"} ));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
