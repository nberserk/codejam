package main.java.codejam.lib.impl;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/rotate-array/
 *
 */
public class RotateArray {
    static void rotate(int[] a, int k){
        LinkedList<Integer> l = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            l.add(a[i]);
        }

        for (int i = 0; i < k; i++) {
            Integer t = l.removeLast();
            l.addFirst(t);
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = l.get(i);
        }
    }

    static void rotateInplace(int[] a, int k){
        k %= a.length;
        if(k==0) return;

        int size = a.length;
        int p = a[0];
        int i=k;
        int last = a[size-k];
        while(i!=0){
            int t=a[i];
            a[i] = p;
            p=t;
            i = (i+k)%size;
        }
        a[0] = last;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        //rotate(a, 3);
        rotateInplace(a,3);
        assertEquals(5, a[0]);
    }
}
