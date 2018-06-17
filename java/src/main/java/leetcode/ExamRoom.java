package leetcode;

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class ExamRoom {

    TreeSet<Integer> tree = new TreeSet<>();
    int N;



    public void init(int N) {
        this.N=N;
    }

    public int seat() {
        if(tree.size()==0) {
            tree.add(0);
            return 0;
        }
        int ret=0;
        int maxDistance=Integer.MIN_VALUE;
        if (tree.first()!=0){
            int dist = tree.first();
            if(dist>maxDistance){
                ret=0;
                maxDistance=dist;
            }
        }
        if(tree.last()!=N-1){
            int dist = N-1-tree.last();
            if(dist>maxDistance){
                ret=N-1;
                maxDistance=dist;
            }
        }

        for(int d: tree){
            Integer high = tree.higher(d);
            if(high==null) break;
            int dist = (high - d)/2;
            if(dist>maxDistance){
                ret=d+dist;
                maxDistance=dist;
            }
        }
        tree.add(ret);
        return ret;
    }

    public void leave(int p) {
        tree.remove(p);
    }


    @org.junit.Test
    public void test(){
        ExamRoom er = new ExamRoom();
        er.init(10);

        assertEquals(0, er.seat());
        assertEquals(9, er.seat());
        assertEquals(4, er.seat());
        assertEquals(2, er.seat());
        er.leave(4);
        assertEquals(5, er.seat());
    }
}
