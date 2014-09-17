package crackcode;


public class Search {

    public static void main(String[] args) {

        int[] a = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
        int[] b = { 3, 4, 5, 7, 10, 14, 15, 16, 19, 20, 25, 1 };
        int idx = findKey(a, 0, a.length - 1, 7);
        System.out.println(idx);
        idx = findKey(b, 0, b.length - 1, 7);
        System.out.println(idx);

        int[] c = { 1, 1, 1, 1, 2, 3, 3, 3, 3, 3 };
        idx = binarySearch(c, 0, c.length - 1, 8);
        System.out.println(idx);

        String[] strings = { "at", "", "", "", "ball", "", "", "car", "", "", "dad", "", "" };
        idx = customBinarySearch(strings, 0, strings.length - 1, "ball");
        System.out.println(idx);

        // matrix search
        int[][] m = new int[4][4];
        int v = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m[i][j] = ++v;
            }
        }
        boolean found = findKeyInMatrix(m, 4, 4, 100);
        System.out.println(found);

        //
    }

    private static boolean findKeyInMatrix(int[][] m, int row, int col, int key) {
        int r = 0;
        int c = col - 1;

        while (r < row && c >= 0) {
            if (m[r][c] > key) {
                c--;
            } else if (m[r][c] < key)
                r++;
            else
                return true;
        }

        return false;
    }


    private static int customBinarySearch(String[] strings, int start, int end, String key) {

        while (start <= end) {
            int m = (start + end) / 2;
            int lm = m;
            while ("".equals(strings[lm]) && start <= lm) {
                lm--;
            }
            if (lm < start) {
                start = m + 1;
            } else {
                int compare = key.compareTo(strings[lm]);
                if (compare > 0) {
                    start = m + 1;
                } else if (compare < 0) {
                    end = lm - 1;
                } else
                    return lm;
            }
        }

        return -1;
    }

    static int binarySearch(int[] a, int s, int e, int key) {
        while (s <= e) {
            int m = (s + e) / 2;
            if (a[m] > key) {
                e = m - 1;
            } else if (a[m] < key) {
                s = m + 1;
            } else
                return m;
        }
        return -1;
    }

    static int findKey(int[] a, int s, int e, int key) {
        if (s==e) {
            if (a[s]==key) {
                return s;
            }else
                return -1;
        }

        int m = (s+e)/2;
        if (isInRange(a, s, m, key)) {
            if (a[s] <= a[m]) {
                // return Arrays.binarySearch(a, s, m, key);
                return binarySearch(a, s, e, key);
            }
        }

        if (isInRange(a, m + 1, e, key)) {
            if (a[m + 1] <= a[e]) {
                // return Arrays.binarySearch(a, m + 1, e, key);
                return binarySearch(a, s, e, key);
            }
        }
        return -1;
    }

    static boolean isInRange(int[] a, int s, int e, int key) {
        if (a[s] <= a[e]) {
            if (a[s] <= key && key <= a[e]) {
                return true;
            }
        } else {
            if (a[s] >= key || a[e] >= key) {
                return true;
            }
        }

        return false;
    }

}
