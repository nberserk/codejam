// http://algospot.com/judge/problem/read/MATCHORDER
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

bool gDebug;
int gN;
int gUs[100];
int gOther[100];

int solve(){
    sort(gUs, gUs+gN, std::greater<int>());
    sort(gOther, gOther+gN, std::greater<int>());

    int idxUs=0;
    int count=0;
    int idxOther = 0;
    while(true){
        if (idxUs == gN || idxOther==gN)
            break;
        
        if (gUs[idxUs]>=gOther[idxOther]){
            count++;
            idxUs++;
            idxOther++;
            continue;
        }else
            idxOther++;
    }
    
    return count;
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
    char fn[] = "matchorder.in";
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
            scanf("%d", gOther+j);
        }
        for ( j = 0; j < gN; j++){
            scanf("%d", gUs+j);
        }        
        int r = solve();
        printf("%d\n", r);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

