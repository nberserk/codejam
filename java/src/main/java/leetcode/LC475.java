package leetcode;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class _475 {

    private class Node{
        int pos;
        boolean isHouse;

        Node(int p, boolean h){
            this.pos=p;
            this.isHouse=h;
        }
    }

    public int findRadius(int[] houses, int[] heaters) {

        ArrayList<Node> nodes = new ArrayList<>();
        for(int i:houses){
            Node n = new Node(i, true);
            nodes.add(n);
        }
        for(int i:heaters){
            Node n=new Node(i, false);
            nodes.add(n);
        }
        Collections.sort(nodes, (a,b)-> (a.pos == b.pos) ? ( a.isHouse? -1:1) : (a.pos - b.pos));
        int prevHeat=-1;
        int max = 0;

        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).isHouse==false){
                if(prevHeat==-1){
                    max= nodes.get(i).pos- nodes.get(0).pos;
                }else{
                    int cur=0;
                    Node p=nodes.get(prevHeat);
                    Node n=nodes.get(i);
                    for (int j = prevHeat+1; j <= i - 1; j++) {
                        if(nodes.get(j).isHouse){
                            cur=Math.max(cur, Math.min(nodes.get(j).pos-p.pos, n.pos-nodes.get(j).pos));
                        }
                    }
                    max=Math.max(max, cur);
                }
                prevHeat=i;
            }
        }
        if(prevHeat!=nodes.size()-1){
            max=Math.max(max, nodes.get(nodes.size()-1).pos - nodes.get(prevHeat).pos);
        }
        return max;
    }


    @org.junit.Test
    public void test() {
        Assert.assertEquals(1, findRadius(new int[]{1,2,3,4}, new int[]{1,4}));
        Assert.assertEquals(1, findRadius(new int[]{1,2,4, 10, 12}, new int[]{1,4, 13}));
    }
}
