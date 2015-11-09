package hackerrank;

import codejam.lib.CheckUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * category: dp
 * from : https://www.hackerrank.com/challenges/direct-connections
 */
public class DirectConnections {

    static int solve(final int[] x, final int [] p){
        int N = x.length;
        Integer[] sx = new Integer[N]; // sorted x
        Integer[] sp = new Integer[N]; // sorted p

        for (int i = 0; i < N; i++) {
            sx[i] = i;
            sp[i] = i;
        }

        Arrays.sort(sx, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return x[o1]-x[o2];
            }
        });

        //Arrays.sort
        Arrays.sort(sp, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return p[o1] - p[02];
            }
        });

        int sum=0;
        int module = 1000000007;
        for(int j=0;j<N;j++){
            for(int k=j+1;k<N;k++){
                long d = Math.abs(x[k]-x[j]);
                long pp = Math.max(p[j], p[k]);
                pp *= d;
                pp %= module;
                sum += pp;
                sum %= module;
            }
        }

        return sum;
    }

    static void test(){

        int [] x = new int[]{5, 55, 555, 55555, 555555};
        int[] p = new int[] {3333,333, 333, 33, 35};
        int s = solve(x,p);
        CheckUtil.check(463055586, s);
    }

    public static void main(String[] args) {

        test();

        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        for(int i =0;i<T;i++){
            int N = s.nextInt();
             int[] x = new int[N];
             int[] p = new int[N];


            for(int j=0;j<N;j++){
                x[j] = s.nextInt();
            }
            for(int j=0;j<N;j++){
                p[j] = s.nextInt();
            }

            int sum = solve(x, p);
            System.out.println(sum);

        }
    }
}

