// http://algospot.com/judge/problem/read/KLIS

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
#define H_EPSILON 0.000001
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
int gN, gK;
int gSequence[500];
ll gCache[501][501];
int gParent[100][501];

// 
int lis(int start, int p){
    if(start >=gN)
        return 0;

    int& ret = gCache[start][p+1];
    if(ret!=-1)
        return ret;

    ret = lis(start+1, p);
    int cur;
    if(p==-1 || gSequence[start] > gSequence[p]){
        cur = 1 + lis(start+1, start);
        if(cur> ret){
            ret = cur;
        }
    }

    return ret;
}

void solve(){
    gN=1;
    g
    
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
    char fn[] = "klis.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
  
    //test();
    int count, p,j,k,n, i;
    scanf("%d", &count);
    
    for (p=0; p<count; p++) {
        scanf("%d %d %d", &gN ,&gK);
        for (i=0;i<gN;i++){
            scanf("%d", gSequence+i);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

