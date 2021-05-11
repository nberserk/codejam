package leetcode;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class SimilarStringGroups_839 {

    boolean match(String a, String b) {
        int wrong = 0;
        char aa = 0, bb = 0;
        boolean ret = false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                wrong++;
                if (wrong > 2) {
                    return false;
                }
                if (wrong == 2) {
                    if (a.charAt(i) == bb && b.charAt(i) == aa) {
                        ret = true;
                    } else {
                        return false;
                    }
                } else {
                    aa = a.charAt(i);
                    bb = b.charAt(i);
                }
            }
        }
        if (wrong == 0) {
            HashSet<Character> set = new HashSet<>();
            for (int i = 0; i < a.length(); i++) {
                set.add(a.charAt(i));
            }
            if (set.size() != a.length()) {
                return true;
            } else {
                return false;
            }
        }
        return ret;
    }

    int parent(int[] g, int i) {
        int org = i;
        while (i != g[i]) {
            i = g[i];
        }
        if (org != i) {
            g[org] = i;
        }
        return i;
    }

    public int numSimilarGroups(String[] A) {
        int N = A.length;
        int[] group = new int[N];
        for (int i = 0; i < N; i++) {
            group[i] = i; // 0,1,2,3
        }

        for (int i = 0; i < N; i++) {
            int p = parent(group, i); // 0
            for (int j = i + 1; j < N; j++) {
                int q = parent(group, j); // 2
                if (q != p) {
                    if (match(A[i], A[j])) {
                        group[q] = p; // 0,0,0,3
                    }
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(parent(group, i));
        }
        return set.size();
    }

    @Test(timeout = 1000)
    public void test() {
        assertEquals(1, numSimilarGroups(new String[] {"aaaa", "aaaa", "aaaa", "aaaa"}));
        assertEquals(2, numSimilarGroups(new String[] {"tars", "rats", "arts", "star"}));
        assertEquals(5, numSimilarGroups(new String[] {"kccomwcgcs", "socgcmcwkc", "sgckwcmcoc", "coswcmcgkc",
            "cowkccmsgc", "cosgmccwkc", "sgmkwcccoc", "coswmccgkc", "kowcccmsgc", "kgcomwcccs"}));

    }
}
