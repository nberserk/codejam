package hackerrank;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2015. 12. 31..
 */
public class EfficientExponentiation {

    static List<Integer> best ;

    public static void solve(int N, int cur, LinkedList<Integer> list){
        if (cur>N)
            return;
        if(best!=null && list.size()>best.size())
            return;
        if(cur==N){
            if(best==null || best.size()>list.size()){
                best = new LinkedList<Integer>(list);
                //System.out.println(list.size()+ ":"+list);
            }
            return;
        }

        for (int i = list.size()-1; i >=0; i--) {
            int v = cur + list.get(i);
            list.addLast(v);
            solve(N, v, list);
            list.removeLast();
        }
    }

    static int solve2(int N){
        best=null;

        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(1);
        solve(N, 1, list);

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

        assertEquals(1, solve2(2));
        assertEquals(5, solve2(15));


        Scanner s = new Scanner(System.in);
        int T = s.nextInt();

        for (int i = 0; i < T; i++) {
            int k = s.nextInt();
            solve2(k);
        }
    }
}
