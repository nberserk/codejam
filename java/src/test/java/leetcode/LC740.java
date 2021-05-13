package leetcode;

import java.util.Arrays;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class LC740 {

    public int deleteAndEarn(int[] nums) {
        int N =10001;
        int[] n = new int[10001];
        for (int i: nums){
            n[i]+=i;
        }
        int[][] dp = new int[N][2]; // previously used
        for (int i = N-1; i >=0; i--) {

            dp[i][0] = Math.max( n[i]+ (i+1<N? dp[i+1][1]:0), i+1<N ? dp[i+1][0]:0 );
            dp[i][1] = i+1<N ? dp[i+1][0]:0;
        }

        return dp[1][0];
    }

    public int deleteAndEarn_1(int[] nums) {
        TreeMap<Integer, Integer> map=new TreeMap<>();
        for (int i: nums){
            map.putIfAbsent(i,0);
            map.put(i, map.get(i)+1);
        }
        int N = map.size();
        int[][] dp = new int [N][N+1];
        for (int[] d: dp)
            Arrays.fill(d, -1);

        int[] sortedKey = new int[N];
        int i=0;
        for (int k: map.keySet()){
            sortedKey[i++]=k;
        }
        return max_1(map, sortedKey, 0, -1, dp);
    }

    int max_1(TreeMap<Integer, Integer> map, int[] sortedKey, int start, int prevIdx, int[][] dp) {
        int N = map.size();
        if(start>=N) return 0;

        if (dp[start][prevIdx+1]!=-1)
            return dp[start][prevIdx+1];

        int ret=0;
        if (prevIdx!=-1 && sortedKey[prevIdx]+1 ==sortedKey[start])
            ret = max_1(map, sortedKey, start+1, prevIdx, dp);
        else{
            ret = Math.max( max_1(map, sortedKey, start+1, start, dp)+sortedKey[start]*map.get(sortedKey[start]),
                    max_1(map, sortedKey, start+1, prevIdx, dp) );
        }

        dp[start][prevIdx+1] = ret;
        return ret;
    }

    @org.junit.Test
    public void test(){

        assertEquals(9, deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
        assertEquals(6, deleteAndEarn(new int[]{3,2,4}));
    }
}
