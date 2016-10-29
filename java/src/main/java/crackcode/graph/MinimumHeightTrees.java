package main.java.crackcode.graph;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/29/16.
 *
 * from: https://leetcode.com/problems/minimum-height-trees/
 *
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 * Format
 *  The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 *   You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 *   Example 1:
 *   Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *

     0
     |
     1
     / \
     2   3
     return [1]

     Example 2:

     Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
     \ | /
     3
     |
     4
     |
     5
     return [3, 4]
 *
 */
public class MinimumHeightTrees {
    static class Node{
        int v, depth;
        Node parent;
        Node(int v, int d, Node p){
            this.v=v;
            depth=d;
            parent=p;
        }
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for(int[] e: edges){
            List<Integer> list = map.get(e[0]);
            if(list==null)
                list = new ArrayList<>();
            list.add(e[1]);
            map.put(e[0], list);

            list = map.get(e[1]);
            if(list==null)
                list = new ArrayList<>();
            list.add(e[0]);
            map.put(e[1], list);
        }

        //find leaf
        int leaf=0;
        for(int key:map.keySet()){
            List<Integer> list = map.get(key);
            if(list.size()==1){
                leaf=key;
                break;
            }
        }
        List<Integer> path = dfs(leaf, map, n);
        path = dfs(path.get(0), map, n);

        System.out.println(path);
        List<Integer> ret = new ArrayList<>();
        if(path.size()%2==1){
            ret.add(path.get(path.size()/2));
        }else{
            ret.add(path.get(path.size()/2));
            ret.add(path.get(path.size()/2-1));
        }

        return ret;
    }

    List<Integer> dfs(int start, Map<Integer, List<Integer>> map, int n){
        boolean[] v = new boolean[n];
        Stack<Node> stack=new Stack<Node>();
        stack.add(new Node(start,0,null));
        Node longest = null;
        while(stack.size()>0 ){
            Node cur = stack.pop();
            if(v[cur.v]) continue;
            v[cur.v]=true;
            if(longest==null || cur.depth>longest.depth ){
                longest=cur;
            }

            List<Integer> connected = map.get(cur.v);
            if(connected==null) continue;
            for(int next:connected){
                stack.push(new Node(next, cur.depth+1, cur));
            }
        }
        Node temp = longest;
        List<Integer> path = new ArrayList<>();
        while(temp!=null){
            path.add(temp.v);
            temp=temp.parent;
        }
        return path;
    }

    @Test
    public void test(){
        assertEquals("[1]", findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}).toString());
        assertEquals("[4, 3]", findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3},{4,3},{5,4}}).toString());
    }
}
