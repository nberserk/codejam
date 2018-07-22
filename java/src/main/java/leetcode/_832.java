package leetcode;


import leetcode.common.JUnitHelper;

public class _832 {

    void swap(int[]a, int i, int j){
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
    public int[][] flipAndInvertImage(int[][] A) {
        int R=A.length;
        int C=A[0].length;
        for(int r=0;r<R;r++){
            int l=0;
            int h=C-1;
            while(l<h){
                swap(A[r], l, h);
                A[r][l]=A[r][l]==0?1:0;
                A[r][h]=A[r][h]==0?1:0;
                l++;
                h--;
            }
            if(l==h){
                A[r][l]=A[r][l]==0?1:0;
            }
        }
        return A;
    }

    @org.junit.Test
    public void test() {

        JUnitHelper.assert2DArrayEquals(new int[][]{
                        {1,0,0},
                        {0,1,0},
                        {1,1,1}
                }
        ,flipAndInvertImage(new int[][]{
                {1,1,0},
                {1,0,1},
                {0,0,0}}));

    }
}
