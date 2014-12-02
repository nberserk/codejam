// http://algospot.com/judge/problem/read/TILING
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
#define MAX_CACHE 32000000
short gWay[MAX_CACHE];


short ways(int n){
    if (n<MAX_CACHE) {
        short& ret = gWay[n];
        if(ret!=-1)
            return ret;
    }

    short r[3];
    r[0]=gWay[MAX_CACHE-1];
    r[1] = gWay[MAX_CACHE-2];
    r[2] = gWay[MAX_CACHE-3];
    int t;
    for (int i = MAX_CACHE; i < n; i++){
        t = r[0] + 2*r[1] + 4*r[2];
        t %=9901;
        r[2]=r[1];
        r[1]=r[0];
        r[0] = t;
    }
    
    return r[0];
}

int solve(){
    int n = gN;
//    if (n>=9901) {
//        n%=9901;
//        n++;
//    }
    return ways(n);
}

void check(int expected, int actual){
    if (expected!=actual) {
        printf("failed expected=%d, actual=%d\n", expected, actual);
    }
}


void test(){
    check(20, gWay[3]);
   check(52, gWay[4]);
    check(ways(1), ways(1000000000));
    //check(ways(2), ways(9902));
}

int main(){
    char fn[] = "tiling.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }

    //memset(gWay, -1, sizeof(gWay));
    gWay[0]=1;
    gWay[1]=3;
    gWay[2]=10;
    
    for (int i=3; i<MAX_CACHE; i++) {
        int t = gWay[i-1] + 2*gWay[i-2] + 4* gWay[i-3];
        t %= 9901;
        gWay[i] = t;
    }
    
    //test();

    // handling input
    int count, p,j,k;
    scanf("%d", &count);
    for (int p = 0; p < count; p++){

        scanf("%d", &gN);
        gN--;

        int r = solve();
        printf("%d\n", r);
    }    
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}





