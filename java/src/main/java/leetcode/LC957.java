package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 *
 */
public class LC957 {
    public int[] prisonAfterNDays(int[] cells, int N) {
        HashSet<Integer> seen = new HashSet<>();
        int state = 0;
        for (int i = 0; i < cells.length; i++) {
            int cur= cells[i]==1? 1:0;
            state|=1<<i;
        }

        while(N>0){
            if (seen.contains(state)){
                N%=seen.size();
                seen.clear();
            }
            seen.add(state);
            if(N>0){
                N--;
                state=apply(state);
            }
        }

        for (int j = 0; j < cells.length; j++) {
            cells[j] = ((state>>j)&1)==1 ? 1:0;
        }
        return cells;
    }

    private int apply(int state) {
        int ret =0;
        for (int i = 1; i <= 6; i++) {
            if( ((state>>(i-1))&1) == (( state>>(i+1) )&1) ){
                ret|=(1<<i);
            }
        }

        return ret;
    }


    @Test
    public void test(){
        Assert.assertArrayEquals(new int[]{0,0,1,0,0,1,1,0}, prisonAfterNDays(new int[]{0, 1, 1, 1, 0, 0, 0, 0}, 99));
        //Assert.assertArrayEquals(new int[]{0,0,0,0,1,1,1,0}, prisonAfterNDays(new int[]{0,0,1,0,0,1,1,0}, 100));

//[0,0,1,1,1,1,1,0]

    }
}
