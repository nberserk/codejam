package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LC865 {
    int K = 0, R, C;

    public int shortestPathAllKeys(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        int s = 0;
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[j].length(); i++) {
                char c = grid[j].charAt(i);
                if (c == '@') {
                    s = j * 100 + i;
                } else if (c >= 'a' && c <= 'f') {
                    K++;
                }
            }
        }
        int r = find(grid, new HashSet<>(), s, 0);
        if (r == Integer.MAX_VALUE) r = -1;
        return r;
    }

    private int find(String[] grid, Set<Integer> keys, int start, int cost) {
        if (keys.size() == K) return cost;

        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < K; i++) {
            if (keys.contains(i)) continue;
            Node v = bfs(grid, keys, start, i);
            if (v.cost == Integer.MAX_VALUE) continue;
            int vv = find(grid, v.keys, v.p, v.cost + cost);
            ret = Math.min(ret, vv);
        }
        return ret;
    }

    static class Node {
        int p, cost;
        Set<Integer> keys = new HashSet<>();

        Node(int p, int cost, Set<Integer> k) {
            this.p = p;
            this.cost = cost;
            for (Integer i : k) {
                keys.add(i);
            }
        }
    }

    private Node bfs(String[] grid, Set<Integer> keys, int start, int target) {
        LinkedList<Node> queue = new LinkedList<>();
        Node s = new Node(start, 0, keys);
        queue.add(s);
        HashSet<Integer> visited = new HashSet<>();
        int[][] d = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (queue.size() > 0) {
            Node cur = queue.pollFirst();
            if (visited.contains(cur.p)) continue;
            visited.add(cur.p);
            int x = cur.p % 100;
            int y = cur.p / 100;
            char c = grid[y].charAt(x);
            if (c == '#') continue;
            if (c >= 'a' && c <= 'f') {
                cur.keys.add(c - 'a');
                if (c - 'a' == target) {
                    return cur;
                }
            } else if (c >= 'A' && c <= 'F') {
                if (!cur.keys.contains(c - 'A')) continue;
            }


            for (int i = 0; i < 4; i++) {
                int nx = x + d[i][0];
                int ny = y + d[i][1];
                if (nx < 0 || ny < 0 || nx >= C || ny >= R) continue;
                Node next = new Node(ny * 100 + nx, cur.cost + 1, cur.keys);
                queue.addLast(next);
            }
        }

        // can't get to target
        s.cost = Integer.MAX_VALUE;
        return s;
    }


    @Test
    public void test() {
        Assert.assertEquals(3, shortestPathAllKeys(new String[]{"b", "A", "a", "@"}));
        Assert.assertEquals(6, shortestPathAllKeys(new String[]{"@..aA", "..B#.", "....b"}));
        Assert.assertEquals(8, shortestPathAllKeys(new String[]{"@.a.#", "###.#", "b.A.B"}));
    }
}
