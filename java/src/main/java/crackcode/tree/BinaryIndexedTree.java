package main.java.crackcode.tree;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by darren on 9/7/16.
 *
 * ex>
 *      7 : 111 -> 7
 *      6 : 110 -> 6,5
 *      4 : 100 -> 4,3,2,1
 *
 */
public class BinaryIndexedTree extends TestCase{
    int[] n;
    int[] tree;
    int size;

    public void init(int[] n){
        //this.n = n;

        size = n.length +1;
        this.n = new int[size];
        tree = new int[size];
        for (int i = 0; i < n.length; i++) {
            update(i,n[i]);
        }
    }

    void update(int i, int newval){
        int delta = newval - n[i];
        n[i] = newval;
        i=i+1;
        while(i<size){
            tree[i] += delta;
            i += (i &(-i));
        }
    }

    int sum(int i){
        i++;
        int sum=0;
        while(i>0){
            sum += tree[i];
            i-= i&(-i);
        }
        return sum;
    }

    @Test
    public void testRangeSum(){
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        BinaryIndexedTree t = new BinaryIndexedTree();
        t.init(a);
        assertEquals(t.sum(0), 1);
        assertEquals(t.sum(3), 10);
        assertEquals(t.sum(9), 55);
    }

}
