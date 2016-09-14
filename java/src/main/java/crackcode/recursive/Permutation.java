package main.java.crackcode.recursive;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by darren on 9/13/16.
 */
public class Permutation extends TestCase {

    private static class Node{
        int[] c;
        int idx;
        public Node(int[]a, int idx){
            this.c = a;
            this.idx = idx;
        }
    }

    List<List<Integer>> permutationIterative(int[] a){
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(a, 0));
        int n = a.length;
        List<List<Integer>> ret = new ArrayList<>();
        while(stack.size()>0){
            Node cur = stack.pop();

            if(cur.idx==n){
                List<Integer> t = new ArrayList<>();
                for (int i = 0; i < cur.c.length; i++) {
                    t.add(cur.c[i]);
                }
                ret.add(t);
                continue;
            }

            for (int i = cur.idx; i < n; i++) {
                int[] copy = Arrays.copyOf(cur.c, cur.c.length);
                swap(copy, cur.idx,i );
                stack.push(new Node(copy, cur.idx+1));
            }
        }
        return  ret;
    }

    List<List<Integer>> permutation(int[] a){
        List<List<Integer>> ret = new ArrayList<>();

        _permutation(a, 0, ret);

        return ret;
    }

    void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private void _permutation(int[] a, int i, List<List<Integer>> ret) {
        int n = a.length;
        if ( i==n){
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                t.add(a[j]);
            }
            ret.add(t);
            return;
        }

        for (int j = i; j < n; j++) {
            swap(a, i, j);
            _permutation(a, i + 1, ret);
            swap(a, i,j);
        }
    }

    public void testPermutation(){
        int[] a = {1,2,3,4};
        List<List<Integer>> r = permutation(a);
        assertEquals("[1, 2, 3, 4]", r.get(0).toString());
        //assertEquals("[4, 3, 2, 1]", r.get(23).toString());

        List<List<Integer>> ri = permutationIterative(a);
        assertEquals(r.get(0), ri.get(ri.size()-1));
        assertEquals(r.get(3), ri.get(ri.size()-4));

        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i).toString());
            System.out.println(ri.get(i).toString());
        }
    }

}
