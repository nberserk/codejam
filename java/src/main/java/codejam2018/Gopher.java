package codejam2018;//package codejam2017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by darren on 5/17/17.
 */

public class Gopher {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int A = in.nextInt();

            solve(A, in);
        }
    }

    private static void solve(int A, Scanner in) {
        // x,y [2, 999]
        // A: 20 or 200
        int offset=10;

        int[][] cell = null;
        if(A==20){
            int row=4;
            int col=5;
            cell = new int[row][col];
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (cell[r][c]==1) continue;
                    while (true){
                        int deltaX=0;
                        int deltaY=0;
                        if(r==0) deltaY++;
                        if(c==0) deltaX++;
                        if(r==row-1) deltaY--;
                        if(c==col-1) deltaX--;
                        System.out.println(String.format("%d %d", offset+r+deltaY, offset+c+deltaX));
                        System.out.flush();
                        int or = in.nextInt();
                        int oc = in.nextInt();
                        if(or==0 && oc==0) return;
                        or-=offset;
                        oc-=offset;
                        if (or==-1){
                            System.out.println("error or=-1");
                        }
                        cell[or][oc]=1;
                        //System.out.println(String.format("%d %d",or,oc));
                        if(or==r && oc==c) break;
                    }
                }
            }
        }else if(A==200){
            int row=10;
            int col=20;
            cell = new int[row][col];
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (cell[r][c]==1) continue;
                    while (true){
                        int deltaX=0;
                        int deltaY=0;
                        if(r==0) deltaY++;
                        if(c==0) deltaX++;
                        if(r==row-1) deltaY--;
                        if(c==col-1) deltaX--;
                        System.out.println(String.format("%d %d", offset+r+deltaY, offset+c+deltaX));
                        System.out.flush();
                        int or = in.nextInt();
                        int oc = in.nextInt();
                        if(or==0 && oc==0) return;
                        or-=offset;
                        oc-=offset;
                        if (or==-1){
                            System.out.println("error or=-1");
                        }
                        cell[or][oc]=1;
                        //System.out.println(String.format("%d %d",or,oc));
                        if(or==r && oc==c) break;
                    }
                }
            }
        }



    }

    //@Test
    public void test(){
//        assertEquals(-1, solve(new int[]{5, 6, 8, 4, 3}, in));
//        assertEquals(1, solve(new int[]{8, 9, 7}, in));
    }


}
