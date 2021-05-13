package leetcode;

import static org.junit.Assert.assertEquals;

public class LC562 {

    public int longestLine(int[][] M) {
        int R = M.length;
        if (R == 0) return 0;
        int C = M[0].length;
        int ret = 0;

        int[][][] dp = new int[R][C][4];
        // 0 ->, 1: |, 2:\ 3:/
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (M[r][c]==1){
                    dp[r][c][0]= c>0? dp[r][c-1][0] +1:1;
                    dp[r][c][1]= r>0? dp[r-1][c][1] +1:1;
                    dp[r][c][2]= r>0&&c>0? dp[r-1][c-1][2] +1:1;
                    dp[r][c][3]= r>0&&c<C-1? dp[r-1][c+1][3] +1:1;
                    ret=Math.max(ret, Math.max(dp[r][c][3], Math.max(Math.max(dp[r][c][0],dp[r][c][1]), dp[r][c][2])));
                }
            }
        }
        return ret;
    }

    public int longestLine_1st(int[][] M) {
        int R=M.length;
        if(R==0) return 0;
        int C=M[0].length;
        int ret=0;
        for(int i=0;i<R;i++){
            int c=0;
            for(int j=0;j<C;j++){
                if(M[i][j]==1){
                    c++;
                    ret=Math.max(ret, c);
                }else c=0;
            }
        }
        for(int i=0;i<C;i++){
            int c2=0;
            for(int j=0;j<R;j++){
                if(M[j][i]==1){
                    c2++;
                    ret=Math.max(ret,c2);
                }else c2=0;
            }
        }
        // 1,-1 or 1,1
        for(int i=0;i<R;i++){
            int c=0;
            int x=0;
            int y=i;
            while(y>=0&&x<C){
                if(M[y][x]==1){
                    c++;
                    ret=Math.max(ret, c);
                }else c=0;
                x++;
                y--;
            }
            c=0;
            x=0;
            y=i;
            while(y<R&&x<C){
                if(M[y][x]==1){
                    c++;
                    ret=Math.max(ret, c);
                }else c=0;
                x++;
                y++;
            }
        }

        // down right
        for(int i=0;i<C;i++){
            int c=0;
            int x=i;
            int y=0;
            while(x<C&&y<R){ // 1,1
                if(M[y][x]==1){
                    c++;
                    ret=Math.max(ret, c);
                }else c=0;
                x++;
                y++;
            }
            c=0;
            x=i;
            y=R-1;
            while(x<C&&y>=0){ // up right
                if(M[y][x]==1){
                    c++;
                    ret=Math.max(ret, c);
                }else c=0;
                x++;
                y--;
            }
        }

        return ret;
    }

    @org.junit.Test
    public void test() {
        assertEquals(3, longestLine(new int[][]{
                {0,1,1,0},
                {0,1,1,0},
                {0,0,0,1}
        }));
    }
}
