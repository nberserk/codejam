package leetcode;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class MininumNumberOfRefuelingStops_871 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int refuel = startFuel;
        int ret = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, (a, b) -> -a + b);
        for (int i = 0; i < stations.length; i++) {
            if (refuel >= target) break;
            if (stations[i][0] <= refuel) {
                pq.add(stations[i][1]);
            } else {
                while (pq.size() > 0 && stations[i][0] > refuel) {
                    refuel += pq.poll();
                    ret++;
                }
                if (stations[i][0] > refuel) return -1;
                else pq.add(stations[i][1]);
            }
        }
        while (refuel < target && pq.size() > 0) {
            refuel += pq.poll();
            ret++;
        }
        if (refuel < target) ret = -1;
        return ret;
    }

    @org.junit.Test
    public void test() {

        assertEquals(2, minRefuelStops(100, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
        assertEquals(-1, minRefuelStops(100, 1, new int[][]{{10, 100}}));
        assertEquals(0, minRefuelStops(1, 1, new int[][]{{}}));

    }
}
