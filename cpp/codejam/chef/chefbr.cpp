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
#define H_MOD 10000000
typedef long long ll;


void check(bool ret);

bool gDebug;
int gN;
int gn[100];
int gWay[100];

void solve(){
    vector<pair<int,int>> pair; // endIndex, startIndex
    for (int i = 0; i < gN; i++){        
        if (gn[i]>0)
            continue;

        for (int j = i+1; j < gN; j++){
            if (gn[i]==gn[j]*-1){
                pair.push_back(make_pair(j,i));
            }
        }
    }

    sort(pair.begin(), pair.end());

    int prev=-1;    
    for (int i = 0; i < pair.size(); i++){
        int e = pair[i].first;
        int s = pair[i].second;
        if (prev!=-1){
            for (int j = prev+1; j < e; j++){
                gWay[j] = gWay[j-1];
            }
        }
        if (s==0) {
            gWay[e]=1 + gWay[e-1];
        }else{
            gWay[e] = gWay[s-1] + gWay[e-1];            
        }
        gWay[e] %= 1000000007;
        printf("%d=%d\n", e, gWay[e]);
        prev = e;
    }
    if (prev==-1){
        prev=0;
    }
    printf("%d\n", gWay[prev]);
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
    char fn[256] = __FILE__".in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
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
            scanf("%d", gn+i);
            gWay[i]=1;
        }
        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

