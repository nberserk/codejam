// http://www.codechef.com/problems/CHEFBR

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
#define H_MOD 1000000009
typedef long long ll;


void check(bool ret);

bool gDebug;
int gN;
ll gn[101];
ll gWay[101][101];

ll ways(int s, int e){
    if (s>=e){
        return 1;
    }
    ll& ret = gWay[s][e];
    if (ret!=-1){
        return ret;
    }
    ret = ways(s, e-1);

    for (int i = s; i < e; i++){
        if (gn[e]>0 && gn[e]==-gn[i]){
            ll n = ways(s,i-1);
            ll m = ways(i+1, e-1);
            n = (n*m)%H_MOD;
            ret = (ret + n)%H_MOD;
        }
    }
    //printf("%d,%d=%lld\n", s,e,ret);
    return ret;
}

void solve(){
    //int s = sizeof(long);
    //memset(gWay,-1,sizeof(gWay));
    for (int i = 0; i < gN; i++){
        for (int j=0; j<gN; j++) {
            gWay[i][j]=-1;
        }
    }
    printf("%lld\n", ways(0,gN-1));
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

void check(char expected, char actual){
    if (expected!=actual) {
        printf("failed expected=%c, actual=%c\n", expected, actual);
    }
}

void test(){    
}

int main(){
    string fn = __FILE__;
    size_t pos = fn.find(".cpp");
    fn = fn.substr(0,pos) + ".txt";
    if (access(fn.c_str(), F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn.c_str(), "r", stdin);
    }
    
    test();

    // handling input
    int count=1;
    if (gDebug){
        scanf("%d", &count);
    }    
    for (int p=0; p<count; p++) {
        scanf("%d", &gN);

        for (int i = 0; i < gN; i++){
            scanf("%lld", gn+i);
        }
        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
}

