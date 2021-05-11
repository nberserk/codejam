package main.java.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by darren on 3/12/17.
 */
public class MineSweeper_529 {
    int neighbor(char[][]b, int cx, int cy){
        int c=0;
        int row = b.length;
        int col=b[0].length;
        for(int y=cy-1;y<=cy+1;y++){
            if(y<0||y>=row)continue;
            for(int x=cx-1;x<=cx+1;x++){
                if(x<0||x>=col) continue;
                if(b[y][x]=='M')c++;
            }
        }
        return c;
    }
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = board.length;
        int col=board[0].length;
        int cy=click[0];
        int cx=click[1];

        if(board[cy][cx]=='M'){
            board[cy][cx]='X';
        }else if(board[cy][cx]=='E' ){
            int count = neighbor(board, cx,cy);
            if(count==0){
                board[cy][cx] = 'B';
                for(int y=cy-1;y<=cy+1;y++){
                    if(y<0||y>=row)continue;
                    for(int x=cx-1;x<=cx+1;x++){
                        if(x<0||x>=col) continue;
                        if(x==cx&&y==cy)continue;
                        updateBoard(board, new int[]{y,x});
                    }
                }

            }
            else board[cy][cx]=(char)('0'+count);


        }
        return board;
    }


    @Test
    public void test(){
        char[][] ret = updateBoard(new char[][]{"EEEEE".toCharArray(),"EEMEE".toCharArray(),"EEEEE".toCharArray(),"EEEEE".toCharArray()}, new int[]{3,0} );

        assertArrayEquals( new char[][]{"B1E1B".toCharArray(),"B1M1B".toCharArray(),"B111B".toCharArray(),"BBBBB".toCharArray()}  , ret);

    }
}
