package leetcode;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class LC869 {

    public boolean reorderedPowerOf2(int N) {
        String s = String.valueOf(N);
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }
        long min = (long) Math.pow(10, s.length() - 1);
        long max = min * 10;
        long n = 1;
        while (n <= max) {
            if (n >= min) {
                String t = String.valueOf(n);
                HashMap<Character, Integer> map2 = new HashMap<>();
                for (char c : t.toCharArray()) {
                    map2.putIfAbsent(c, 0);
                    map2.put(c, map2.get(c) + 1);
                }
                if (map2.equals(map)) return true;
            }
            n *= 2;
        }

        return false;
    }

    @org.junit.Test
    public void test() {
        assertEquals(true, reorderedPowerOf2(46));
        assertEquals(true, reorderedPowerOf2(1));
        assertEquals(false, reorderedPowerOf2(24));
    }
}
