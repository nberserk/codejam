package leetcode;


import java.util.*;

import static org.junit.Assert.assertEquals;

public class _797 {
    class Node{
        int id;
        Node parent;
        Node(int id, Node p){
            this.id=id;
            parent=p;
        }
    }
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int N = graph.length;
        List<List<Integer>> ret = new ArrayList<>();

        Stack<Node> s = new Stack<>();
        s.push(new Node(0,null));
        while(s.size()>0){
            Node c = s.pop();
            if(c.id==N-1){
                List<Integer> r = new ArrayList<>();
                //r.add(N-1);
                Node t = c;
                while(t!=null){
                    r.add(t.id);
                    t=t.parent;
                }
                Collections.reverse(r);
                ret.add(r);
                continue;
            }

            for(int next:graph[c.id]){
                s.push(new Node(next, c));
            }
        }

        return ret;
    }


    @org.junit.Test
    public void test() {
        Stack<Integer> s= new Stack<>();
        s.push(1);
        s.push(2);
        ArrayList<Integer> a = new ArrayList<>(s);
        assertEquals((Integer)1, a.get(0));

        assertEquals("[[0, 2, 3], [0, 1, 3]]", allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}}).toString());



    }
}
