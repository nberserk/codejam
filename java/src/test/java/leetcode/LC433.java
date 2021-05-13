package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 22/05/2017.
 */
public class LC433 {

    boolean canMutate(String a, String b){
        int N = a.length();
        if (N!=b.length()) return false;

        int diff=0;
        for (int i = 0; i < N; i++){
            if(a.charAt(i) != b.charAt(i)) diff++;
            if(diff>=2) return false;
        }
        return true;
    }

    public int minMutation(String start, String end, String[] bank) {
        int N = bank.length;
        int dest=-1;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<N+1;i++){
            graph.add(new ArrayList<Integer>());
        }

        for(int i=0;i<N;i++){
            if (end.equals(bank[i])){
                dest = i;
            }
            for (int j = i+1; j < N; j++){
                if(canMutate(bank[i], bank[j])){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        for (int i = 0; i < N; i++){
            if (canMutate(start, bank[i])){
                graph.get(N).add(i);
                graph.get(i).add(N);
            }
        }


        boolean[] v = new boolean[N+1];
        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[]{N,0});
        while (q.size()>0){
            int[] cur = q.pollFirst();
            if(v[cur[0]])continue;
            v[cur[0]] = true;

            if (cur[0]==dest&&cur[1]>0){
                return cur[1];
            }

            for (int next: graph.get(cur[0])){
                q.addLast(new int[]{next, cur[1]+1});
            }
        }

        return -1;
    }

    @Test
    public void test(){

        assertEquals(3, minMutation("AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"}));
        assertEquals(2, minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));
    }
}
