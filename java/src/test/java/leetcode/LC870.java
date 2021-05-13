package leetcode;

import java.util.TreeMap;

import static org.junit.Assert.assertArrayEquals;

public class LC870 {
    public int[] advantageCount(int[] A, int[] B) {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (int i : A)
            tree.put(i, tree.getOrDefault(i, 0) + 1);

        for (int i = 0; i < B.length; i++) {
            Integer v = tree.higherKey(B[i]);
            if (v == null) v = tree.firstKey();
            A[i] = v;
            tree.put(v, tree.get(v) - 1);
            if (tree.get(v) == 0) tree.remove(v);

        }
        return A;
    }

    @org.junit.Test
    public void test() {

        assertArrayEquals(new int[]{2, 11, 7, 15}, advantageCount(new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11}));

    }
}
