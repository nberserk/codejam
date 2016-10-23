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
    int K, N1, N2;
    int max2(int[] n1,int[] n2,int i,int j,int k, int[][][] dp, int[] cur, int[] answer){
        if(k==0) {
            boolean bigger =false;
            for (int l = 0; l < K; l++) {
                if(cur[i]>answer[i]){
                    bigger=true;
                    break;
                }
            }
            if(bigger){
                System.arraycopy(cur,0, answer, 0, cur.length);
            }
            return -1;
        }

        int N1 = n1.length;
        int N2 = n2.length;
        if(i>N1 || j>N2) return Integer.MIN_VALUE;
        if(i==N1 && j==N2) return Integer.MIN_VALUE;// due to k>0
        if(k>N1-i+N2-j) return Integer.MIN_VALUE;
        //System.out.println(i+","+ j+","+k);
        if(dp[i][j][k]!=0) return dp[i][j][k];

        int ret = -1;
        // if (i<N1)
        //     ret = Math.max(ret, max(n1,n2,i+1,j,k,number,dp));
        // if(j<N2)
        //     ret = Math.max(ret, max(n1,n2,i,j+1,k,number,dp) );

        // take j
        if(i==N1 ){
            cur[K-k]=n2[j];
            ret= Math.max(ret, n2[j]);
            max2(n1, n2, i, j + 1, k - 1, dp, cur, answer);
            ret = Math.max(ret, max2(n1, n2, i, j + 1, k, dp, cur, answer) );
        }else if ( j==N2){
            cur[K-k]=n1[i];
            ret =Math.max(ret,n1[i]);
            max2(n1, n2, i + 1, j, k - 1, dp, cur, answer);
            ret = Math.max(ret, max2(n1, n2, i + 1, j, k, dp, cur, answer));
        }else {
            cur[K-k] = n1[i];
            ret = Math.max(ret, n1[i]);
            max2(n1,n2,i+1,j,k-1, dp,cur, answer);
            cur[K-k] = n2[j];
            ret = Math.max(ret, n2[j]);
            max2(n1, n2, i, j + 1, k -1,dp,cur,answer ) ;
            ret = Math.max(ret, max2(n1, n2, i, j + 1, k, dp, cur, answer) );
            ret = Math.max(ret, max2(n1, n2, i + 1, j, k, dp, cur, answer));
        }
        dp[i][j][k]=ret;
        return ret;
    }


    // i,j : start index
    int max(int[] n1,int[] n2,int i,int j,int k, int number, int[][][] dp){
        if(k==0) return number;

        N1 = n1.length;
        N2 = n2.length;
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
        int[][][] dp= new int[nums1.length+1][nums2.length+1][k+1];
        //max(nums1, nums2, 0,0,k,0,dp);
        int[] cur = new int[k];
        int[] answer = new int[k];
        K=k;
        max2(nums1, nums2, 0,0,k,dp, cur, answer );
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    int[] select(int[] a, int k){
        int[] ret = new int[k];
        int N = a.length;
        int j=0;
        for (int i = 0; i < N; i++) { // 3,4,5,6 :k=2
            while(i>0 && a[i]>a[j-1] && N-i>=k-j) j--;
            ret[j++]=a[i];
        }
        return ret;
    }

    @Test
    public void test(){
        int[] a={3,4,6,5};
        int[] b = {9,1,2,5,8,3};
        assertEquals("[9, 8, 6, 5, 3]", Arrays.toString(maxNumber(a,b, 5)));

    }

}
