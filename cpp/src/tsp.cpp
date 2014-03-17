#include <stdio.h>
#include <math.h>

#define MAX_N 10

int t[10][];

int tsp(int cost[4][4], int N){

    int n = powl(2, N-1);


}



void tsp_main(){
    int c[4][4] = {
            {0, 12, 11, 16},
            {15, 0, 15, 10},
            {8,14,0,8},
            {9,11,17,0}
    };

    int min = tsp(c, 4);
    printf("%d\n", min);
}
