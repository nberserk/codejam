package main.java.crackcode.impl;

import java.util.Arrays;

/**
 4444444
 4333334
 4322234
 4321234
 4322234
 4333334
 4444444
 */


public class Rectangle7 {

    static char[][] print(){
        int N=7;
        char[][] s = new char[7][7];

        int[][] d = { {1,0}, {0,1}, {-1,0}, {0, -1}};

        int limit=0;
        int v=4;
        for (int y=0 ; y<N-limit; y++){ // when y=3, limit=3
            int x = y;

//            if (y==3){
//                s[y][x] = Character.forDigit(v, 10);
//                break;
//            }

            int left = x;  // 1
            int right = N-limit-1; // 5
            int cx = x; // 1
            int cy = y; // 1
            int di =0;
            while(di<=3){
                s[cy][cx] = Character.forDigit(v, 10);
                cx+=d[di][0]; // 5
                cy += d[di][1];  // 6
                if (cx > right || cy > right || cx < left || cy < left){
                    cx-=d[di][0];  // 5
                    cy -= d[di][1];  // 0
                    di++;
                    if(di>3)
                        break;
                    cx+=d[di][0];  // 4
                    cy += d[di][1];    // 5
                }
            }
            v--;
            limit++;
        }

        return s;
    }

    public static void main(String[] args) {
        char[][]s =  print();

        System.out.println(Arrays.toString(s[0]));
        System.out.println(Arrays.toString(s[1]));
        System.out.println(Arrays.toString(s[2]));
        System.out.println(Arrays.toString(s[3]));
        System.out.println(Arrays.toString(s[4]));
        System.out.println(Arrays.toString(s[5]));
        System.out.println(Arrays.toString(s[6]));

    }


}
