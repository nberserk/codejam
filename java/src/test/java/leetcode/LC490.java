package leetcode;


import java.util.HashSet;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class LC490 {

    int mod = 1000;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int R = maze.length;
        int C = maze[0].length;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Stack<Integer> s = new Stack<>();
        s.add(start[0] * mod + start[1]);
        HashSet<Integer> visited = new HashSet<>();
        while (s.size() > 0) {
            int cur = s.pop();
            if (visited.contains(cur)) continue;
            visited.add(cur);
            int y = cur / mod;
            int x = cur % mod;
            if (destination[0] == y && destination[1] == x) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if (nx < 0 || ny < 0 || nx >= C || ny >= R || maze[ny][nx] == 1) continue;
                while (true) {
                    nx += dir[i][0];
                    ny += dir[i][1];
                    if (nx < 0 || ny < 0 || nx >= C || ny >= R || maze[ny][nx] == 1) break;
                }
                nx -= dir[i][0];
                ny -= dir[i][1];
                s.push(ny * mod + nx);
            }
        }
        return false;
    }

    @org.junit.Test
    public void test() {
        assertEquals(true, hasPath(new int[][]{{0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}}, new int[]{0, 4}, new int[]{4, 4}));
    }
}
