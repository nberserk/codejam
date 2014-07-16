// http://algospot.com/judge/problem/read/ZIMBABWE

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
int  gM;
char gE[16];
vector<int> gOrg;
int gCache[501];
int gCount[501];
int gLength[51];


int num(vector<int>& remaining, vector<int>& added){
    if (remaining.size()==0) {
        
        return 1;
    }
    
    int& ret = gCache[][];
    if (ret!=-1) {
        return ret;
    }
    
    
    int size = remaining.size();
    ret = 0;
    int nth = added.size();
    for (int i=0; i<size; i++) {
        if ( remaining[i] > gOrg[nth]) {
            continue;
        }
        
        ret += num(remaining, added);
    }
    
    return ret;
}

void solve(){
    int size = strlen(gE);
    int n;
    for(int i=0;i<size;i++){
        n = gE[i] - '0';
        gOrg.push_back(n);
    }
    
    vector<int> copy(gOrg);
    vector<int> added;
    n = num(gOrg, added);
    
    printf("%d\n", n);
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
    char fn[] = "zimbabwe.in";
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
        scanf("%s %d", gE , &gM);
        solve();        
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

