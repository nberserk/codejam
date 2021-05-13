package leetcode;


import static org.junit.Assert.assertEquals;

public class LC836 {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if(rec1[2]<= rec2[0] || rec2[2]<=rec1[0] || rec1[3]<=rec2[1] || rec2[3]<=rec1[1])
            return false;
        return true;
    }

    @org.junit.Test
    public void test() {
        assertEquals(true, isRectangleOverlap(new int[]{0,0,2,2}, new int[]{1,1,3,3}));
        assertEquals(false, isRectangleOverlap(new int[]{0,0,1,1}, new int[]{1,0,2,1}));
    }
}
