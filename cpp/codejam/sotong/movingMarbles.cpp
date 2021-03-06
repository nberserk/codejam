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
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
typedef long long ll;

bool gDebug;
ll gN;
ll gn[32000];


void solve(){
    ll sum =0;
    for (int i = 0; i < gN; i++){
        sum += gn[i];
    }
    
    if (sum%gN!=0){
        printf("-1\n");
        return;
    }

    ll desired = sum/gN;
    ll ret=0;
    for (int i = 0; i < gN; i++){
        if (gn[i]<desired){
            ret += desired-gn[i];
        }
    }    

    printf("%d\n", ret);
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
    //h_generateRandomMap(100);
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        for (int i = 0; i < gN; i++){
            scanf("%d",&gn[i]);            
        }        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

