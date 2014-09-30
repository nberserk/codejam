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
int gTemp[255];
int gTemp1[255];
int gSum[100000];
vector<vector<int>> pairs;
bool gDebug;
int gCount, gUniqueCount;
int gLastIndex;

void FindMaxHeightCols(int* newMatrixCols)
{
    for (int j = 0; j < gN; ++j)
    {
        // Find the ascending sequence from Top to bottom
        gTemp[0] = gM[0][j];
        for (int i = 1; i < gN; ++i)
        {
            gTemp[i] =max(gTemp[i-1], gM[i][j]);
        }
        // Find the ascending sequence from Top to bottom
        gTemp1[0] = gM[gN-1][j];
        for (int i = gN-2; i >=0; --i)
        {
            gTemp1[i] = (max(gTemp1[i+1], gM[i][j]));
        }
        
        for(int i = 0; i < gN; ++i)
        {
            newMatrixCols[i, j] = Math.Min(ascB_T[i], ascT_B[i]);
        }
    }
}


void FindMaxHeightRows(int[] newMatrixRows){
    
    for (int i = 0; i < gN; ++i)
    {
        // Find the ascending sequence from left to right
        int[] ascL_R = new int[n];
        ascL_R[0] = matrix[i, 0];
        for (int j = 1; j < n; ++j)
        {
            ascL_R[j] =Math.Max(ascL_R[j-1], matrix[i,j]);
        }
        // Find the ascending sequence from Right to Left
        int[] ascR_L = new int[n];
        ascR_L[n-1] = matrix[i, n-1];
        for (int j = n-2; j >=0; --j)
        {
            ascR_L[j]= Math.Max(ascR_L[j+1], matrix[i,j]);
        }
        
        for(int j = 0; j < n; ++j){
            newMatrixRows[i, j] = Math.Min(ascR_L[j], ascL_R[j]);
        }
    }
}


int solve()
{
    //Two extra 2d array with same size as matrix
    int[] potentialWaterRows = new int[gN];
    int[] potentialWaterCol = new int[gN];
    FindMaxHeightRows(potentialWaterRows);
    FindMaxHeightCols(potentialWaterCol);
    int sum = 0;
    for (int i = 0; i < m; ++i)
    {
        for (int j = 0; j < n; ++j)
        {
           
            sum += Math.Min(potentialWaterRows[i, j], potentialWaterCol[i, j]) - matrix[i, j];
        }
    }
    return sum;
}



void solve(){
    gCount = gUniqueCount=0;
    gLastIndex=-1;
    // calc partial sum
    pairs = vector<vector<int>>(gK);
    pairs[0].push_back(-1);
    int sum = 0;
    for (int i = 0; i < gN; i++){
        sum = ( sum + gn[i])%gK;
        pairs[sum].push_back(i);
    }
    
    int count =0;
    int size;
    for (int i = 0; i < gK; i++){
        size = pairs[i].size();
        if (size >=2) {
            count += size*(size-1)/2;
            count %= 20091101;
        }
    }
    gCount = count;
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
    char fn[] = "christmas.in";
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
        scanf("%d %d", &gN, &gK);
        for (int i=0;i<gN;i++)
            scanf("%d", gn+i);
        solve();
        printf("%d %d\n", gCount, gUniqueCount);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

