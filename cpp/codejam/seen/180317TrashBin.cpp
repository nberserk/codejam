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
1000x1000 cell, each cell default value is 0
10000 trash : -1
3 trash bin : 1,2,3
move_trash(y,x,dir)
 
 my result: 4960 0000
*/

using namespace std;
// #define H_MAX 987654321
// #define H_MIN -987654321
// #define H_EPSILON 0.000001

#define SWAP(a,b) do{int t=a;a=b;b=t;} while(0)

#define HDEBUG
#ifdef HDEBUG
#define hprint(...) printf( __VA_ARGS__ )
#else
#define hprint(...)
#define hassert(a)
#endif


void hassert(bool a){
    if(!a){
        hprint("error_line: %d\n", __LINE__);
    }
}

#define SIZE 1000

int trash_map[SIZE][SIZE];
int org_trash_map[SIZE][SIZE];
int trash_bin[3];
int result;

void gen_trash_map(int m[SIZE][SIZE]){
    for (int y=0; y<SIZE; y++) {
        for (int x=0; x<SIZE; x++) {
            m[y][x]=0;
        }
    }
    for (int i=0; i<10000; ) {
        int y = rand()%SIZE;
        int x = rand()%SIZE;
        
        if(m[y][x]==0){
            m[y][x] = -1;
            i++;
        }
    }
    for(int i=0;i<3;){
        int y = rand()%SIZE;
        int x = rand()%SIZE;
        if(m[y][x]==0){
            m[y][x] = i+1;
            i++;
        }
    }
    
    for (int y=0; y<SIZE; y++) {
        for (int x=0; x<SIZE; x++) {
            org_trash_map[y][x]=m[y][x];
        }
    }
    
    for(int i=0;i<3;i++){
        trash_bin[i]=0;
    }
}

void move_trash(int y,int x, int d){
    int ox = x;
    int oy = y;

    switch (d) {
        case 0: y--; break;
        case 1: y++; break;
        case 2: x--; break;
        case 3: x++; break;
    }
    
    if(x<0 || y<0 || x>=SIZE || y>=SIZE){
        hassert(0);
        return;
    }
    
    if(org_trash_map[oy][ox]!=-1 || org_trash_map[y][x]==-1){
        hassert(0);
        return;
    }
    
    if (org_trash_map[y][x]==0) {
        org_trash_map[y][x]=-1;
        org_trash_map[oy][ox]=0;
    }else{
        int i=org_trash_map[y][x]-1;
        
        if (trash_bin[i]>=3500) {
            hassert(0);
            return;
        }
        trash_bin[i]++;
        org_trash_map[oy][ox]=0;
    }
}

void test(int m[SIZE][SIZE]){
    
}

int main(){
    srand(3);

    for (int i = 0; i < 10; i++) {
        gen_trash_map(trash_map);
        test(trash_map);
        clock_t start = clock();

        result += clock()-start;

        for (size_t y = 0; y < SIZE; y++)
        {
            for (size_t x = 0; x < SIZE; x++)            
            {
                if(org_trash_map[y][x] == -1) 
                    result += 10000;
            }
        }
    }
    
    printf("result=%d\n", result);
}



