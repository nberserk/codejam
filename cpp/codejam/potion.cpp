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
int gR[1000];
int gP[1000];

// a>=b, 
int gcd(int a, int b){
    if (b==0){
        return a;
    }
    return gcd(b, a%b);
}

void solve(){
    
    if (gN==1) {
        printf("0\n");
        return;
    }
    
    int g = gcd(gR[0], gR[1]);
    for (int i=1; i<gN-1; i++) {
        int small = g > gR[i] ? gR[i] : g;
        int large = g <= gR[i] ? gR[i] : g;
        g = gcd(large, small);
    }
    //printf("gcd=%d\n", g);
    if (g!=1) {
        for (int i=0; i<gN; i++) {
            gR[i]/=g;
        }
    }
    
    // ratio
    float maxRatio=-1.0;
    int maxIdx;
    for (int i=0; i<gN; i++) {
        float r = gP[i]/(float)gR[i];
        if (r > maxRatio) {
            maxRatio = r;
            maxIdx = i;
        }
    }
    
    
    bool s = true;
    for (int j=0; j<gN; j++) {
        if (j==maxIdx) {
            continue;
        }
        float f = maxRatio*gR[j];
        int i = f;
        if (f-i >= 0.0001f) {
            s=false;
            break;
        }
    }
    if (s) {
        for (int j=0; j<gN; j++) {
            float desired = maxRatio*(float)gR[j];
            int diff = (int)desired - gP[j];
            printf("%d ", diff);
        }
        printf("\n");
        return;
    }
    int next = int(maxRatio + 0.999f);
    for (int i=0; i<gN; i++) {
        int desired = next*gR[i];
        int diff = desired - gP[i];
        printf("%d ", diff);
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
    int r = gcd(3, 12);
    check(3, r);
    r = gcd(7, 14);
    check(7, r);
    r = gcd(7, 3);
    check(1, r);
    
    float f = 2.0;
    int i = 2;
    if (f -i < 0.0001f) {
        printf("same\n");
    }else{
        printf("diff\n");
    }
    
    
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
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {
        
        scanf("%d", &gN);
        for (int i = 0; i < gN; i++){
            scanf("%d", &gR[i]);
        }
        for (int i = 0; i < gN; i++){
            scanf("%d", &gP[i]);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

