package main.java.crackcode.recursive;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darren on 9/13/16.
 */
public class Permutation extends TestCase {


    List<List<Integer>> permutationIterative(int[] a){
        Stack<>

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

        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i).toString());
        }
    }

}
