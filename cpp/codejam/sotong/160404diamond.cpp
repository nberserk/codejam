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
                gCube[z][y][x] = rand()%32==0 ? 0 : 1;
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

// return endZ having the region is 1.
int findEndZ(int sx, int sy, int ex, int ey, int sz){
    for (int z=sz; z<N;z++) {
        for (int y = sy; y <= ey; y++){
            for (int x = sx; x <= ex; x++){
                if (gCube[z][y][x]==0){
                    return z-1;
                }                
            }
        }        
    }

    return N-1;
}

int bruteforce(){
    int maxVolume=1;
    
    for (int z = 0; z < N; z++){
        for (int y = 0; y < N; y++){
            for (int x = 0; x < N; x++){
                
                
                int max_x = N-1;
                for (int ey = y; ey < N; ey++){
                    for (int ex = x; ex <= max_x; ex++){
                        if (gCube[z][ey][ex]==0) {
                            max_x = ex-1;
                            break;
                        }

                        printf("%d,%d - %d,%d\n", y,x, ey,ex);
                        int ez = findEndZ(x,y, ex, ey, z+1);
                        int w = ex-x+1;
                        int h = ey-y+1;
                        int d = ez-z+1;
                        int v = w*h*d;
                        if (v>maxVolume) {
                            printf("maxVolume=%d\n", v);
                            maxVolume=v;
                        }
                    }
                    
                    if (max_x==0) {
                        break;
                    }
                }
                // d
            }
                
                
        }
        break;            
    }

    return maxVolume;
}

int test(){
    print(0);
    return bruteforce();
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

