package leetcode;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC787 {
    class Node{
        int pos, cost,k;
        Node(int p, int c, int k){
            this.pos=p;
            cost=c;
            this.k=k;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] g = new int[n][n];
        for(int[] f: flights){
            g[f[0]][f[1]]=f[2];
            //g[f[1]][f[0]]=f[2];
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(10, (a, b)->a.cost-b.cost);
        pq.add(new Node(src, 0, K+1));
        while(pq.size()>0){
            Node cur = pq.poll();
            if(cur.pos==dst)
                return cur.cost;
            if(cur.k>0){
                for(int i=0;i<n;i++){
                    if(g[cur.pos][i]>0 ){
                        pq.add(new Node(i, cur.cost+g[cur.pos][i], cur.k-1));
                    }
                }
            }
        }
        return -1;
    }

    @Test
    public void test(){
        assertEquals(200, findCheapestPrice(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}}, 0,2,1));
        assertEquals(6, findCheapestPrice(4, new int[][]{{0,1,1},{0,2,5},{1,2,1},{2,3,1}}, 0,3,1));
    }
}
