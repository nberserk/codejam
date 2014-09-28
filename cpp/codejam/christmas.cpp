// http://algospot.com/judge/problem/read/CHRISTMAS
// category: partial sum

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
#include <limits>
#include <set>
#include <iostream>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
#define H_MOD 10000000
typedef long long ll;
typedef unsigned int ui;

void check(bool ret);

int gN, gK;
int gn[100000];
int gSum[100000];
vector<vector<int>> pairs;
bool gDebug;
int gCount, gUniqueCount;
int gLastIndex;

int partialSum(int a, int b){
    if (a==0) return gSum[b];
    return gSum[b]-gSum[a-1];
}

void solve(){
    gCount = gUniqueCount=0;
    gLastIndex=-1;
    // calc partial sum
    pairs = vector<vector<int>>(gK);
    pairs[0].push_back(-1);
    int sum = 0;
    for (int i = 0; i < gN; i++){
        sum = ( sum + gn[i])%gK;
        pairs[sum].push_back(i);
    }
    
    int count =0;
    int size;
    for (int i = 0; i < gK; i++){
        size = pairs[i].size();
        if (size >=2) {
            count += size*(size-1)/2;
            count %= 20091101;
        }
    }
    gCount = count;
}

void check(bool ret){
    if (ret==false) {
        printf("failed\n");
    }
}

void check(ui expected, ui actual){
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
    char fn[] = "christmas.in";
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
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d %d", &gN, &gK);
        for (int i=0;i<gN;i++)
            scanf("%d", gn+i);
        solve();
        printf("%d %d\n", gCount, gUniqueCount);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

