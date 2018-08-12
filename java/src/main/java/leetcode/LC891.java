package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC891 {

    public int superEggDrop(int K, int N) {
        return minMoves(1, N, K);
    }

    private int minMoves(int start, int end, int k) {
        if(k==1){
            return end-start+1;
        }
        int mid = start+(end-start)/2;
        int min = Math.min(minMoves(start,mid-1, k-1), minMoves(mid+1,end,k-1));
        return min+1;
    }

    @Test
    public void test(){
        assertEquals(2, superEggDrop(1, 2));
        assertEquals(3, superEggDrop(2, 6));
        assertEquals(4, superEggDrop(3, 14));
    }
}
