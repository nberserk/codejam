// http://www.codechef.com/problems/RRSTONE
// category : 

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
int gN, gK;
ll gn[100000];

ll getMax(){
    ll maxV = -1;
    for (int i = 0; i < gN; i++){
        if (gn[i]>maxV){
            maxV=gn[i];
        }
    }
    return maxV;
}

void process(ll m){
    for (int i = 0; i < gN; i++){
        gn[i] = m-gn[i];
    }
}

void solve(){
    if (gK!=0) {
        ll m = getMax();
        process(m);
        if (gK%2==0){
            m = getMax();
            process(m);
        }    
    }

    // print
    for (int i = 0; i < gN; i++){
        printf("%lld ", gn[i]);
    }    
    printf("\n");
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
    
    //test();

    // handling input
    int count=1;
    if (gDebug){
        scanf("%d", &count);
    }    
    for (int p=0; p<count; p++) {
        scanf("%d%d", &gN, &gK);
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

