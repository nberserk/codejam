package leetcode;

import static org.junit.Assert.assertArrayEquals;

public class BeautifulArrangementII_667 {
    public int[] constructArray(int n, int k) {
        int[] a = new int[n];

        int s=1;
        int e=n;
        int i=0;
        for(;i<k;i++){
            if(i%2==0) a[i]=s++;
            else a[i]=e--;
        }
        for(;i<n;i++){
            if(k%2==0) a[i]=e--;
            else a[i]=s++;
        }
        return a;
    }

    @org.junit.Test
    public void test(){
        assertArrayEquals(new int[]{1,2,3}, constructArray(3,1));
        assertArrayEquals(new int[]{1,3,2}, constructArray(3,2));
        assertArrayEquals(new int[]{1,10,2,9,8,7,6,5,4,3}, constructArray(10,4));
        assertArrayEquals(new int[]{1,10,2,9,3,4,5,6,7,8}, constructArray(10,5));

    }
}
