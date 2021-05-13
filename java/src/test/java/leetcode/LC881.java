package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

public class LC881 {
    Random random = new Random();
    int max;
    int R,C;
    HashMap<Integer,Integer> map = new HashMap<>();

    public LC881(int n_rows, int n_cols) {
        R=n_rows; C=n_cols;
        max=n_rows*n_cols;
    }

    public int[] flip() {
        int target = random.nextInt(max--);
        int rt = map.getOrDefault(target,target);
        map.put(target, map.getOrDefault(max,max ));
        return new int[]{rt/C, rt%C};
    }

    public void reset() {
        map.clear();
        max=R*C;
    }

    @Test
    public void test_allSame() {
        LC881 r = new LC881(4,4);
        r.flip();
        r.flip();
        r.reset();
    }
}
