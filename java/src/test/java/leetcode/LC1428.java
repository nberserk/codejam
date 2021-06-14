package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class LC1428 {
    static class BinaryMatrix {
        public int get(int row, int col) {
            return 1;
        }
        public List<Integer> dimensions() {
            return null;
        }
    }

    int find(BinaryMatrix m,int row, int lo, int hi){
        while(lo<hi){
            int mid = (lo+hi)/2;
            if(m.get(row, mid)==1){
                hi=mid;
            }else
                lo=mid+1;
        }
        return lo;
    }
    public int leftMostColumnWithOne(BinaryMatrix m) {
        List<Integer> dimension = m.dimensions();
        int row = dimension.get(0);
        int col = dimension.get(1);
        int lo = 0;
        int hi = col;
        for(int i=0;i<dimension.get(0);i++){
            int leftmost = find(m, i, lo, hi);
            if(leftmost<col){
                hi=leftmost;
            }
        }

        if(hi==col)
            hi=-1;
        return hi;
    }

    @Test
    public void test(){

    }
}
