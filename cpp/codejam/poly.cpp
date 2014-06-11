// http://algospot.com/judge/problem/read/POLY

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
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
int gN;
int gCache[100][101];

int combination(int parentColCount, int remain){
    if(remain==0) return 1;

    int& ret = gCache[parentColCount][remain];
    if(ret!=-1)
        return ret;
    
    ret = 0;
    for(int i=1; i<=remain ;i++){
        int temp = combination(i, remain-i);
        if(parentColCount!=0)
            temp *= parentColCount+i-1;
        
        ret += temp;
        ret %= H_MOD;
    }

    //printf("%d,%d=%d\n", parentColCount, remain, ret);
    return ret;
}

void initCache(){
    memset(gCache, -1, sizeof(gCache));
}

int  solve(){    
    return combination(0, gN);
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
    char fn[] = "poly.in";
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

