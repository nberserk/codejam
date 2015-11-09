package codejam.lib.impl;


import codejam.lib.CheckUtil;

public class RotateMatrix {

    public static void main(String[] args) {

        int[][] a = new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };

        int[][] sol = new int[][]{
                {5, 1,2,3},
                {9,10,6,4},
                {13,11,7,8},
                {14,15,16,12}
        };

        //printMatrix(a);

        rotate(a);
        CheckUtil.check(true, equals(sol, a));

        int[][] t  = generateMatrix(3);
        printMatrix(t);
        rotate(t);
        printMatrix(t);

         t  = generateMatrix(6);
        printMatrix(t);
        rotate(t);
        printMatrix(t);


        //printMatrix(a);
    }

    private static int[][] generateMatrix(int n){
        int [][]m  = new int [n][n];

        int c = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = c;
                c++;
            }
        }

        return m;
    }

    private static boolean equals(int[][]expected, int[][] actual){
        int n = actual.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (expected[i][j] != actual[i][j] )
                    return false;
            }
        }
        return true;
    }

    private static void printMatrix(int[][] a) {
        for (int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.print(String.format("%3d", a[i][j]));
            }
            System.out.println("\n");
        }
    }

    public static void rotate(int[][] m){
        int r = m.length;
        int c = m[0].length;

        int [][] b = new int[r][c];
        for (int i = 0; i <r; i++) {
            for (int j = 0; j < c; j++) {
                b[i][j] = m[i][j];
            }
        }

        int [][] dir = { {0,1}, {1,0}, {0,-1}, {-1,0}}; // y,x
        int half = r/2;
        for(int i=0;i<half;i++){
            int x = i;
            int y =i;
            int sx = x;
            int sy = y;
            int px = x;
            int py = y;
            int d = 0;
            int bound = r-i;
            int left = i;
            int right = r-i;
            while(d<=3){
                x += dir[d][1];
                y += dir[d][0];
                if (x>=right || x<left || y<left || y>=right){
                    x = px;
                    y = py;
                    d++;
                    if (d>=4) break;
                    x += dir[d][1];
                    y += dir[d][0];
                }
                m[y][x] = b[py][px];
                //System.out.println(String.format("%d,%d=%d", x, y, b[py][px]));
                py = y;
                px = x;
            }
            m[y][x] = b[y+1][x];
            //printMatrix(m);
            //System.out.println("\n");
        }

        //printMatrix(b);


    }
}
