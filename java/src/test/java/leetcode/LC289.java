package leetcode;

public class LC289 {

    int numLive(int[][] b, int x, int y){
        int R=b.length;
        int C=b[0].length;
        if(x<0||x>=C) return 0;

        int c=0;
        for (int i = -1; i <= 1 ; i++) {
            int ny=y+i;
            if(ny<0||ny>=R) continue;
            if((b[ny][x]&1)==1)
                c++;
        }
        return c;
    }

    void gameOfLife(int[][] board) {
        int R= board.length;
        if(R==0) return;
        int C=board[0].length;

        int c2=0;
        int c1=0;
        int c0=0;
        for (int y = 0; y < R; y++) {
            c2=0;
            for (int x = 0; x < C; x++) {
                if (x==0){
                    c1=numLive(board, x,y);
                }else{
                    c2=c1;
                    c1=c0;
                }
                c0=numLive(board, x+1,y);
                int c = c2+c1+c0;
                if (board[y][x]==1){
                    if(c==3 || c==4)
                        board[y][x]|=2;
                }else{
                    if (c==3)
                        board[y][x]|=2;
                }
            }
        }

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                board[y][x] >>=1;
            }
        }
    }

    @org.junit.Test
    public void test(){
        gameOfLife(new int[][]{{1,1,1},{1,1,1},{1,1,1}});
//        assertEquals(new int[][] {{1,0,1},{0,0,0},{1,0,1}}, );
    }
}
