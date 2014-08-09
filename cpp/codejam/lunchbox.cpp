// http://algospot.com/judge/problem/read/LUNCHBOX
// category: greedy

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <map>
#include <time.h>
#include <string>
#include <sstream>
#include <string.h>
#include <stack>
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


struct Lunch{
    int m, e;    
};

bool gDebug;
int gN;
Lunch gLunch[10000];

bool operator < (const Lunch& left, const Lunch& right){
    return left.e > right.e;
}

int solve(){
    sort(gLunch, gLunch+gN);
    // calc remaining m
    int remainM = 0;    
    for (int i = 0; i < gN; i++){    
        remainM += gLunch[i].m;        
    }

    // calc
    int ret = remainM;
    int maxWait=0, cur;
    for (int i = 0; i < gN; i++){
        remainM -= gLunch[i].m;
        cur = gLunch[i].e - remainM;
        if (cur<0){
            continue;
        }
        maxWait = max(maxWait, cur);
    }

    return ret + maxWait;    
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
    char fn[] = "lunchbox.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    test();

    // handling input
    int count, p,j,k,n, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        for ( j = 0; j < gN; j++){            
            scanf("%d", &gLunch[j].m);
        }
        for ( j = 0; j < gN; j++){
            scanf("%d", &gLunch[j].e);
        }        
        int r = solve();
        printf("%d\n", r);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

