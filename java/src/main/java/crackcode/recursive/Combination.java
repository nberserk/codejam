package main.java.crackcode.recursive;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 9/19/16.
 */
public class Combination {


    static List<List<Integer>> combination(int[] a, int k){
        List<List<Integer>> out = new ArrayList<>();

        Arrays.sort(a);
        LinkedList<Integer> temp = new LinkedList<>();
        combinationUtil(a, 0, k, temp, out);
        return out;
    }

    static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i]=a[j];
        a[j]=t;
    }

    static private void combinationUtil(int[] a, int start, int k, LinkedList<Integer> temp,  List<List<Integer>> out) {
        if(temp.size()==k){
            out.add(new ArrayList<Integer>(temp));
            return;
        }

        for (int i = start; i < a.length; i++) {
            if(i>start && a[i-1]==a[i]) continue; // remove duplicate
            if(temp.size()>0 && a[i]<temp.peekLast()) continue; // always take same or larger one
            temp.add(a[i]);
            combinationUtil(a, i + 1, k, temp, out);
            temp.removeLast();
        }
    }


    @Test
    public void test(){
        int[] a = {3,2,2,1};
//        int[] a = {3,2,1,4};
        List<List<Integer>> r = combination(a, 2);

        assertEquals(4, r.size());

        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i));
        }

        int[] b = {1,1,1};
        r= combination(b, 2);
        assertEquals(1, r.size());
        System.out.println("");
        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i));
        }
    }
}
