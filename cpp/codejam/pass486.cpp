#include <algorithm>
#ifdef _MSC_VER
#include <io.h>
#define F_OK 0
#else
#include <unistd.h>
#endif
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
#define H_MAX 10000000
#define H_MIN -987654321
#define H_EPSILON 0.000001
typedef long long ll;



void check(bool ret);

bool gDebug;
int gN;// #count
int gLow, gHigh;
int gCache[H_MAX+1];
int gMinFactor[H_MAX+1];

void precalcMinFactor(){
    for (int i = 2; i <= H_MAX; i++){
        gMinFactor[i]=i;
    }
    
    int sqrtn = sqrt(H_MAX);
    for (int i = 2; i <= sqrtn; i++){
        for (int j = i*i; j < H_MAX+1; j+=i){
            if (gMinFactor[j]==j){
                gMinFactor[j]=i;
            }
//            printf("%d\n", j);
        }
    }
}

void eratosthenes(){
    for (int i = 1; i <= H_MAX; i++)
        gCache[i]=1;
    
    for (int i = 2; i <= H_MAX; i++){
        for (int j = i; j < H_MAX+1; j+=i){
            gCache[j]++;
            //            printf("%d\n", j);
        }
    }
}

// not working
int countUsingMinFactor(int n){
    int& r = gCache[n];
    if (r!=0) {
        return r;
    }

    r=1;
    while(n>1){
        n /=gMinFactor[n];
        r++;
    }
    return r;
}

// time out
//int countBruteforce(int n){
//    int& r = gCache[n];
//    if (r!=0) {
//        return r;
//    }
//    
//    r=2;
//    float half = sqrtf(n);
//    int halfn = half;
//    for (int i=2; i<=halfn; i++) {
//        if (n%i==0) {
//            r+=2;
//        }
//    }
//    if (half-halfn < 0.0001) {
//        r--; // when n is 9
//    }
//    //printf("%d=%d\n", n, r);
//    return r;
//}

void solve(){
    int r = 0;
    for (int i = gLow; i < gHigh+1; i++){
        if (gCache[i]==gN){
            r++;
        }
    }
    printf("%d\n", r);
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
    eratosthenes();
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d %d %d", &gN, &gLow, &gHigh);        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

