package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class _656 {
    public List<Integer> cheapestJump(int[] A, int B) {
        int N = A.length;
        List<Integer> ret = new ArrayList<>();
        if(A[A.length-1]==-1) return ret;
        if(N==1){
            ret.add(1);
            return ret;
        }

        int[] dp = new int[N];
        int[] p = new int[N];
        int max = 1000000;
        Arrays.fill(p, -1);
        // dp(i) = 1 + dp(i+1) .. dp(i+B), else Integer.MAX
        for(int i=N-2;i>=0;i--){
            if(A[i]==-1)
                dp[i]=max;
            else{
                dp[i]=max;
                for (int j = i+1; j <= i+B; j++) {
                    if(j==N)break;
                    int next = A[i]+dp[j];
                    if(dp[i]>next){
                        dp[i]=next;
                        p[i]=j;
                    }
                }
            }
        }


        int cur=0;
        while(cur!=N-1){
            if(cur==-1){
                ret.clear();
                break;
            }
            ret.add(cur+1);
            cur=p[cur];
        }
        if(ret.size()>0)
            ret.add(N);
        return ret;
    }

    @Test
    public void test() {
        assertEquals("[1]", cheapestJump(new int[]{1}, 1).toString());
        assertEquals("[]", cheapestJump(new int[]{0,-1,-1,-1,-1,-1}, 5).toString());

        assertEquals("[1, 3, 5]", cheapestJump(new int[]{1,2,4,-1,2}, 2).toString());
        assertEquals("[]", cheapestJump(new int[]{1,2,4,-1,2}, 1).toString());
    }
}
