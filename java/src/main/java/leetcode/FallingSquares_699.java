package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class FallingSquares_699 {

    static class Height{
        int x,height,dir; //0:up, 1:down, 2:dot
        Height(int x, int h, int d){
            this.x=x;
            height=h;
            dir=d;
        }
    }

    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> r = new ArrayList<>();
        TreeSet<Height> tree = new TreeSet<>((a,b)->a.x==b.x ? a.height-b.height:a.x-b.x);
//        tree.add(new Height(0,0,0));
//        tree.add(new Height(Integer.MAX_VALUE,0,1));

        int max=0;
        for (int[] p: positions){
            Height left = new Height(p[0],0,0);
            Height right = new Height(p[0]+p[1]-1, Integer.MAX_VALUE, 1);

            int highest=0;
            NavigableSet<Height> cross = tree.subSet(left, true, right, true);
            if(cross.size()>0){
                for(Height h: cross){
                    highest=Math.max(highest, h.height);
                }
            }
            Height lower = tree.lower(left);
            if(lower!=null&& lower.dir==0)
                highest=Math.max(highest, lower.height);
            Height higher = tree.higher(right);
            if(higher!=null&&higher.dir==1)
                highest=Math.max(highest, higher.height);

            left.height=highest+p[1];
            right.height=left.height;
            if(left.x==right.x){
                left.dir=2;
                tree.add(left);
            }else {
                tree.add(left);
                tree.add(right);
            }

            max=Math.max(max, left.height);
            r.add(max);
        }

        return r;
    }

    @org.junit.Test
    public void test(){
        assertEquals("[7, 16, 17]", fallingSquares(new int[][]{{9,7},{1,9},{3,1}}).toString());
        assertEquals("[1, 2, 4]", fallingSquares(new int[][]{{6,1},{9,2},{2,4}}).toString());
        assertEquals("[2, 5, 5]", fallingSquares(new int[][]{{1,2},{2,3},{6,1}}).toString());
    }
}
