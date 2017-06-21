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
        int[] cnt = new int[8];

        int m=2;
        while(a>1){
            if(m>=10) return 0;
            if(a%m==0){
                cnt[m]++;
                a/=m;
            }
            else m++;
        }

        List<Integer> after = new ArrayList<>();
        int[] key = {2,3,5,7};
        for (int i = 0; i < cnt[5] ; i++) {
            after.add(5);
        }
        for (int i = 0; i < cnt[7] ; i++) {
            after.add(7);
        }

        int multiple = cnt[2]/3;
        cnt[2]%=3;
        for (int i = 0; i < multiple; i++) {
            after.add(8);
        }
        multiple = cnt[3]/2;
        for (int i = 0; i < multiple; i++) {
            after.add(9);
        }
        cnt[3]%=2;

        if(cnt[3]==0) {
            switch (cnt[2]){
                case 1:
                    after.add(2);
                    break;
                case 2:
                    after.add(4);
                    break;
            }
        }else{
            switch (cnt[2]){
                case 0:
                    after.add(3);
                    break;
                case 1:
                    after.add(6);
                    break;
                case 2:
                    after.add(2);
                    after.add(6);
                    break;
            }
        }

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
