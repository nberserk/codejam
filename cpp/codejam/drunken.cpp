// http://algospot.com/judge/problem/read/DRUNKEN
// category: graph

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
#include <list>
#include <limits>
#include <set>
#include <iostream>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
int gN;
int gMat[501][501];
int gDelay[501];
int gTime[501][501];
int gMaxDelay[501][501];

void floyd(){
    for (int i=1; i<=gN; i++) {
        for (int j=1; j<=gN; j++) {
            if (gMat[i][j]==-1)
                gTime[i][j] = H_MAX;
            else
                gTime[i][j] = gMat[i][j];
        }
    }
//    memcpy(gTime, gMat, sizeof(gMat));
    for (int i = 0; i < gN; i++){
        gTime[i][i] =0;
    }

    for (int k = 1; k <= gN; k++){
        for (int i = 1; i <= gN; i++){
            for (int j = 1; j <= gN; j++){
                int delay = 0;
                if (k!=i && k!=j){
                    delay = gDelay[k];
                }
                gTime[i][j] = min(gTime[i][j], delay+gTime[i][k]+gTime[k][j]);                
            }            
        }
    }
}

void test(){
}

int main(){
    char fn[] = "drunken.in";
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

    
        int e;
        scanf("%d %d", &gN, &e);
        for (int i = 0; i < gN; i++){
            scanf("%d", &gDelay[i]);            
        }
        memset(gMat, -1, sizeof(gMat));
        for (int j = 0; j < e; j++){
            int from , to, time;
            scanf("%d %d %d", &from, &to, &time);
            gMat[from][to] = time;
            gMat[to][from] = time;
        }
        floyd();
        
        scanf("%d", &count);
        for (int i = 0; i < count; i++){
            int from, to;
            scanf("%d %d", &from, &to);
            printf("%d\n", gTime[from][to]);
        }


    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}





