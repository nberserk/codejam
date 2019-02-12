package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC675 {

    static class Node{
        int h, x,y;
        Node(int x,int y, int h){
            this.x=x;
            this.y=y;
            this.h=h;
        }
    }

    public int cutOffTree(List<List<Integer>> forest) {
        PriorityQueue<Node> pq = new PriorityQueue<>(10, (a,b) -> a.h-b.h);
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                int h = forest.get(i).get(j);
                if(h>1){
                    pq.add(new Node(j,i,h));
                }
            }
        }
        //
        int x = 0;
        int y=0;
        int move=0;
        while (pq.size()>0){
            Node cur = pq.poll();
            int r = dfs(x,y, cur.x,cur.y, forest);
            if(r==-1)
                return r;
            move += r;
            x=cur.x;
            y=cur.y;
        }
        return move;
    }

    static class TNode{
        int pos,depth;
        TNode(int p, int d){
            pos=p;
            depth=d;
        }
//        @Override
//        public int hashCode() {
//            Objects.hash(pos,depth)
//            return super.hashCode();
//        }
    }

    private int dfs(int sx, int sy, int dx, int dy, List<List<Integer>> f) {

        int ROW=f.size();
        int COL=f.get(0).size();

        int mod =100;
        int start = sy*mod+sx;
        LinkedList<TNode> queue = new LinkedList<>();
        queue.add(new TNode(start, 0));
        HashSet<Integer> visited = new HashSet<>();
        while(queue.size()>0){
            TNode cur = queue.pollFirst();
            if(visited.contains(cur.pos)) continue;
            visited.add(cur.pos);
            int x = cur.pos%mod;
            int y = cur.pos/mod;
            if(x==dx && y==dy){
                return cur.depth;
            }
            int[][] d = { {-1,0},{1,0},{0,-1},{0,1}  };
            for (int i = 0; i < d.length; i++) {
                int nx = x + d[i][0];
                int ny = y + d[i][1];
                if(nx<0||ny<0||nx>=COL || ny>=ROW) continue;
                if (f.get(ny).get(nx)==0) continue;
                queue.addLast(new TNode(nx+ny*mod, cur.depth+1));
            }
        }
        return -1;
    }

    @Test
    public void test(){
        List<Integer> r0 = new ArrayList<>();
        r0.add(1);
        r0.add(2);
        r0.add(3);
        List<Integer> r1 = new ArrayList<>();
        r1.add(0);
        r1.add(0);
        r1.add(4);
        List<Integer> r2 = new ArrayList<>();
        r2.add(7);
        r2.add(6);
        r2.add(5);
        List<List<Integer>> m = new ArrayList<>();
        m.add(r0);
        m.add(r1);
        m.add(r2);

        Assert.assertEquals( 6, cutOffTree(m));
    }
}
