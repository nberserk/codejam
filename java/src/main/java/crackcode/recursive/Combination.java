package main.java.crackcode.recursive;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by darren on 9/19/16.
 */
public class Combination extends TestCase {



    static List<List<Integer>> combination(int[] a, int k){
        List<List<Integer>> out = new ArrayList<>();

        Arrays.sort(a);
        combinationUtil(a, 0, k, out);
        return out;
    }

    static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i]=a[j];
        a[j]=t;
    }

    static private void combinationUtil(int[] a, int index, int k, List<List<Integer>> out) {
        if(index==k){
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                list.add(a[i]);
            }
            out.add(list);
            return;
        }

        for (int i = index; i < a.length; i++) {
            if (index>0 && a[index-1]>=a[i])
                continue;
            swap(a, index, i);
            combinationUtil(a, index+1, k, out);
            swap(a, index, i);

            // to remove duplicates
            while(i<a.length-1 && a[i] == a[i+1])
                i++;
        }
    }


    public void testCombination(){
        int[] a = {3,2,2,1};
//        int[] a = {3,2,1,4};
        List<List<Integer>> r = combination(a, 2);

        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i));
        }
    }
}
