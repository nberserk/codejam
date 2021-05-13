package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertArrayEquals;


public class LC851 {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int N = quiet.length;
        int[][] g = new int[N][N];

        for (int[] r : richer) {
            g[r[1]][r[0]] = 1;
        }

        int[] ret = new int[N];
        HashSet<Integer> rich = new HashSet<>();
        int[] cache = new int[N];
        Arrays.fill(cache, -1);
        for (int i = 0; i < N; i++) {
            ret[i] = dfs(g, i, rich, cache, quiet);
            rich.clear();
        }
        return ret;
    }

    int dfs(int[][] g, int s, HashSet<Integer> rich, int[] cache, int[] q) {
        int ret = s;
        if (rich.contains(s)) return ret;
        rich.add(s);
        if (cache[s] != -1) return cache[s];

        for (int i = 0; i < g[0].length; i++) {
            if (g[s][i] == 1) {
                int v = dfs(g, i, rich, cache, q);
                if (q[ret] > q[v])
                    ret = v;
            }
        }
        cache[s] = ret;
        return ret;
    }

    @Test(timeout = 1000)
    public void test() {

        assertArrayEquals(new int[]{5, 5, 2, 5, 4, 5, 6, 7},
                loudAndRich(new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}},
                        new int[]{3, 2, 5, 4, 6, 1, 7, 0}));


    }
}
