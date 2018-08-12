package leetcode;

import leetcode.common.JUnitHelper;
import org.junit.Test;

/**
 *
 */
public class LC889 {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] path = new int[R*C][2];

        int[][] offset = {{1,0},{0,1},{-1,0},{0,-1}};
        int dir=0;
        int pi=0;
        int r=r0, c=c0;
        path[pi][0]=r;
        path[pi++][1]=c;
        int len=1;
        while(pi<R*C){
            int move=len;
            while(move>0){
                r += offset[dir][1];
                c += offset[dir][0];
                if(r>=0&&c>=0&&r<R&&c<C){
                    path[pi][0]=r;
                    path[pi][1]=c;
                    //System.out.println(r+","+c);
                    pi++;
                }
                move--;
            }

            if(dir==1 || dir==3) len++;
            dir++; dir%=4;
        }

        return path;
    }

    @Test
    public void test(){

        JUnitHelper.assert2DArrayEquals(new int[][]{{1,4},{1,5},{2,5},{2,4},{2,3},{1,3},{0,3},{0,4},{0,5},{3,5},{3,4},{3,3},{3,2},{2,2},{1,2},{0,2},{4,5},{4,4},{4,3},{4,2},{4,1},{3,1},{2,1},{1,1},{0,1},{4,0},{3,0},{2,0},{1,0},{0,0}},
                spiralMatrixIII(5,6,1,4));


        
    }
}
