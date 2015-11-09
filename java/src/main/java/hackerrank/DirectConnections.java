package hackerrank;

import java.util.Scanner;


public class DirectConnections {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
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

            int sum=0;
            int module = 1000000007 ;
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

            System.out.println(sum);
        }
    }
}

