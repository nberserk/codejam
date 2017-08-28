package leetcode;

import java.util.*;

import static org.junit.Assert.assertEquals;


//  https://leetcode.com/problems/find-k-closest-elements/description/
public class FindKClosestElements_658 {

    static class Node{
        int v, org;
        Node(int v, int org){
            this.v=v;
            this.org=org;
        }
    }

    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        LinkedList<Integer> ret = new LinkedList<>();
        if(k==0 || arr.size()==0) return ret;

        PriorityQueue<Node> pq = new PriorityQueue<>(  (a,b) -> a.v==b.v? b.org-a.org:b.v-a.v );

        for(int v: arr){
            pq.add(new Node(Math.abs(x-v), v));
            if(pq.size()>k){
                pq.poll();
            }
        }

        while(pq.size()>0){
            ret.addFirst(pq.poll().org);
        }
        Collections.sort(ret);
        return ret;
    }


    @org.junit.Test
    public void test(){

        assertEquals("[1, 2, 3, 4]", findClosestElements(Arrays.asList(1,2,3,4,5), 4,3).toString());
    }
}
