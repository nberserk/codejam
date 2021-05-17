package codejam.y2018;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 5/17/17.
 */

public class RoundingError {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int L = in.nextInt();
            int[] c = new int[L];
            for (int j = 0; j < L; j++) {
                c[j] = in.nextInt();
            }

            int r = solve(N, L, c);

            System.out.println("Case #" + i + ": " + r);
        }
    }

    static class  Node{
        double cur;
        int cost;
        boolean invincible;

        Node(double cur,int c, boolean i){
            this.cur=cur;
            cost=c;
            invincible=i;
        }
    }

    private static int solve(int N, int L, int[] c) {

        int remain = N;
        for (int i = 0; i < L; i++) {
            remain -= c[i];
        }

        double unit = (1/(double)N)*100 ;
        int unitN = (int)unit;
        unit -= unitN;

        int ret = unitN*N;
        double target = 0.5;
        PriorityQueue<Node> pq = new PriorityQueue<>(c.length, (a,b)->a.cost-b.cost);
        for (int i = 0; i < c.length; i++) {
            double cur = unit*c[i];
            int curN = (int)cur;
            cur -=curN;
            ret+=curN;
            if(cur>=target){
                ret++;
                continue;
            }
            int cost = (int)Math.ceil((target-cur)/unit);
            if(cost>remain){
                //System.out.println(String.format("ignore %d-%d",i, c[i]) );
                continue;
            }

            Node node = new Node(cur, cost, false);
            pq.add(node);
        }
        //
        double cur = unit;
        int cost = (int)Math.ceil((target-cur)/unit);
        if(cost<=0) cost=1;
        Node node = new Node(cur, cost, true);
        pq.add(node);


        while(remain>0){
            if (remain < pq.peek().cost){
                break;
            }
            Node n = pq.peek();

            ret++;
            remain-=n.cost;

            if(pq.peek().invincible==false) pq.poll();
        }
        return ret;
    }



    @Test
    public void test(){
        assertEquals(101, solve(6,2, new int[]{3,1}));
        assertEquals(99, solve(9,8, new int[]{1,1,1,1,1,1,1,1}));
        assertEquals(100, solve(10,3, new int[]{1,3,2}));
        assertEquals(100, solve(3,2, new int[]{1,1}));


        //solve(6,1, null);
    }
}
