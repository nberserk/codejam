package codejam.lib;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 *
 * from : https://www.careercup.com/question?id=5745302387556352
 *
 * Give an array A of n integers where 1 <= a[i] <= K.
 * Find out the length of the shortest sequence that can be constructed out of numbers 1, 2, .. k that is NOT a subsequence of A.
 * eg. A = [4, 2, 1, 2, 3, 3, 2, 4, 1], K = 4
 * All single digits appears.
 * Each of the 16 double digit sequences, (1,1), (1, 2), (1, 3), (1, 4), (2, 1), (2, 2) ... appears.
 * Because (1, 1, 2) doesn't appear, return 3.
 */
public class ShortestSequenceNotInArray {

    int shortestSequcen(int[] a, int K){
        HashSet<Integer> set = new HashSet<>();
        int ret=0;
        for (int i: a){
            set.add(i);
            if(set.size()==K){
                ret++;
                set.clear();
            }
        }
        return ret+1;
    }

    @Test
    public void test(){
        assertEquals(3, shortestSequcen(new int[]{4, 2, 1, 2, 3, 3, 2, 4, 1}, 4));
    }
}
