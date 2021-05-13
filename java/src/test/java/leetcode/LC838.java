package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LC838 {
    public String pushDominoes(String dominoes) {
        char[] ch = dominoes.toCharArray();
        int ri = -1;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'L') {
                if (ri == -1) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (ch[j] != '.') {
                            break;
                        }
                        ch[j] = 'L';
                    }
                } else {
                    int l = ri;
                    int r = i;
                    while (l < r) {
                        ch[l++] = 'R';
                        ch[r--] = 'L';
                    }
                    ri = -1;
                }
            } else if (ch[i] == 'R') {
                if (ri == -1) {
                    ri = i;
                } else {
                    for (int j = ri + 1; j < i; j++) {
                        ch[j] = 'R';
                    }
                    ri = i;
                }
            }
        }
        if (ri != -1) {
            for (int j = ri + 1; j < ch.length; j++) {
                ch[j] = 'R';
            }
        }

        return String.valueOf(ch);
    }

    @Test(timeout = 1000)
    public void test() {
        assertEquals("LL.RR.LLRRLL..", pushDominoes(".L.R...LR..L.."));
        assertEquals("RR.L", pushDominoes("RR.L"));
    }
}
