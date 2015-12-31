package hackerrank;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by darren on 2015. 12. 31..
 */
public class EfficientExponentiation {

    static int [][]cache = new int[280][3];

    public static int solve(int N){
        if (N==1) return 0;
        if(cache[N][2]!=0) return cache[N][2];
        cache[N][2] = 987654321;

        int half = N/2;
        for(int i=1;i<=half;i++){
            int a = i;
            int b =N-i;
            int cur = 1+solve(a);
            if(a!=b)
                cur += solve(b);
            if(cur<cache[N][2]){
                cache[N][0]=a;
                cache[N][1]=b;
                cache[N][2]=cur;
            }
        }
        return cache[N][2];
    }

    static void path(int N, TreeSet<Integer> set){
        if(N==1)return;
        set.add(N);

        path(cache[N][0], set);
        path(cache[N][1], set);
    }

    static int solve2(int N){
        
    }

    public static void main(String[] args) {

        cache[2][0] = 1;
        cache[2][1] = 1;
        cache[2][2] = 1;

        solve(15);
        path(15, new TreeSet<Integer>());

        Scanner s = new Scanner(System.in);
        int T= s.nextInt();
        for(int i=0;i<T;i++){
            int n = s.nextInt();
            solve(n);
            TreeSet<Integer> set = new TreeSet<Integer>();
            path(n, set);

            System.out.println(set);

        }

    }
}
