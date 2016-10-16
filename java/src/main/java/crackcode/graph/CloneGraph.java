package main.java.crackcode.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/15/16.
 */
public class CloneGraph {
    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if(node==null)        return null;
        if(map.containsKey(node.label)) return map.get(node.label);

        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node.label, clone);
        for(UndirectedGraphNode n : node.neighbors){
            clone.neighbors.add( clone(n));
        }
        return clone;
    }

    @Test
    public void test(){
        UndirectedGraphNode n1 = new UndirectedGraphNode(1);
        UndirectedGraphNode n2 = new UndirectedGraphNode(2);
        UndirectedGraphNode n3 = new UndirectedGraphNode(3);

        n1.neighbors.add(n2);
        n1.neighbors.add(n1);
        n1.neighbors.add(n3);

        CloneGraph cg = new CloneGraph();
        UndirectedGraphNode cloned = cg.cloneGraph(n1);
        assertEquals(1, cloned.label);
        assertEquals(3, cloned.neighbors.size());
        assertEquals(0, cloned.neighbors.get(0).neighbors.size());
    }
}
