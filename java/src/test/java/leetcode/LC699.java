package leetcode;

import java.util.*;

import static org.junit.Assert.assertEquals;

// coordinate compression
public class LC699 {

    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> r = new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();
        for (int[]p:positions){
            int l = p[0];
            int right = l+p[1]-1;
            set.add(l);
            set.add(right);
        }

        List<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);

        HashMap<Integer, Integer> index = new HashMap<>();
        int count=0;
        for (int t: sorted){
            index.put(t, count++);
        }

        int[] height = new int[count];
        int max=0;
        for (int[]p:positions){
            int L = index.get(p[0]);
            int R = index.get(p[0]+p[1]-1);
            int base = 0;
            for (int i = L; i <= R; i++) {
                base = Math.max(base, height[i]);
            }

            base += p[1];
            for (int i = L; i <= R; i++) {
                height[i] = base;
            }
            max = Math.max(max, base);
            r.add(max);
        }

        return r;
    }

    @org.junit.Test
    public void test(){
        assertEquals("[7, 16, 17]", fallingSquares(new int[][]{{9,7},{1,9},{3,1}}).toString());
        assertEquals("[1, 2, 4]", fallingSquares(new int[][]{{6,1},{9,2},{2,4}}).toString());
        assertEquals("[2, 5, 5]", fallingSquares(new int[][]{{1,2},{2,3},{6,1}}).toString());
    }
}
