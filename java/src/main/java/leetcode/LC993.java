package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 */
public class LC994 {
    int mod=100;
    public int orangesRotting(int[][] grid) {
        ArrayList<Integer> q = new ArrayList<>();
        int R=grid.length;
        int C=grid[0].length;

        for(int i=0;i<R;i++){
            for(int c=0;c<C;c++){
                if(grid[i][c]==1)
                    q.add(i*mod+c);
            }
        }

        int day=0;
        for (int i = 0; i < q.size(); i++) {
            int cur = bfs(q.get(i), grid);
            if(cur==-1) return -1;
            day=Math.max(day,cur);
        }
        return day;
    }

    class Node{
        int pos;
        int depth;
        Node(int pos, int d){
            this.pos=pos;
            depth=d;
        }
    }

    private int bfs(int start, int[][] grid) {
        LinkedList<Node> q = new LinkedList<>();
        int R=grid.length;
        int C=grid[0].length;

        q.add(new Node(start,0));
        HashSet<Integer> visited = new HashSet<>();
        while(q.size()>0){
            Node cur = q.pollFirst();
            if(visited.contains(cur.pos)) continue;
            visited.add(cur.pos);
            int y = cur.pos/mod;
            int x = cur.pos%mod;
            if (grid[y][x]==2){
                return cur.depth;
            }
            int[][] d = {{-1,0},{1,0},{0,-1},{0,1}};
            for(int k=0;k<4;k++){
                int nx=x+d[k][0];
                int ny=y+d[k][1];
                if(nx<0||ny<0||nx>=C||ny>=R||grid[ny][nx]==0) continue;
                q.add(new Node(ny*mod+nx, cur.depth+1));
            }
        }
        return -1;
    }


    @Test
    public void test(){
        Assert.assertEquals(1, orangesRotting(new int[][]{{2,2},{1,1},{0,0},{2,0}}));
    }
}
