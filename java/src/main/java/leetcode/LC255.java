package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by darren on 2/11/17.
 *
 *
 */
public class LC255 {
    public boolean verifyPreorder(int[] preorder) {
        int c = check(preorder, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return c<0? false: true;
    }

    private int check(int[] a, int start, int min, int max) {
        if (min<=a[start] && a[start]<=max){
            if (start == a.length-1)
                return 1;

            if (a[start]>a[start+1]){ // to the left
                int left = check(a, start+1, min, a[start]);
                if (left>0){
                    int right = check(a, start+1+left, a[start], max);
                    if (right>=0){
                        return left+right+1;
                    }
                }
                    return 1+left;
            }else{ // to the right
                int right=check(a,start+1, a[start], max);
                if (right>0)
                    return 1+right;
            }
        }

        return -1;
    }


    @Test
    public void test(){

        Assert.assertEquals(false, verifyPreorder(new int[]{5,2,6,1,3}));
        Assert.assertEquals(false, verifyPreorder(new int[]{5,2,1,3,6}));

    }
}
