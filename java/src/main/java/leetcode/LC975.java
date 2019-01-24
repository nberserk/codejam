package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.TreeSet;

public class LC975 {

    public int oddEvenJumps(int[] A) {
        int N = A.length;
        int [][] dp = new int[N][2];
        dp[N-1][0] = dp[N-1][1]=1;

        TreeSet<Integer> set = new TreeSet<>( (i,j) -> A[i]==A[j]? i-j: A[i]-A[j] );
        TreeSet<Integer> setReverse = new TreeSet<>( (i,j) -> A[i]==A[j]? -i+j: A[i]-A[j] );
        set.add(N-1);
        setReverse.add(N-1);
        for(int i=N-2;i>=0;i--){
            Integer lo = set.ceiling(i);
            dp[i][0]= lo==null? 0 : dp[lo][1];
            Integer hi = setReverse.floor(i);
            if(hi!=null){
                dp[i][1] = dp[hi][0];
            }

            //
            set.add(i);
            setReverse.add(i);
        }

        int count=0;
        for(int i=0;i<N;i++){
            if(dp[i][0]==1) count++;
        }
        return count;
    }

    @Test
    public void test(){
        Assert.assertEquals(2 , oddEvenJumps(new int[]{10,13,12,14,15}));
        Assert.assertEquals(3 , oddEvenJumps(new int[]{5,1,3,4,2}));
        Assert.assertEquals(3 , oddEvenJumps(new int[]{2,3,1,1,4}));


    }
}
