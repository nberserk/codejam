package codejam2018;//package codejam2018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class WaffleChoppers {
    int[][] m;
    int[][] tree;
    int row, col, size;

    public void init(int[][] matrix) {
        row = matrix.length;
        if (row > 0)
            col = matrix[0].length;
        size = col + 1;
        m = new int[row][col];
        tree = new int[row + 1][col + 1];
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                update(y, x, matrix[y][x]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int delta = val - m[row][col];
        int i = col+1;
        while(i<size){
            tree[row][i] += delta;
            i+= i& (-i);
        }
        m[row][col] = val;
    }

    public int sum (int row, int col){
        int sum=0;
        int i= col+1;
        while(i>0){
            sum += tree[row][i];
            i-=(i&(-i));
        }
        return sum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int s = 0;
        for (int y = row1; y <= row2; y++) {
            s+= sum(y, col2);
            s-= sum(y,col1-1);
        }
        return s;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int R = in.nextInt();
            int C = in.nextInt();
            int H = in.nextInt();
            int V = in.nextInt();
            int[][] m = new int[R][C];
            for (int j = 0; j < R; j++) {
                String temp = in.next();
//                System.out.println(temp);
//                System.out.flush();
                for (int k = 0; k < C; k++) {
                    m[j][k] = temp.charAt(k)=='.' ? 0:1;
                }
            }

            int r = solve(R,C,H,V,m);

            if(r==-1)
                System.out.println("Case #" + i + ": IMPOSSIBLE" );
            else
                System.out.println("Case #" + i + ": POSSIBLE" );
        }
    }

    private static int solve(int R, int C, int H, int V, int[][] m) {
        int chocolate =0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(m[i][j]==1) chocolate++;
            }
        }
        if(chocolate==0) return 0;

        if(chocolate%( (H+1)*(V+1)) !=0) return -1;

        WaffleChoppers waffleChoppers = new WaffleChoppers();
        waffleChoppers.init(m);
        int target = chocolate/(H+1);
        int[] h = new int[H+1];
        int hi=0;
        int startRow=0;
        for (int i = 0; i < R; i++) {
            int count = waffleChoppers.sumRegion(startRow,0,i,C-1);
            if(count==target){
                h[hi++]=i;
                startRow=i+1;
            }else if(count > target)
                return -1;
        }
        if(hi!=H+1) return -1;

        target /=(V+1);

        startRow=0;
        int[] v = new int[V+1];
        for (int i = 0; i < 1; i++) {
            int vi=0;
            int startCol=0;

            for (int j = 0; j < C; j++) {
                int count = waffleChoppers.sumRegion(startRow, startCol, h[i], j);
                if(count==target){
                    startCol=j+1;
                    v[vi++]=j;
                }else if(count > target)
                    return -1;
            }

            if(vi!=V+1)
                return -1;
            startRow=h[i]+1;
        }

        for (int i = 1; i < H+1; i++) {

            int startCol=0;

            for (int j = 0; j < V+1; j++) {
                int count = waffleChoppers.sumRegion(startRow, startCol, h[i], v[j]);
                if(count==target){
                    startCol=v[j]+1;
                }else
                    return -1;
            }
            startRow=h[i]+1;
        }

        return 0;
    }


//    @Test
//    public void test(){
//        int[][] m3 = new int[][]{
//                {0,0,1,1},
//                {0,0,1,1},
//                {1,1,0,0},
//                {1,1,0,0}
//
//        };
//        assertEquals(-1, solve(4,4,1,1, m3));
//
//        int[][] m = new int[][]{
//            {0,1,1,0,0,1},
//            {0,0,0,0,0,1},
//            {1,0,1,0,1,1}
//        };
//        assertEquals(0, solve(3,6,1,1, m));
//
//        int[][] m2 = new int[][]{
//                {0,0,0,0,0},
//                {0,0,0,0,0},
//                {0,0,0,0,0},
//                {0,0,0,0,0},
//                {0,0,0,0,0}
//        };
//        assertEquals(0, solve(4,5,1,1, m2));
//
//
//        int[][] m4 = new int[][]{
//                {0,1,0,1},
//                {1,0,1,0},
//                {0,1,0,1},
//        };
//        assertEquals(-1, solve(3,4,1,2, m4));
//
//    }


}
