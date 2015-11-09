package crackcode;

import codejam.lib.CheckUtil;

// http://www.careercup.com/question?id=5709186067333120
public class Words {

    public static void main(String[] args) {

        char [][] m = {
 { 'S', 'N', 'B', 'S', 'N' },
 {'B', 'A', 'K', 'E', 'A'},
 {'B', 'K', 'B', 'B', 'K'},
 {'S', 'E', 'B', 'S', 'E'}
        };

        char[] t = "SNAKES".toCharArray();

        CheckUtil.check(2, findWord(m, t, 0,0,0));

        int count = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                count += findWord(m, t, j, i, 0);
            }
        }
        CheckUtil.check(3, count);
    }

    static int findWord(char[][] m, char[] t, int x, int y, int idx) {
        if (m[y][x] != t[idx])
            return 0;

        if (idx == t.length - 1)
            return 1;

        int[][] d = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

        int ret = 0;
        int width = m[0].length;
        int height = m.length;
        for (int i = 0; i < d.length; i++) {
            int nx = x + d[i][0];
            int ny = y + d[i][1];
            if (nx < 0 || ny < 0 || nx >= width || ny >= height)
                continue;

            ret += findWord(m, t, nx, ny, idx + 1);
        }
        return ret;
    }

}
