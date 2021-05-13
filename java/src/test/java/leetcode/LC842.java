package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LC842 {

    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> r = split(S, 0, new LinkedList<>());
        if (r == null)
            r = new ArrayList<>();
        return r;
    }

    private List<Integer> split(String s, int start, LinkedList<Integer> num) {
        List<Integer> ret = null;

        if (start == s.length()) {
            if (num.size() > 2)
                return new ArrayList<>(num);
            else
                return ret;
        }

        if (s.charAt(start) == '0') {
            if (num.size() >= 2 && 0 != num.get(num.size() - 1) + num.get(num.size() - 2))
                return null;
            num.addLast(0);
            ret = split(s, start + 1, num);
            num.removeLast();
            return ret;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            if (i - start >= 12) break;
            String v = s.substring(start, i);
            long temp = Long.valueOf(v);
            if (temp > Integer.MAX_VALUE) continue;
            if (num.size() >= 2 && temp != num.get(num.size() - 1) + num.get(num.size() - 2)) continue;
            num.addLast((int) temp);
            ret = split(s, i, num);
            num.removeLast();
            if (ret != null) return ret;
        }
        return ret;
    }


    @Test(timeout = 1000)
    public void test() {
        assertEquals(0, splitIntoFibonacci("0123").size());

        assertEquals(4, splitIntoFibonacci("0000").size());
        assertEquals(4, splitIntoFibonacci("1011").size());
        assertEquals(0, splitIntoFibonacci("214748364721474836422147483641").size());
        assertEquals(0, splitIntoFibonacci("112358130").size());
        assertEquals(4, splitIntoFibonacci("1101111").size());
        assertEquals(7, splitIntoFibonacci("11235813").size());
        assertEquals(3, splitIntoFibonacci("123456579").size());
    }
}
