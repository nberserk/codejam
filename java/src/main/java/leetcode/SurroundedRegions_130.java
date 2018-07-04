package leetcode;

import java.util.Stack;

public class SurroundedRegions_130 {

    static class Node{
        int r,c;
        Node(int r, int c){
            this.r=r; this.c=c;
        }
    }
    public void solve(char[][] board) {
        int R = board.length;
        if(R==0) return;
        int C = board[0].length;
        int[][] v = new int[R][C];// 1
        Stack<Node> s = new Stack<>();
        for(int c=0;c<C;c++){
            if(board[0][c]=='O') s.add(new Node(0,c));
            if(board[R-1][c]=='O') s.add(new Node(R-1,c));
        }

        for(int r=1;r<R-1;r++){
            if(board[r][0]=='O') s.add(new Node(r,0));
            if(board[r][C-1]=='O') s.add(new Node(r,C-1));
        }

        int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
        while(s.size()>0){
            Node c = s.pop();
            if(v[c.r][c.c]==1) continue;
            v[c.r][c.c]=1;
            for(int i=0;i<4;i++){
                int nr = c.r+dir[i][0];
                int nc = c.c+dir[i][1];
                if(nr<0||nc<0||nr>=R||nc>=C|| board[nr][nc]=='X' || v[nr][nc]==1) continue;
                s.add(new Node(nr,nc));
            }
        }

        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(board[r][c]=='O'&&v[r][c]!=1)
                    board[r][c]='X';
            }
        }


    }
}
