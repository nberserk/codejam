package leetcode;

import static org.junit.Assert.assertEquals;

public class AllNodesDistanceKInBinaryTree_863 {

    public int matrixScore(int[][] A) {
        int R = A.length;
        int C = A[0].length;

        for (int c = 0; c < C; c++) {
            int count=0;
            for (int r = 0; r < R; r++) {
                if (A[r][c]==1)count++;
            }
            if(count<R-count){
                for (int r = 0; r < R; r++) {
                    A[r][c]= A[r][c]==1?0:1;
                }
            }
            //
            if(c==0){
                for (int r = 0; r < R; r++) {
                    if(A[r][c]==0){
                        for (int j = 0; j < C; j++) {
                            A[r][j] = A[r][j]==0? 1:0;
                        }
                    }
                }
            }
        }

        int ret = 0;
        for (int r = 0; r < R; r++) {
            int mul=1;
            for (int c = C-1; c >=0; c--) {
                if(A[r][c]==1)
                    ret+=mul;
                mul*=2;
            }
        }
        return ret;
    }


    @org.junit.Test
    public void test(){
        int[][] m2 = new int[][]{
                {0,1},
                {1,1}
        };
        assertEquals(5, matrixScore(m2));

        int[][] m = new int[][]{
                {0,0,1,1},
                {1,0,1,0},
                {1,1,0,0}
        };
        assertEquals(39, matrixScore(m));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
