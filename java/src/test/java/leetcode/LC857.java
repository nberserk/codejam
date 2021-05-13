package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class LC857 {
    static class Node{
        int q,w;
        double qv;//qualtiy value
    }
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int N = quality.length;
        Node[] nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            nodes[i] = new Node();
            nodes[i].q = quality[i];
            nodes[i].qv = (double) wage[i]/quality[i];
        }

        Comparator<Node> cp = new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                return Double.compare(a.qv,b.qv);
            }
        };
        Arrays.sort(nodes, cp);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        double sum=0;
        double ret=Double.MAX_VALUE;
        for(Node n: nodes){
            pq.add(-n.q);
            sum+=n.q;
            if (pq.size()>K){
                sum +=pq.poll();
            }
            if (pq.size()==K){
                ret=Math.min(ret, sum*n.qv);
            }
        }

        return ret;
    }

    @org.junit.Test
    public void test(){
        assertEquals(30.66667, mincostToHireWorkers(new int[]{3,1,10,10,1}, new int[]{4,8,2,2,7}, 3), 0.00001);
        assertEquals(105.0000, mincostToHireWorkers(new int[]{10,20,5}, new int[]{70,50,30}, 2), 0.00001);
    }
}
