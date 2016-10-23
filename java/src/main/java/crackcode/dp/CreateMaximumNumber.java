package main.java.crackcode.dp;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/22/16.
 */
public class CreateMaximumNumber {
    static class Node{
        int val,idx;
        Node(int v, int i){
            val=v;
            idx=i;
        }
    }

    // i,j : start index
    int max(int[] n1,int[] n2,int i,int j,int k, int number, int[][][] dp){
        if(k==0) return number;

        int N1 = n1.length;
        int N2 = n2.length;
        if(i>N1 || j>N2) return Integer.MIN_VALUE;
        if(i==N1 && j==N2) return Integer.MIN_VALUE;
        if(k>N1-i+N2-j) return Integer.MIN_VALUE;
        //System.out.println(i+","+ j+","+k);
        //if(dp[i][j][k]!=0) return dp[i][j][k];

        int ret = Integer.MIN_VALUE;
        // if (i<N1)
        //     ret = Math.max(ret, max(n1,n2,i+1,j,k,number,dp));
        // if(j<N2)
        //     ret = Math.max(ret, max(n1,n2,i,j+1,k,number,dp) );

        // take j
        if(i==N1 ){
            ret= Math.max(ret, max(n1,n2,i,j+1,k-1,number*10+n2[j], dp));
            ret = Math.max(ret, max(n1, n2, i, j + 1, k, number, dp) );
        }else if ( j==N2){
            ret =Math.max(ret, max(n1,n2,i+1,j,k-1,number*10+n1[i], dp));
            ret = Math.max(ret, max(n1, n2, i + 1, j, k, number, dp));
        }else {
            ret = Math.max(ret, max(n1,n2,i+1,j,k-1, number*10+n1[i], dp));
            ret = Math.max(ret, max(n1,n2,i,j+1,k-1, number*10+n2[j],dp ) );
            ret = Math.max(ret, max(n1, n2, i, j + 1, k, number, dp) );
            ret = Math.max(ret, max(n1, n2, i + 1, j, k, number, dp));
        }
        //dp[i][j][k]=ret;
        System.out.println(i+","+j+","+k+"="+ret);
        return ret;
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int N1 = nums1.length;
        int N2 = nums2.length;

        // i: select #i in first array
        int[] ret = new int[k];
        for (int i = Math.max(0, k-N2); i <= (k>N1 ? N1:k ) ; i++) { // N1:2, N2:5 , k:3
            int[] first = select(nums1, i);
            int[] second = select(nums2, k-i);
            int[] merged = merge(first, second);
            if(greater(merged,0,ret,0))
                ret=merged;
        }
        return ret;
    }

    // is n1[i] greater than n2[j]?
    boolean greater(int[] n1, int i, int[] n2, int j){
        while(i<n1.length && j<n2.length && n1[i]==n2[j]){
            i++;
            j++;
        }
        return j==n2.length || (i<n1.length&& n1[i] > n2[j]);
    }

    private int[] merge(int[] first, int[] second) {
        int N1 =first.length;
        int N2 = second.length;
        int len = N1+N2;
        int[] ret = new int[len];
        int i=0;
        int j=0;
        int idx =0;
        for (int k = 0; k < N1 + N2; k++) {
            ret[k] = greater(first,i, second,j) ? first[i++] : second[j++];
        }
        return ret;
    }

    int[] select(int[] a, int k){
        int[] ret = new int[k];
        int N = a.length;
        int j=0;
        for (int i = 0; i < N; i++) {
            while(j>0 && a[i]>ret[j-1] && N-i>k-j) // if N-j = k-j,  You have to select all remaining element. so can't decrease j
                j--;

            if(j<k)
                ret[j++]=a[i];
        }
        return ret;
    }

    @Test
    public void test(){
        int[] a={3,4,6,5};
        int[] b = {9,1,2,5,8,3};
        assertEquals("[6]", Arrays.toString(select(a, 1) ));
        assertEquals("[6, 5]", Arrays.toString(select(a, 2) ));
        assertEquals("[9, 8, 3]", Arrays.toString(select(b, 3) ));

        assertEquals("[9, 8, 6, 5, 3]", Arrays.toString(maxNumber(a,b, 5)));

        int[] a2={3,9};
        int[] b2={8,9};
        assertEquals("[9, 8, 9]", Arrays.toString(maxNumber(a2,b2, 3)));

        int[] a3={6,7};
        int[] b3={6,0,4};
        assertEquals("[6, 7, 6, 0, 4]", Arrays.toString(maxNumber(a3,b3, 5)));
    }

}
