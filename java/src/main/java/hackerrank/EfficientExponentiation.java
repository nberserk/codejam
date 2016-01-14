package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2015. 12. 31..
 */
public class EfficientExponentiation {

    static List<Integer> best = new ArrayList<>();
    static final int MAX_N = 300;
    static int[] depth =  new int[MAX_N];
    static int[] path = new int[MAX_N];

    public static void expand(int N, int cur, int curDepth){
        if(cur>N || curDepth > depth[cur]) return;

        if(best.size()>0 && curDepth+1 >= best.size()) return;

        depth[cur] = curDepth;
        path[curDepth] = cur;
        if(cur==N){
            if(best.size()==0 || best.size() > curDepth+1){
                best.clear();
                for (int i = 0; i <= curDepth; i++) {
                    best.add(path[i]);
                }
                //System.out.println(best.size() +":" + best.toString());
            }
            return;
        }
        for (int i = curDepth; i >= 0; i--) {
            expand(N, cur + path[i], curDepth+1);
        }

    }

    static int solve(int N){
        best.clear();
        for (int i = 1; i <= N; i++) {
            depth[i] = Integer.MAX_VALUE;
        }

        expand(N, 1, 0);

        System.out.println(best.size() - 1);
        for (int i = 1; i < best.size(); i++) {
            int t = best.get(i);
            int prev = best.get(i-1);

            for (int k = 0; k < i; k++) {
                if ( prev + best.get(k) == t){
                    System.out.println(String.format("n^%d * n^%d=n^%d", prev, best.get(k), t));
                    break;
                }
            }
        }
        return best.size()-1;
    }

    public static void main(String[] args) {

        assertEquals(1, solve(2));
        assertEquals(5, solve(15));
        assertEquals(11, solve(275));


        Scanner s = new Scanner(System.in);
        int T = s.nextInt();

        for (int i = 0; i < T; i++) {
            int k = s.nextInt();
            solve(k);
        }
    }
}
