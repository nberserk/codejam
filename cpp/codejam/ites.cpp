// http://algospot.com/judge/problem/read/ITES
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
bool gDebug;
ui gPrev=1983;
int gCount;

ui next(){
    gPrev = (gPrev*214013 + 2531011) ;
    return gPrev%10000 +1;
}

void solve(){
    gCount =0;
    gPrev=1983;

    //
    int s = 0, cur;
    queue<unsigned int> q;
    for (int i=0; i<gN; i++) {
        cur = next();
        s+=cur;
        q.push(cur);
  
        while (s>gK) {
            s-= q.front();
            
            q.pop();
        }
        
        if (s==gK) {
            gCount++;
        }
    }
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

    check(8791,  next());
    check(4770, next());
    check(7665, next());
    check(3188, next());
    
}

int main(){
    char fn[] = "ites.in";
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
        scanf("%d %d", &gK, &gN);        
        solve();
        printf("%d\n", gCount);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

