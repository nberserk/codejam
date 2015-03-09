// http://www.codechef.com/problems/CNOTE
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
int gN, gX, gY, gK, gNeed;
int gP[100000];
int gC[100000];


void solve(){
    int required = gX - gY;
    int ret=-1;
    for (int i = 0; i < gN; i++){
        if (gC[i]<=gK && gP[i]>=required){
            ret=i;
            break;
        }        
    }
    if (ret==-1){
        printf("UnluckyChef\n");    
    }else{
        printf("LuckyChef\n");
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
    int count=1;
    scanf("%d", &count);

    for (int p=0; p<count; p++) {
        scanf("%d %d %d %d", &gX, &gY, &gK, &gN);

        for (int i = 0; i < gN; i++){
            scanf("%d%d", gP+i, gC+i);
        }
        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
}

