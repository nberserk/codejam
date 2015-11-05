package codejam.lib.dp;


public class RotateMatrix {

    public static void main(String[] args) {

        int[][] a = new int[][]{
            {1,2,3,4},
            {4,5,6,7},
            {8,9,10,11},
            {12,13,14,15}
        };

        printMatrix(a);

        rotate(a);

        printMatrix(a);
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

        int [][] dir = { {0,1}, {1,0}, {0,-1}, {-1,0}};
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
            while(d<=3){
                x += dir[d][1];
                y += dir[d][0];
                if (x>=bound || x<sx || y<sy || y>=bound){
                    x -= dir[d][1];
                    y -= dir[d][0];
                    d++;
                    if (d>=4) break;
                    x += dir[d][1];
                    y += dir[d][0];
                }
                m[y][x] = b[py][px];
                py = y;
                px = x;
            }
            m[y][x] = b[y+1][x];
            printMatrix(m);
            System.out.println("\n");
        }

        //printMatrix(b);


    }
}
