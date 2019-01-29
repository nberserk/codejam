package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

public class LC973 {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, (a, b)-> points[a][0]*points[a][0] +points[a][1]*points[a][1]
                -points[b][0]*points[b][0]-points[b][1]*points[b][1] );

        for(int i=0;i<points.length;i++){
            pq.add(i);
            //if(pq.size()>K) pq.poll();
        }
        int[][] r = new int[K][2];
        int i=0;
        while(i<K){
            int idx = pq.poll();

                r[i][0]=points[idx][0];
                r[i++][1] = points[idx][1];

        }
        return r;
    }

        @Test
    public void test(){
        Assert.assertArrayEquals(new int[][]{{3,3},{-2,4}} , kClosest(new int[][]{{3,3},{5,-1},{-2,4}}, 2));
    }
}
