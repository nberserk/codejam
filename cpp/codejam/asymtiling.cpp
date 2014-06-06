// algospot
// http://algospot.com/judge/problem/read/SNAIL

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string.h>
#include <limits>
#include <set>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define _MOD 1000000007
typedef long long ll;

void check(bool ret);


bool gDebug;
int gN, gM;
int gCache[101];

int tiling(int n){
    if(n==1 || n==2)
        return 1;
    
    int&ret = gCache[n];
    if(ret!=-1)
        return ret;

    ret= tiling(n-1) + tiling(n-2);
    ret%=_MOD;
    return ret;
}

void initCache(){
    memset(gCache, -1, sizeof(gCache));
}

int  solve(){
    int total = tiling(gN);
    int half = gN/2;
    int halfNum = tiling(half);

    int r = total - halfNum;        
    return r;
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

    gN = 2;
    check(0, solve());

    gN = 4;
    check(2, solve());
}


int main(){
    char fn[] = "asymtiling.in";
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
        scanf("%d", &gN);
                
        int v = solve();
        printf("%d\n", v);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

