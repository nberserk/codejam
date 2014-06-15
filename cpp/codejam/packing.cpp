// http://algospot.com/judge/problem/read/PACKING

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string.h>
#include <stack>
#include <limits>
#include <set>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
int gN, gW, gSize[100], gValue[100];
char gName[100][20];
int gCache[101][1000];
int gNext[101];

void initCache(){
    memset(gCache, -1, sizeof(gCache));
    memset(gNext, -1, sizeof(gNext));
}

int doSolve(int idx, int size){
    if(idx==gN || size <=0)
        return 0;
    int& ret = gCache[idx+1][size] ;
    if(ret !=-1)
        return ret;

    int cur;
    ret =0;
    
    for(int i=idx;i<gN;i++){
        if(idx==-1 || gSize[i] < size){
            if(idx==-1)
                cur = doSolve(i+1,size);
            else
                cur = gValue[i] + doSolve(i+1,size-gSize[i]);
            if(cur > ret){
                gNext[idx+1] = i;
                ret = cur;
            }
        }       
    }
    //printf("%d,%d=%f", day,dest,ret);
    return ret;
}

void solve(){
    int maxValue = doSolve(-1, gW);
    printf("%d ", maxValue);

    printf("\n");
}

void check(bool ret){
    if (ret==false) {
        printf("failed\n");
    }
}

void check(int expected, int actual){
    if (expected!=actual) {
        printf("failed expected=%d, actual=%d\n", expected, actual);
    }
}


void test(){
   
}


int main(){
    char fn[] = "packing.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }

    initCache();
    //test();
    
    int count, p,j,k;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%d %d ", &gN, &gW);
        for(j=0;j<gN;j++){
            scanf("%s %d %d", &gName[j], gSize+j, gValue+j );            
        }
                        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

