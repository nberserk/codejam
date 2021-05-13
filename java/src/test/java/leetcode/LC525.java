package leetcode;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/22/17.
 *
 * https://leetcode.com/problems/contiguous-array/
 */


public class LC525 {
    // O(N)
    public int findMaxLength(int[] nums) {
        int N = nums.length;
        if(N<2) return 0;

        int[] s = new int[N];
        s[0]= (nums[0]==0?-1:1);
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=1;i<N;i++){
            s[i] = s[i-1] + (nums[i]==0?-1:1);
            map.put(s[i], i);
        }
        int max = 0;
        int sum=0;
        //System.out.println(Arrays.toString(s));
        //System.out.println(map);
        for(int i=0;i<N;i++){
            if(map.containsKey(sum)){
                int last = map.get(sum);

                max=Math.max(max, last-i+1);
            }
            sum=s[i];
        }
        return max;
    }

    // O(N^2)
    public int findMaxLength2(int[] nums) {
        int N = nums.length;
        if(N<2) return 0;

        int[][] dp = new int[N][N];
        for(int i=0;i<N;i++){
            if(nums[i]==0)
                dp[i][i] = -1;
            else dp[i][i]=1;
        }
        int max = 0;
        for(int len=1;len<N;len++){
            for(int i=0;i<N-len;i++){
                int s = i;
                int e = s+len;
                int cur = nums[e]==0?-1:1;
                dp[s][e] = dp[s][e-1] + cur;
                if(dp[s][e]==0){
                    int l = e-s+1;
                    max = Math.max(max, l);
                }
            }
        }
        return max;
    }

    @Test
    public void test(){
        assertEquals(2, findMaxLength(new int[]{1,0}));
        assertEquals(2, findMaxLength(new int[]{1,0,1}));
        assertEquals(8, findMaxLength(new int[]{0,0,1,0,1,0,1,1}));
    }
}
