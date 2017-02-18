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
optimize test function withn 5 sec
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

#define MAX 1000000

const int X = 2048;
const int Y = 2048;
const int BLOCK = 64;

char bitmap[Y][X], bitmap_org[Y][X];

void rect(int x, int y, int ex, int ey){
    memset(bitmap[y]+x, 1, ex-x+1);
    memset(bitmap[ey]+x, 1, ex-x+1);
    for (int i = y+1; i <= ey-1; i++){
        bitmap[i][x]=1;
        bitmap[i][ex]=1;
    }    
}

void swap(int x, int y, int dx, int dy){
    
}

void build(){
    memset(bitmap, 0, sizeof(bitmap)*X*Y);
    for (int i = 0; i < 100; i++){
        int x = rand()%(X-BLOCK);
        int y = rand()%(Y-BLOCK);
        int ex,ey;
        do{
            ex = rand()%(X-x);
        }while(x==ex);
        do{
            ey= rand()%(Y-y);            
        }while(y==ey);
        
        rect(x, y, ex, ey);
    }

    for (int i = 0; i < 100; i++){
        swap();
    }
}


int main(){
    srand(3);    

    int max =100;
    h_startTimeMeasure();
    for (int p=0; p<30; p++) {
    //for (int p=0; p<50; p++) {

        int result =0;
        for (int j = 0; j < 5; j++){

            for (int k = 0; k < max; k++){
                item[j].pos = (rand()%20000) -10000 + (rand()%10000)/10000.f;
                item[j].val = (rand()%20000) -10000 + (rand()%10000)/10000.f;                
            }
            //result += testNaive(item, max);
            result += testSort(item, max);
        }

        max *= 1.25;
        if (max>MAX){
            max=MAX;
        }

        printf("%d: %d(max=%d)\n", p+1, result, max);
    }    
   
    h_endTimeMeasure();
    
}

