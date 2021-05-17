package codejam.y2018;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 5/17/17.
 */

public class TroubleSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();

            int[] n = new int[N];
            for (int j = 0; j < N; j++) {
                n[j] = in.nextInt();
            }
            int r = solve(n);

            if(r==-1)
                System.out.println("Case #" + i + ": OK" );
            else
                System.out.println("Case #" + i + ": " + r);
        }
    }

    private static int solve(int[] n) {
        int N = n.length;
        int start=0;
        while(true){
            boolean swap=false;
            //System.out.println("phase:"+count++);
            for (int i = start; i < N - 2; i++) {
                if (n[i]>n[i+2]){
                    int t = n[i];
                    n[i]=n[i+2];
                    n[i+2]=t;
                    if (swap==false){
                        start=i-2;
                        swap=true;
                    }
                    //System.out.println("swap:"+ i+" start:"+start);
                }
            }
            if (!swap) break;
            if(start<0) start=0;
            //System.out.println("start:"+start);
        }

        for (int i = 0; i < N - 1; i++) {
            if (n[i]>n[i+1]){
                return i;
            }
        }

        return -1;
    }

    private static int solve_2(int[] n) {
        int N = n.length;
        int start=0;

        int en=N/2+N%2;
        int on=N/2;
        int[] odd = new int[on];
        int[] even = new int[en];

        for (int i = 0; i < N; i++) {
            if(i%2==0)
                even[i/2]=n[i];
            else
                odd[i/2]=n[i];
        }

        Arrays.sort(even);
        Arrays.sort(odd);

        for (int i = 0; i < en; i++) {
            if(i<on && even[i]>odd[i])
                return i*2;
            if(i+1<en && odd[i]>even[i+1]){
                return i*2+1;
            }
        }

        return -1;
    }

    @Test(timeout = 20000)
    public void test(){
        assertEquals(-1, solve_2(new int[]{5, 6, 8, 4, 3}));
        assertEquals(1, solve_2(new int[]{8, 9, 7}));
        assertEquals(1, solve_2(new int[]{9,4,3,100,2,33,54,38,99}));

        assertEquals(-1, solve(new int[]{5, 6, 8, 4, 3}));
        assertEquals(1, solve(new int[]{8, 9, 7}));
        assertEquals(1, solve(new int[]{9,4,3,100,2,33,54,38,99}));

        for (int j = 0; j < 100; j++) {
            int[] n = new int[100000];
            Random random = new Random();

            for (int i = 0; i < 100000; i++) {
                n[i] = random.nextInt(1000000);
            }

            //int r = solve(n);
            int r2 = solve_2(n);

            //assertEquals(r,r2);
            System.out.println(r2);
        }
    }


}
