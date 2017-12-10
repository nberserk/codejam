package leetcode;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class NetworkDelayTime_743 {

    public int networkDelayTime(int[][] times, int N, int K) {
        int[] n = new int[N+1];
        Arrays.fill(n,-1);
        HashMap<Integer, Set<int[]>> edge = new HashMap<>();
        for(int[] t: times){
            edge.putIfAbsent(t[0], new HashSet<>());
            edge.get(t[0]).add(t);
        }

        // HashSet<Integer> v = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> time = new Stack<>();
        stack.add(K);
        time.add(0);
        while(stack.size()>0){
            int cur = stack.pop();
            int t = time.pop();
            if(n[cur]!=-1 && n[cur]<=t) continue;
            n[cur]=t;
            Set<int[]> set = edge.get(cur);
            if(set==null) continue;
            for(int[] ti: set){
                //if(n[ti[1]]!=-1) continue;
                stack.add(ti[1]);
                time.add(t+ti[2]);
            }
        }
        int max=Integer.MIN_VALUE;
        for(int i=1;i<N+1;i++){
            if(n[i]==-1) return -1;
            max=Math.max(max, n[i]);
        }
        return max;
    }


    @org.junit.Test
    public void test(){
        assertEquals(2, networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}}, 4,2));

                
      
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
