package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC255 {
    public boolean verifyPreorder(int[] preorder) {
        if(preorder.length==0) return true;
        int c = traverse(preorder,0,Integer.MIN_VALUE, Integer.MAX_VALUE);
        return c==-1 ? false: true;
    }

    int traverse(int[] a, int i, int min, int max){
        int c=a[i];
        if(c<min || c>max) return -1;
        int N=a.length;
        if(i==N-1) return 1;
        int left=0;
        int right=0;
        if(a[i+1]<c){ //c=1, a[i+1]=3
            left=traverse(a,i+1, min, c); // i=2, max=2
            if(left<0) return -1;
        }
        if(i+1+left<N && a[i+1+left]>=min && a[i+1+left]<=max && a[i+1+left]>c){
            right=traverse(a,i+1+left,c,max); // i=3, min=1,max=2
            if(right<0) return -1;
        }

        return 1+left+right;
    }

    @Test
    public void test(){
        assertEquals(false, verifyPreorder(new int[]{5,2,6,1,3}));
        assertEquals(true, verifyPreorder(new int[]{5,2,1,3,6}));
    }
}
