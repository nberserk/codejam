// algospot
// quaantize
// http://algospot.com/judge/problem/read/QUANTIZE

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
#define H_MAX 987654321;
bool gDebug;
int gN;
int cache[101];

typedef long long ll;
void check(bool ret);

int doSolve(int column){
    if(column==1)
        return 1;
    if(column==2)
        return 2;

    int&ret = cache[column];
    if(ret!=-1)
        return ret;

    ret = doSolve(column-2) + doSolve(column-1);
    ret %= 1000000007;
    return ret;
}

int solve(){
    memset(cache, -1, sizeof(cache));
    return doSolve(gN);
}

void check(bool ret){
    if (ret==false) {
        printf("failed\n");
    }
}


void test(){    
}


int main(){
    char fn[] = "tiling2.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    int count, p,j;    
    scanf("%d", & count);
//    a[0] =1; a[1]=4; a[2] =6;
//    check(calcError(0, 3)==13);
    test();

    for (p=0; p<count; p++) {
        scanf("%d", &gN);
        
        int maxV = solve();
        printf("%d\n", maxV);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

