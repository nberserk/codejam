// https://algospot.com/judge/problem/read/FIRE
// category: ??

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
int gN, gP;
int gCost[100001];
int gMin [100001];


void solve(){
    int i;
    for (i = gN-gP+1; i <=gN; i++){
        gMin[i] = gCost[i];
    }
    for (i = gN-gP; i >=0; i--){
        gMin[i]=gMin[i+1];
        for (int j = 2; j <= gP; j++){
            gMin[i] = min(gMin[i], gMin[i+j]);
        }
        gMin[i]+= gCost[i];
    }
    
    printf("%d\n", gMin[0]);
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
    for (int i = 1; i <= 10; i++){
        gCost[i]=i;        
    }
    gN =10;
    gP = 3;
    solve();
    check(10, gMin[10]);
    check(9, gMin[9]);
    check(8, gMin[8]);
    check(15, gMin[7]);
    check(14, gMin[6]);    
}

int main(){
    char fn[] = "fire.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    //test();
    gCost[0] = 0;
    // handling input
    int count, p,j,k, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d %d", &gN, &gP);
        for (int i = 1; i <= gN; i++){
            scanf("%d", gCost+i);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

