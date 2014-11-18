// http://algospot.com/judge/problem/read/PACKING

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string>
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
char gName[100][21];
int gCache[101][1001];
int gNext[101][1001];

void initCache(){
    memset(gCache, -1, sizeof(gCache));
    memset(gNext, -1, sizeof(gNext));
}


int doSolve2(int idx, int w){
    if (idx >= gN || w<0) {
        return 0;
    }
    
    int& ret = gCache[idx][w];
    if (ret!=-1) {
        return ret;
    }

    int& next = gNext[idx][w];
    ret = doSolve2(idx+1, w);
    if (w >= gSize[idx]) {
        int another = doSolve2(idx+1, w-gSize[idx]) + gValue[idx];
        if (ret < another){
            next = 1;
            ret = another;
        }
    }
    //printf("%d,%d=%d, %d\n", idx,w,ret, next);
    return ret;
}

vector<string> reconstruct2(){
    vector<string> items ;
    
    int w = gW;
    for (int i=0; i<gN; i++) {
        if (gNext[i][w]==1) {
            items.push_back(gName[i]);
            w -= gSize[i];
        }
    }
    
    return items;
}

void solve2(){
    initCache();
    int v = doSolve2(0, gW);
    vector<string> items = reconstruct2();
    printf("%d %d\n", v, items.size());
    for (int i=0; i<items.size(); i++) {
        printf("%s\n", items[i].c_str());
    }
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

  
//    test();
    
    int count, p,j,k;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%d %d ", &gN, &gW);
        for(j=0;j<gN;j++){
            scanf("%s %d %d", &gName[j], gSize+j, gValue+j );            
        }
                        
        solve2();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

