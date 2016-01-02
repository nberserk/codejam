package hackerrank;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by darren on 2015. 12. 31..
 */
public class EfficientExponentiation {

    static int [][]cache = new int[280][3];

    public static void solve(int N, TreeSet<Integer> set){
        if(N==1) return;

        if(cache[N][0]!=0){
            set.add(cache[N][0]);
            set.add(cache[N][1]);
            return;
        }

        cache[N][2] = 999999999;
        set.add(N);
        int half = N/2;
        for(int i=1;i<=half;i++){

            int b =N-i;

            TreeSet<Integer> next = new TreeSet<>();
            solve(i, next);
            //merge(set, next);

            if(!set.contains(b)){
                TreeSet<Integer> next2 = new TreeSet<>();
                solve(b, next2);
                merge(next, next2);
            }

            if(next.size()+1<cache[N][2]){
                System.out.println(String.format("%d,%d-%d=%d",N, i,b,next.size()+1));
                cache[N][0] = i;
                cache[N][1] = b;
                cache[N][2] = next.size()+1;
                set.clear();
                set.addAll(next);
            }
        }
    }

    private static void merge(TreeSet<Integer> big, TreeSet<Integer> small) {
        for (Integer i : small){
            if (big.contains(i))
                continue;
            big.add(i);
        }
    }

    static void path(int N, TreeSet<Integer> set){
        if(N==1)return;
        set.add(N);

        path(cache[N][0], set);
        path(cache[N][1], set);
    }

    static int solve2(int N){
        return 0;
    }

    public static void main(String[] args) {
        cache[1][0] = 1;
        cache[1][1] = 0;
        cache[1][2] = 1;

        cache[2][0] = 1;
        cache[2][1] = 1;
        cache[2][2] = 1;

        TreeSet<Integer> set2 = new TreeSet<>();
        solve(15, set2);
        path(15, new TreeSet<Integer>());

        Scanner s = new Scanner(System.in);
        int T= s.nextInt();
        for(int i=0;i<T;i++){
            int n = s.nextInt();
            TreeSet<Integer> set = new TreeSet<Integer>();
            solve(n, set);

            path(n, set);

            System.out.println(set);

        }

    }
}
