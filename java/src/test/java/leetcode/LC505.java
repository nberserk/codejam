package leetcode;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class LC505 {

    class Node{
        int x,y,cost;
        Node(int x, int y, int c){
            this.x=x;
            this.y=y;
            cost=c;
        }
    }
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int R=maze.length;
        int C=maze[0].length;

        PriorityQueue<Node> pq = new PriorityQueue<>(100,(a, b)->a.cost-b.cost );
        pq.add(new Node(start[1], start[0], 0));
        boolean[][] v = new boolean[R][C];
        int[][] d= new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        while(pq.size()>0){
            Node cur = pq.poll();
            if(cur.x==destination[1] && cur.y==destination[0])
                return cur.cost;
            if(v[cur.y][cur.x]) continue;
            v[cur.y][cur.x]=true;
            for(int i=0;i<4;i++){
                int[] dir = d[i];
                int nx=cur.x;
                int ny=cur.y;
                while(true){
                    nx+=dir[0]; ny+=dir[1];
                    if(nx<0||ny<0||nx>=C||ny>=R||maze[ny][nx]==1)
                        break;
                }
                nx-=dir[0];
                ny-=dir[1];
                pq.add(new Node(nx,ny,cur.cost+Math.abs(nx-cur.x)+Math.abs(ny-cur.y)));
            }
        }
        return -1;
    }

    @org.junit.Test
    public void test(){
        assertEquals(12, shortestDistance(new int[][]{{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}},
                new int[]{0,4}, new int[]{4,4}));
    }
}
