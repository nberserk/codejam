package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 *


 Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.

 If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

 Example 1
 Input:

 48
 Output:
 68
 Example 2
 Input:

 15
 Output:
 35

 https://leetcode.com/contest/leetcode-weekly-contest-37/problems/minimum-factorization/

 *
 */
public class MinimumFactorization_625 {
    public int smallestFactorization(int a) {
        if(a==1) return a;
        List<Integer> list = new ArrayList<>();

        int m=2;
        while(a>1){
            if(m>=10) return 0;
            if(a%m==0){
                list.add(m);
                a/=m;
            }
            else m++;
        }

        List<Integer> after = new ArrayList<>();
        int cur = -1;
        for (int i = list.size()-1; i >=0; i--) {
            int c = list.get(i);
            if(c>=5) after.add(c);
            else{
                if (cur==-1) cur = c;
                else if(cur*c<10) {
                    cur = cur*c;
                }
                else {
                    after.add(cur);
                    cur = c;
                }
            }
        }
        if(cur!=-1) after.add(cur);

        Collections.sort(after);

        int r = 0;
        for(int t: after){
            r*=10;
            r+=t;
        }
        return r;
    }


    @Test
    public void test(){
        assertEquals(355555588, smallestFactorization(3000000));

        assertEquals(88, smallestFactorization(64));
        assertEquals(26, smallestFactorization(12));
        assertEquals(68, smallestFactorization(48));
        assertEquals(35, smallestFactorization(15));


    }
}
