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

#define MAX_CAR 1000000
#define TREE


void hassert(bool a){
    if(!a){
        hprint("error_line: %d\n", __LINE__);
    }
}

int src[1000][100];
int dqr[1000][100][100];
int img[1000][200][200];
int gray[1000][100][100];
int dst[1000][100];

#define MUL 4
#define MARK_LEN    4



void encode(int a[100][100], int src[100]){
    
    
}

void decode(int g[100][100], int d[100]){
    
}

//////////////////////////////////////////////////

void test(){
    
}

void img2gray(int image[200][200], int gry[100][100]){
    for (int y=0; y<100; y++) {
        for (int x=0; x<100; x++) {
            gry[y][x] = 0;
            gry[y][x] += image[y*2+0][x*2+0];
            gry[y][x] += image[y*2+1][x*2+0];
            gry[y][x] += image[y*2+0][x*2+1];
            gry[y][x] += image[y*2+1][x*2+1];
        }
    }
}

int main(){
    srand(3);

    test();

    int sum=0;
    h_startTimeMeasure();
    for(int i=0;i<1000;i++){
        for (int j=0; j<100; j++) {
            src[i][j] = rand()%26;
        }
    }
    
    for(int i=0;i<1000;i++){
        encode(dqr[i], src[i]);
    }
    
    for(int i=0;i<1000;i++){
        int offsetx = rand()%101;
        int offsety = rand()%101;
        
        for (int r=0; r<100; r++) {
            for (int c=0; c<100; c++) {
                img[i][r+offsety][c+offsetx] = dqr[i][r][c]==0 ? 0: 1;
            }
        }
    }
    
    for(int i=0;i<1000;i++){
        
        for (int r=0; r<13000; r++) {
            img[i][rand()%200][rand()%200] = 1;
        }
        
        img2gray(img[i], gray[i]);
    }
    
    
    for(int i=0;i<1000;i++){
        decode(gray[i], dst[i]);
    }
    
    int penalty=0;
    for(int i=0;i<1000;i++){
        if(memcmp(src[i], dst[i], sizeof(src[i]))!=0)
            penalty+=100;
    }
    
    printf("penalty: %d\n", penalty);
   
    h_endTimeMeasure();
    
}



