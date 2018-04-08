package codejam2018;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 5/17/17.
 */

public class SavingTheUniverseAgaing {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int D = in.nextInt();
            String P = in.next();

            int r = solve(D, P);

            if(r==-1)
                System.out.println("Case #" + i + ": IMPOSSIBLE" );
            else
                System.out.println("Case #" + i + ": " + r);
        }
    }

    static int calc(char[] ch){
        int sum=0;
        int strength=1;
        for (char c: ch){
            if(c=='S')
                sum += strength;
            else
                strength*=2;
        }
        return sum;
    }

    private static int solve(int D, String P) {
        char[] ch = P.toCharArray();
        int N = ch.length;

        int sCount=0;
        for (char c: ch){
            if(c=='S') sCount++;
        }
        if (D<sCount)
            return -1;

        int count=0;
        while(calc(ch)>D){
            boolean swap=false;
            for (int i = 0; i < N-1; i++) {
                if(ch[i]=='C'&&ch[i+1]=='S'){
                    ch[i]='S';
                    ch[i+1]='C';
                    swap=true;
                    break;
                }
            }

            if (swap==false){
                return -1;
            }
            count++;
        }
        return count;
    }

    @Test
    public void test(){
        assertEquals(1, solve(1, "CS"));
        assertEquals(0, solve(2, "CS"));
        assertEquals(2, solve(6, "SCCSSC"));
        assertEquals(5, solve(3, "CSCSS"));

    }


}
