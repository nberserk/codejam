package codejam2018;//package codejam2018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class WaffleChoppers {


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

    static int getSum(int[] s, int start, int end){
        int ret = s[end];
        if(start>0){
            ret-=s[start-1];
        }
        return ret;
    }

    private static int solve(int R, int C, int H, int V, int[][] m) {
        int chocolate =0;

        int[][] sum = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(m[i][j]==1) chocolate++;
                if(j>0)
                    sum[i][j]=sum[i][j-1] + m[i][j];
            }
        }
        if(chocolate==0) return 0;
        if(chocolate%( (H+1)*(V+1)) !=0) return -1;

        int target = chocolate/(H+1);

        ArrayList<Integer> hsplit = new ArrayList<>();
        int count=0;
        for (int i = 0; i < R; i++) {
            count += getSum(sum[i],0, C-1);
            if(count>target) return -1;
            if(count ==target) {
                hsplit.add(i);
                count=0;
            }
        }

        if(hsplit.size()!=H) return -1;

        // first row
        target/=(V+1);
        count=0;
        ArrayList<Integer> vsplit = new ArrayList<>();
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < hsplit.get(0); j++) {
                count+=getSum(sum[j],j-1,j);
            }
            if(count > target) return -1;
            if(count==target){
                count==0;
                vsplit.add(i);
            }
        }

        if(vsplit.size()!=V) return -1;

        for (int i = 1; i < hsplit.size(); i++) {
            int sr = hsplit.get(i-1);
            int er = hsplit.get(i);

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
