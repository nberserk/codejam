package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertArrayEquals;

public class CandyCrush_723 {

    public int[][] candyCrush(int[][] board) {

        while(true){
            boolean crushed = crush(board);
            if(!crushed) break;
            drop(board);
        }

        return board;
    }

    private void drop(int[][] board) {
        int R= board.length;
        int C=board[0].length;

        for (int x = 0; x < C; x++) {
            int dy=-1;
            for (int y = R-1; y >=0; y--) {
                if(board[y][x]==0){
                    if (dy==-1)dy=y;
                }else if(dy!=-1){
                    board[dy--][x]=board[y][x];
                    board[y][x]=0;
                }
            }
        }
    }

    int hash(int x, int y){
        return x*mod+y;
    }

    int mod=10000;
    private boolean crush(int[][] board) {
        boolean crushed=false;
        int R= board.length;
        int C=board[0].length;
        int[][] d = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
        HashSet<Integer> visited = new HashSet<>();

        List<int[]> remove = new ArrayList<>();

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if(board[y][x]!=0){
                    int color = board[y][x];
                    int start=x;
                    while(x+1<C&&board[y][x+1]==color)
                        x++;

                    if(x-start>=2)
                        remove.add(new int[]{hash(start,y), hash(x,y)});
                }
            }
        }

        for (int x = 0; x < C; x++) {
            for (int y = 0; y < R; y++) {
                if(board[y][x]!=0){
                    int color = board[y][x];
                    int start=y;
                    while(y+1<R && board[y+1][x]==color)
                        y++;

                    if(y-start>=2)
                        remove.add(new int[]{hash(x, start), hash(x,y)});
                }
            }
        }

        for (int[] range: remove){
            int sx = range[0]/mod;
            int sy = range[0]%mod;
            int ex = range[1]/mod;
            int ey = range[1]%mod;

            if (sy==ey){
                for (int i = sx; i <= ex; i++) {
                    board[sy][i]=0;
                }
            }
            if (sx==ex){
                for (int i = sy; i <= ey; i++) {
                    board[i][sx]=0;
                }
            }
        }
        return remove.size()>0?true:false;
    }

    @org.junit.Test
    public void test(){

        assertArrayEquals(new int[][]{{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{110,0,0,0,114},{210,0,0,0,214},{310,0,0,113,314},{410,0,0,213,414},{610,211,112,313,614},{710,311,412,613,714},{810,411,512,713,1014}},
                candyCrush(new int[][]{{110,5,112,113,114},{210,211,5,213,214},{310,311,3,313,314},{410,411,412,5,414},{5,1,512,3,3},{610,4,1,613,614},{710,1,2,713,714},{810,1,2,1,1},{1,1,2,2,2},{4,1,4,4,1014}}));
    }
}
