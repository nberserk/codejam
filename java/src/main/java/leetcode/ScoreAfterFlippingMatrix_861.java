package leetcode;

import static org.junit.Assert.assertEquals;

public class ScoreAfterFlippingMatrix_861 {
    public int matrixScore(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        int ret = (1<<(C-1))*R;

        for (int c = 1; c < C; c++) {
            int oneCount=0;
            for (int r = 0; r < R; r++) {
                oneCount += A[r][0]==A[r][c]?1:0;
            }
            if (oneCount>R-oneCount){
                ret += (1<<(C-1-c))*oneCount;
            }else{
                ret += (1<<(C-1-c))*(R-oneCount);
            }
        }

        return ret;
    }

    public int matrixScore_1st(int[][] A) {
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
        assertEquals((int)Math.pow(2, 6), 1<<6);
        assertEquals(1, 1<<0);
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
