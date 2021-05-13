package leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LC874 {

    int[][] dirOffset = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public int robotSim(int[] commands, int[][] obstacles) {
        HashMap<Integer, List<Integer>> map = new HashMap<>(); // by row
        for (int[] o : obstacles) {
            if (!map.containsKey(o[1]))
                map.put(o[1], new ArrayList<>());
            map.get(o[1]).add(o[0]);
        }
        int x = 0;
        int y = 0;
        int dir = 0;
        int ret = 0;
        for (int i : commands) {
            if (i == -2) {
                dir++;
                dir = dir % 4;
            } else if (i == -1) {
                dir--;
                if (dir < 0) dir += 4;
            } else {
                while (i > 0) {
                    x += dirOffset[dir][0];
                    y += dirOffset[dir][1];
                    if (map.containsKey(y) && map.get(y).indexOf(x) != -1) {
                        x -= dirOffset[dir][0];
                        y -= dirOffset[dir][1];
                        break;
                    }
                    i--;
                }
                ret = Math.max(ret, x * x + y * y);
            }
        }
        return ret;
    }

    @org.junit.Test
    public void test() {
        assertEquals(25, robotSim(new int[]{4, -1, 3}, new int[][]{}));
        assertEquals(65, robotSim(new int[]{4, -1, 4, -2, 4}, new int[][]{{2, 4}}));
    }
}
