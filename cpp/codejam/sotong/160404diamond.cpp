#include <algorithm>
#ifdef _MSC_VER
#include <io.h>
#define F_OK 0
#else
#include <unistd.h>
#endif
#include <stdio.h>
#include <math.h>
#include <vector>
#include <map>
#include <queue>
#include <time.h>
#include <string>
#include <sstream>
#include <string.h>
#include <stack>
#include <list>
#include <limits>
#include <set>
#include <iostream>
#include "../myutil.h"

/*
 32*32*32 cube
 each cell is 0(dirt) or 1(diamond). then we have to cut the diamond as cuboid. cuboid can't contain dirt(0).

what is largest volume of the cuboid ?  
 */


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001

#define SWAP(a,b) do{int t=a;a=b;b=t;} while(0)

#define HDEBUG
#ifdef HDEBUG
#define hprint(fmt, args...) printf(fmt, ##args)
#else
#define hprint(fmt,args...)
#endif

#define N 32

int gCube[N][N][N];

void build(){
    for (int z = 0; z < N; z++){
        for (int y = 0; y < N; y++){
            for (int x = 0; x < N; x++){
                gCube[z][y][x] = rand()%10==0 ? 0 : 1;
            }
        }
    }      
}

void print(int z){
    for (int y = 0; y < N; y++){
        for (int x = 0; x < N; x++){
            printf("%d", gCube[z][y][x]);
        }
        printf("\n");
    }
}

int test(){
    print(0);
    return 0;
}

int main(){

    srand(0);
    
    // handling input
    int count=10;    
    for (int p=0; p<count; p++) {

        build();
        int max = test();
        //solve();
        printf("%d\n", max);
    }    
   
    
}

