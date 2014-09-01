// http://algospot.com/judge/problem/read/JOSEPHUS
// category: 

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
int gN, gK;
int gn[1000];

void solve(){

    int live=gN-1;
    memset(gn, -1, sizeof(gn));
    gn[0]=0;

    int idx=0;
    for (int i = 0; i < gN-3; i++){
        int k=gK;
        while (k>0){
            idx++;
            if (idx>=gN){
                idx-=gN;
            }
            if (gn[idx]==-1){
                k--;
            }
        }
        gn[idx]=0;
    }
    
    for (int i = 0; i < gN; i++){
        if (gn[i]==-1){
            printf("%d ", i+1);
        }
    }
    printf("\n");
}

void solve2(){
    list<bool> l(gN, false);
    
    
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
    char fn[] = "josephus.in";
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
    char buf[30], prev[30];
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d %d", &gN, &gK);
        
        solve();        
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

