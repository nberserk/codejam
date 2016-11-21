package main.java.crackcode.dp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/21/16.
 */
public class LongestIncreasingSubsequence {

    int bisect(int[] a, int s, int e, int target){
        int l=s;
        int h=e;
        while(l<h){
            int m = (l+h)/2;
            if(a[m]<target)
                l=m+1;
            else h=m;
        }
        return l;
    }

    int lis(int[] a){
        int N = a.length;
        if(N==0) return 0;

        int[] dp = new int[N];
        int ret=1;
        int last=0;
        dp[0]=a[0];
        for(int i=0;i<N;i++){
            if(dp[last]<a[i]) {
                dp[++last]=a[i];
                ret = last+1;
                continue;
            }
            //System.out.println(last);
            int pos = bisect(dp, 0, last, a[i]);
            //System.out.println("pos:"+pos + ","+last);
            dp[pos]=a[i];
        }
        return ret;
    }

    int lis_dp(int[] a){
        int N = a.length;
        if(N==0) return 0;

        int[] dp = new int[N];
        int ret =1;
        for(int i=N-1;i>=0;i--){
            dp[i]=1;
            for(int j=i+1;j<N;j++){
                if(a[i]<a[j]){
                    dp[i] =Math.max(dp[i], dp[j]+1);
                }
            }
            ret=Math.max(ret, dp[i]);
        }

        return ret;
    }


    @Test
    public void test(){

        int[] a= {10, 9, 2, 5, 3, 7, 101, 18};
        assertEquals(4, lis(a));

    }
}
