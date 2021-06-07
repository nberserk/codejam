package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LC1650 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    List<Node> getParentList(Node n){
        List<Node> p = new ArrayList<>();
        while(n!=null){
            p.add(n);
            n=n.parent;
        }
        return p;
    }
    public Node lowestCommonAncestor(Node p, Node q) {
        List<Node> p1 = getParentList(p);
        List<Node> p2 = getParentList(q);
        Collections.reverse(p1); // 3, 4
        Collections.reverse(p2); // 3, 1
        Node lowest = p1.get(0);
        for(int i=0;i<Math.min(p1.size(), p2.size());i++){
            if(p1.get(i).val == p2.get(i).val){
                lowest = p1.get(i);
            }else
                break;
        }
        return lowest;

    }

    @Test
    public void test(){
    }
}
