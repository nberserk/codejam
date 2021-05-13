package leetcode;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class LC774 {
    static class Node {
        double d;
        double org;
        int k;
    }

    public double minmaxGasDist_1st(int[] stations, int K) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(10, (a, b) -> a.d == b.d ? b.k - a.k : Double.compare(b.d, a.d));
        for (int i = 0; i < stations.length - 1; i++) {
            Node n = new Node();
            n.org = stations[i + 1] - stations[i];
            n.d = n.org;
            n.k = 1;
            pq.add(n);
        }

        while (K > 0) {
            Node n = pq.poll();
            do {
                n.k++;
                n.d = n.org / (double) n.k;
                K--;
            } while (K > 0 && n.d > pq.peek().d);
            pq.add(n);
        }
        return pq.poll().d;
    }

    public double minmaxGasDist(int[] stations, int K) {
        double lo = 0;
        double hi = stations[stations.length - 1] - stations[0];

        while (hi - lo > 0.000001) {
            double mid = (lo + hi) / 2.0;
            if (can(stations, K, mid)) {
                hi = mid;
            } else
                lo = mid;
        }
        return lo;
    }

    private boolean can(int[] stations, int K, double mid) {
        for (int i = 0; i < stations.length - 1; i++) {
            double org = stations[i + 1] - stations[i];
            int required = (int) Math.ceil(org / mid) - 1;
            K -= required;
            if (K < 0) return false;
        }
        return true;
    }

    @Test
    public void test() {
        assertEquals(0.5, minmaxGasDist(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9), 0.000001);
        assertEquals(9.66667, minmaxGasDist(new int[]{10, 19, 25, 27, 56, 63, 70, 87, 96, 97}, 3), 0.00001);
    }
}
