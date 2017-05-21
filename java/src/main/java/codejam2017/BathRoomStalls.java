package codejam2017;

import codejam.lib.CodejamBase;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by darren on 5/17/17.
 */

public class BathRoomStalls extends CodejamBase {

    @Override
    public void parseAProblem(BufferedReader reader) {
        String[] s = new String[0];
        try {
            s = reader.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        long N = Long.parseLong(s[0]);
        long K = Long.parseLong(s[1]);

        long[] result = solve(N, K);
        writeSolution(String.format("%d %d", result[0], result[1]));
    }


    long[] solve(long N, long K){
        if(N==K) return new long[] {0,0};
        long left = (N-1)/2;
        long right = N-1-left;
        if(K==1){
            return new long[]{right, left};
        }
        K--;

        if(K%2==1)
            return solve(right, (K+1)/2);
        else return solve(left, K/2);
    }

    @Test
    public void test(){
        assertArrayEquals(new long[]{1, 0}, solve(4, 2));
        assertArrayEquals(new long[]{1, 0}, solve(5, 2));
        assertArrayEquals(new long[]{1, 1}, solve(6, 2));
        assertArrayEquals(new long[]{500, 499}, solve(1000, 1));
    }

    @Test
    public void test_file(){
        BathRoomStalls b = new BathRoomStalls();
        String dir = "/Users/darren/Downloads/";
        String small = "C-small-practice-1.in";
        String small2 = "C-small-practice-2.in";
        String large = "C-large-practice.in";

        b.solveFromDefault(large);
    }
}
