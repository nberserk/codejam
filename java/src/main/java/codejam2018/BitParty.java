package codejam2018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by darren on 5/17/17.
 */

public class BitParty {

    static class Cashier{
        Cashier(){
            next =took=0;
        }
        int M,S,P;
        int next;
        int took;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int R = in.nextInt();
            int B = in.nextInt();
            int C = in.nextInt();
            Cashier[] cashier = new Cashier[C];
            for (int j = 0; j < C; j++) {
                cashier[j] = new Cashier();
                cashier[j].M = in.nextInt();
                cashier[j].S = in.nextInt();
                cashier[j].P = in.nextInt();
            }

            int r = solve(R,B, cashier);
            System.out.println("Case #" + i + ": " + r);
        }
    }

    private static int solve(int R, int B, Cashier[] cashier) {
        PriorityQueue<Cashier> pq = new PriorityQueue<>(cashier.length, (a,b)-> a.next ==b.next ? a.S-b.S:a.next -b.next);
        for (Cashier c: cashier){
            c.next = c.S+c.P;
            pq.add(c);
        }

        while(B>0 && pq.size()>0){
            Cashier c = pq.poll();
            if(c.took==0 && R==0){
                continue;
            }
            if(c.took==0)  R--;
            c.took=c.next;
            c.next +=c.S;
            c.M--;
            if(c.M>0)
                pq.add(c);
            B--;
        }

        int max=-Integer.MIN_VALUE;
        for (Cashier c: cashier){
            max=Math.max(max, c.took);
        }
        return max;
    }


//    @Test
//    public void test(){
//    }


}
