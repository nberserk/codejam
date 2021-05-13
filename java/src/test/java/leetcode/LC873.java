package leetcode;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LC873 {

    int find(int[] A, int start, List<Integer> sel) {
        int target = -1;
        int N = A.length;
        if (sel.size() == 2) {
            int ret = 0;
            target = sel.get(0) + sel.get(1);
            int offset = sel.get(0);
            for (int i = start; i < N; i++) {
                if (A[i] == target) {
                    if (ret == 0) ret = 3;
                    else ret++;
                    int nextOffset = target - offset;
                    target = target - offset + target;
                    offset = nextOffset;
                }
            }
            return ret;
        }

        int ret = 0;
        for (int i = start; i < N; i++) {
            sel.add(A[i]);
            int v = find(A, i + 1, sel);

            sel.remove(sel.size() - 1);
            ret = Math.max(ret, v);
        }
        return ret;
    }

    public int lenLongestFibSubseq(int[] A) {
        List<Integer> list = new ArrayList<>();
        return find(A, 0, list);
    }

    @org.junit.Test
    public void test() {
        assertEquals(5, lenLongestFibSubseq(new int[]{2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50}));

    }
}
