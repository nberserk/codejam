package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class LC465 {

    int MOD = 100;
    int minTransfers(int[][] transactions) {

        Map<Integer, Long> person = new HashMap<>();
        for (int[] t: transactions){
            long from = person.getOrDefault(t[0], 0L);
            long to = person.getOrDefault(t[1], 0L);

            person.put(t[0], from-t[2]);
            person.put(t[1], to+t[2]);
        }

        List<Long> list = new ArrayList<>();
        for (Long l: person.values()){
            if(l!=0) list.add(l);
        }

        return helper(list, 0, 0);
    }

    private int helper(List<Long> list, int pos, int count) {
        while(pos< list.size()&& list.get(pos)==0) pos++;
        int res = Integer.MAX_VALUE;

        HashSet<Long> visited = new HashSet<>();
        for (int i = pos+1; i < list.size(); i++) {
            long v = list.get(i);
            if (!visited.contains(v) && list.get(pos)* list.get(i)<0){
                list.set(i, v+list.get(pos));
                res = Math.min(res, helper(list, pos+1, count+1));
                list.set(i, v);
                visited.add(v);
            }
        }
        return res == Integer.MAX_VALUE ? count : res;
    }

    @Test
    public void test(){
        assertEquals(2, minTransfers(new int[][]{{0,1,10},{2,0,5}}));
        assertEquals(1, minTransfers(new int[][]{{0,1,10},{1,0,1}, {1,2,5}, {2,0,5}}));
        assertEquals(1, minTransfers(new int[][]{{0,1,1},{0,2,1}, {1,3,1}, {2,3,1}}));
    }
}
