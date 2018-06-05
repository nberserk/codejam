package codejam2018;//package codejam2018;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class WaffleChoppers {


    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int R = in.nextInt();
            int C = in.nextInt();
            int H = in.nextInt();
            int V = in.nextInt();

            int[][] m = new int[R][C];
            for (int j = 0; j < R; j++) {
                String temp = in.next();
                for (int k = 0; k < C; k++) {
                    m[j][k] = temp.charAt(k)=='.' ? 0:1;
                }
            }

            int r = solve(R,C,H,V,m);

            System.out.println("Case #" + i + (r==-1?": IMPOSSIBLE":": POSSIBLE") );
        }
    }

    private static int solve(int R, int C, int H, int V, int[][] m){
        int chocolate=0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                chocolate+=m[r][c];
            }
        }
        if(chocolate==0) return 0;
        if(chocolate%( (H+1)*(V+1)) !=0) return -1;

        int count=0;
        int k=0;
        int[] hi= new int[R];
        int target = chocolate/(H+1);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                count+=m[i][j];
            }
            hi[i]=k;
            if(count>(k+1)*target) return -1;
            if(count==(k+1)*target) k++;
        }

        // why not check k
        if(k!=H+1) return -1;


        target = chocolate/((H+1)*(V+1));
        int[] countH = new int[H+1];

        k=0;
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                countH[hi[j]] +=m[j][i];
            }

            int hitCount=0;
            for (int j = 0; j < H + 1; j++) {
                if (countH[j]>(k+1)*target) return -1;
                if (countH[j]==(k+1)*target) hitCount++;
            }
            if(hitCount==H+1) k++;
        }

        if(k!=V+1) return -1;


        return 0;
    }


    private static int solve1(int R, int C, int H, int V, int[][] m) {
        int chocolate=0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                chocolate+=m[r][c];
            }
        }
        if(chocolate==0) return 0;
        if(chocolate%( (H+1)*(V+1)) !=0) return -1;

        int target = chocolate/(H+1);

        ArrayList<Integer> hsplit = new ArrayList<>();

        int count=0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                count+=m[i][j];
            }

            if(count>target) return -1;
            if(count ==target) {
                hsplit.add(i);
                count=0;
            }
        }

        if(hsplit.size()!=H+1) return -1;

        // first row
        target= chocolate/((H+1)*(V+1));

        ArrayList<Integer> vsplit = new ArrayList<>();
        count = 0;
        for (int c = 0; c < C; c++) {
            for (int i = 0; i <= hsplit.get(0); i++) {
                count +=m[i][c];
            }
            if(count > target) return -1;
            if(count==target){
                vsplit.add(c);
                count=0;
            }
        }

        if(vsplit.size()!=V+1) return -1;

        for (int hi = 1; hi < H+1; hi++) {
            int rs=hsplit.get(hi-1)+1;
            int re=hsplit.get(hi);

            for (int vi = 0; vi < V+1; vi++) {
                int cs = vi==0? 0 : vsplit.get(vi-1)+1;
                int ce = vsplit.get(vi);

                count=0;
                for (int r = rs; r <= re; r++) {
                    for (int c = cs; c <= ce; c++) {
                        count+=m[r][c];
                    }
                }

                if(count!=target)return -1;
            }
        }


        return 0;
    }


    @Test
    public void test(){
        int[][] m3 = new int[][]{
                {0,0,1,1},
                {0,0,1,1},
                {1,1,0,0},
                {1,1,0,0}

        };
        assertEquals(-1, solve(4,4,1,1, m3));

        int[][] m = new int[][]{
            {0,1,1,0,0,1},
            {0,0,0,0,0,1},
            {1,0,1,0,1,1}
        };
        assertEquals(0, solve(3,6,1,1, m));

        int[][] m2 = new int[][]{
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        assertEquals(0, solve(4,5,1,1, m2));


        int[][] m4 = new int[][]{
                {0,1,0,1},
                {1,0,1,0},
                {0,1,0,1},
        };
        assertEquals(-1, solve(3,4,1,2, m4));

        int[][] m5 = new int[][]{
            {1,0,1,1},
            {1,1,0,1},
            {1,0,1,1},
        };
        assertEquals(0, solve(3,4,2,2, m5));



        int[][] m6 = new int[][]{
            {1,1,1},
            {1,0,1},
            {1,0,1},
            {1,1,1}
        };
        assertEquals(-1, solve(4,3,1,1, m6));

    }


}
