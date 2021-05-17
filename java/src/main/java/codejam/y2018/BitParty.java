package codejam.y2018;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 5/17/17.
 */

public class BitParty {



    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int R = in.nextInt();
            int B = in.nextInt();
            int C = in.nextInt();
            int[][] m = new int[C][3];
            for (int j = 0; j < C; j++) {
                m[j][0] = in.nextInt();
                m[j][1] = in.nextInt();
                m[j][2] = in.nextInt();
            }

            long r = solve(R,B, m);
            System.out.println("Case #" + i + ": " + r);
        }
    }

    static boolean can(int R, int B, int[][] cm, long T){
        int C = cm.length;
        long[] capacity = new long[C];
        for (int i = 0; i < C; i++) {
            capacity[i] = Math.max(0, Math.min(cm[i][0], (T-cm[i][2])/cm[i][1]));
        }

        Arrays.sort(capacity);
        long b=0;
        for (int i = 0; i <R; i++) {
            b+=capacity[C-1-i];
            if(b>=B) return true;
        }
        return false;
    }

    private static long solve(int R, int B, int[][] cm) {
        long lo=1;
        long hi=Long.MAX_VALUE-3;

        while(lo<hi){
//            if(lo+1==hi){
//                if (can(R,B,cm,lo))
//                    return (int)lo;
//                else return (int)hi;
//            }
            long time = (lo+hi)/2;
            if (can(R,B,cm, time)){
                hi=time;
            }else
                lo=time+1;
        }
        return lo;
    }

    @Test
    public void test(){
        int[][] m = new int[][]{
                {2,3,3},
                {2,1,5},
                {2,4,2},
                {2,2,4},
                {2,5,1}
        };
        assertEquals(7, solve(3,4,m));

        int[][] m2 = new int[][]{
                {1,2,3},
                {2,1,2},
        };
        assertEquals(4, solve(2,2,m2));
    }


}
