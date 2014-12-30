// https://algospot.com/judge/problem/read/NERD2
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
int gN;
int gA [50000], gB[50000];

struct compareA{
    bool operator() (const int a,const int o){
        return gA[a]<gA[o];
    }
};

struct compareB{
    bool operator() (const int a,const int o){
        return gB[a]<gB[o];
    }
};

void solve(){
    set<int, compareA> a;
    set<int, compareB> b;
    int ret=1;
    a.insert(0);
    b.insert(0);

    for (int i = 1; i < gN; i++){
        
    } 
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
    set<int, compareA> a;
    gA[0] = 9;
    gA[1] = 8;
    a.insert(0);
    a.insert(1);

    check(8, *a.begin());
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
    int count, p,j,k, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        for (int i = 0; i < gN; i++){
            scanf("%d %d",gA+i, gB+i);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

