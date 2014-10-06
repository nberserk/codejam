// rain water 2d
// from http://codeanytime.blogspot.kr/2013/03/this-was-discussed-in-httpwww.html

#include <algorithm>
#include <unistd.h>
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
#include <limits>
#include <set>
#include <iostream>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
#define H_MOD 10000000
typedef long long ll;
typedef unsigned int ui;

void check(bool ret);

int gN;
int gM[255][255];
int gPotentialWaterRows[255][255];
int gPotentialWaterCol[255][255];

bool gDebug;


void FindMaxHeightCols(int newMatrixCols[255][255])
{
    int gAscTB[255];
    int gAscBT[255];
    for (int j = 1; j < gN; ++j)
    {
        // Find the ascending sequence from Top to bottom
        gAscTB[0] = gM[0][j];
        for (int i = 1; i < gN; ++i)        {
            gAscTB[i] = max(gAscTB[i-1], gM[i][j]);
        }
        // Find the ascending sequence from bottom to top
        gAscBT[gN-1] = gM[gN-1][j];
        for (int i = gN-2; i >=0; --i)        {
            gAscBT[i] = max(gAscBT[i+1], gM[i][j]);
        }
        
        for(int i = 1; i < gN; ++i)        {
            newMatrixCols[i][j] = min(gAscBT[i], gAscTB[i]);
        }
    }
}

void FindMaxHeightRows(int newMatrixRows[255][255]){
    int gAscLR[255];
    int gAscRL[255];
    for (int i = 0; i < gN; ++i)
    {
        // Find the ascending sequence from left to right        
        gAscLR[0] = gM[i][ 0];
        for (int j = 1; j < gN; ++j)        {
            gAscLR[j] =max(gAscLR[j-1], gM[i][j]);
        }
        // Find the ascending sequence from Right to Left
        gAscRL[gN-1] = gM[i][gN-1];
        for (int j = gN-2; j >=0; --j)        {
            gAscRL[j]= max(gAscRL[j+1], gM[i][j]);
        }
        
        for(int j = 0; j < gN; ++j){
            newMatrixRows[i][j] = min(gAscRL[j], gAscLR[j]);
        }
    }
}


int solve()
{
    //Two extra 2d array with same size as matrix
    FindMaxHeightRows(gPotentialWaterRows);
    FindMaxHeightCols(gPotentialWaterCol);
    int sum = 0;
    for (int i = 1; i < gN-1; ++i){
        for (int j = 1; j < gN-1; ++j){
            int cur = min(gPotentialWaterRows[i][j], gPotentialWaterCol[i][j]) - gM[i][j];
            if (j==1 && i==1) {
                printf("\n");
            }
            printf("%3d", cur);
            sum += cur;
        }
        printf("\n");
    }
    return sum;
}


void check(bool ret){
    if (ret==false) {
        printf("failed\n");
    }
}

void check(ui expected, ui actual){
    if (expected!=actual) {
        printf("failed expected=%d, actual=%d\n", expected, actual);
    }
}

void check(char expected, char actual){
    if (expected!=actual) {
        printf("failed expected=%c, actual=%c\n", expected, actual);
    }
}

void test(){

}

int main(){
    char fn[] = "rainfill.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    test();

    // handling input
    int count, p,j,k, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d ", &gN);
        for ( i=0;i<gN;i++)
            for (j=0;j<gN;j++)
                scanf("%d", &gM[i][j]);

        int s = solve();
        printf("%d\n", s);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

