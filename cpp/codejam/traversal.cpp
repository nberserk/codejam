// http://algospot.com/judge/problem/read/TRAVERSAL
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
int gN;
int gPre[100];
int gMid[100];

// both inclusive
void traverse(int s,int e, int s1, int e1){
    if (e-s<=0){
        printf("%d ", gPre[s]);
        return;
    }
    int center = gPre[s];
    int idxCenterMid=-1;
    for (int i = s1; i <= e1; i++){
        if (gMid[i]==center){
            idxCenterMid=i;
            break;
        }
    }
    check(idxCenterMid!=-1);
    int l = idxCenterMid-s1;
    int r = e1-idxCenterMid;

    int ns = 
    
}

void solve(){
    
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
    char fn[] = "traversal.in";
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
        scanf("%d", &gN);
        for (int j = 0; j < gN; j++){
            scanf("%d", gPre+j);
        }
        for (int j = 0; j < gN; j++){
            scanf("%d", gMid+j);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

