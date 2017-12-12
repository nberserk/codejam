package leetcode;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class NetworkDelayTime_743 {

    class Node{
        int time, n;
        Node(int n, int t){
            this.n=n;
            time=t;
        }
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        int[] n = new int[N+1];
        Arrays.fill(n,Integer.MAX_VALUE);
        HashMap<Integer, Set<int[]>> edge = new HashMap<>();
        for(int[] t: times){
            edge.putIfAbsent(t[0], new HashSet<>());
            edge.get(t[0]).add(t);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(100, (a,b)->a.time-b.time);
        pq.add(new Node(K, 0));
        while(pq.size()>0){
            Node cur = pq.poll();
            if(cur.time>=n[cur.n]) continue;
            n[cur.n]=cur.time;
            Set<int[]> set = edge.get(cur.n);
            if(set==null) continue;
            for(int[] ti: set){
                if(n[ti[1]]>cur.time+ti[2]){
                    pq.add(new Node(ti[1], cur.time+ti[2]));
                }
            }
        }
        int max=Integer.MIN_VALUE;
        for(int i=1;i<N+1;i++){
            if(n[i]==Integer.MAX_VALUE) return -1;
            max=Math.max(max, n[i]);
        }
        return max;
    }


    @org.junit.Test
    public void test(){
        assertEquals(2, networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}}, 4,2));

                
      
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
