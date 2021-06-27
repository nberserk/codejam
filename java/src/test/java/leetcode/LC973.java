package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC973 {
    static class Distance{
        int idx;
        int dist;
        Distance(int idx, int dist){
            this.idx=idx;
            this.dist=dist;
        }
    }
    
    void swap(Distance[]d, int i, int j){
        Distance temp = d[i];
        d[i] = d[j];
        d[j] = temp;
    }
    int partition(Distance[] d, int left, int right){ // left=0, right=2
        int p = d[right].dist; // p= 20
        int idxSmallerEnd=left-1; // -1
        System.out.println(left + " " + right);
        for(int i=left; i<right;i++){ //i=2
            if(d[i].dist < p){
                idxSmallerEnd++; // idxSmallerEnd=0
                swap(d, i, idxSmallerEnd); // 18, 26, 20
            }
        }
        swap(d, idxSmallerEnd+1, right); // 18, 20, 26
        return idxSmallerEnd+1; // return 1
    }

    void quickselect(Distance[] d, int k){ // 18, 26, 20
        int left = 0;
        int right = d.length-1;
        while(left<right){
            int mid = partition(d, left, right); // mid=1

            if(mid<k)
                left=mid+1;
            else if(mid>k)
                right=mid-1;
            else
                return;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        int len = points.length;
        Distance[] dist = new Distance[len];

        for(int i=0;i<len;i++){
            int[] point = points[i] ;
            int distance = point[0]*point[0] + point[1]*point[1];
            dist[i] = new Distance(i, distance);
        }
        quickselect(dist, k-1);
        int[][] out= new int[k][2];
        for(int i=0;i<k;i++){
            out[i] = points[dist[i].idx];
        }
        return out;
    }

    public int[][] kClosest_pq(int[][] points, int k) {
        int len = points.length;
        PriorityQueue<Distance> queue = new PriorityQueue<>(k, (a,b)->-a.dist+b.dist);

        for(int i=0;i<len;i++){
            int[] point = points[i] ;
            int distance = point[0]*point[0] + point[1]*point[1];
            if(queue.size()<k){
                queue.add(new Distance(i, distance));
            }else if(queue.peek().dist>distance){
                queue.poll();
                queue.add(new Distance(i, distance));
            }
        }
        Distance[] sorted = queue.toArray(new Distance[k]);
        Arrays.sort(sorted, (a, b)->a.idx-b.idx);
        int[][] out = new int[sorted.length][2];
        for(int i=0;i<sorted.length;i++){
            out[i] = points[sorted[i].idx];
        }
        return out;
    }

    @Test
    public void test(){
        Assert.assertArrayEquals(new int[][]{{3,3},{-2,4}} , kClosest(new int[][]{{3,3},{5,-1},{-2,4}}, 2));
    }
}
