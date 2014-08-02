// http://algospot.com/judge/problem/read/MEETINGROOM
// category: greedy

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

struct Meeting{
    int start, end;
    int nth;
};

bool gDebug;
int gN;
Meeting gn[200];
int gSelected[100];

bool operator < (const Meeting& a, const Meeting& b){
    if (a.end != b.end) {
        return a.end < b.end;
    }
    
    return a.start < a.start;
}

void solve(){
    memset(gSelected, -1, sizeof(gSelected));

    // sort gn by end time
    sort(gn, gn+gN);
    int cur = -1;    
    for (int i=0; i<gN; i++) {
        if (gn[i].start<cur)
            continue;
        if(gSelected[gn[i].nth]!=-1)
            continue;
        gSelected[gn[i].nth] = i;
        cur = gn[i].end;
    }

    bool impossible=false;
    for (int i = 0; i < gN/2; i++){
        if (gSelected[i]==-1){
            impossible=true;
            break;
        }
    }

    //
    if (impossible){
        printf("IMPOSSIBLE\n");
    }else{
        printf("POSSIBLE\n");
        int idx;
        for (int i = 0; i < gN/2; i++){
            idx = gSelected[i];
            printf("%d %d\n",gn[idx].start, gn[idx].end);
        }
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
    char fn[] = "meetingroom.in";
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
        scanf("%d", &gN);
        n=0;
        for ( j = 0; j < gN; j++){
            scanf("%d %d %d %d", &gn[n].start, &gn[n].end
                  , &gn[n+1].start, &gn[n+1].end);
            gn[n].nth = j;
            gn[n+1].nth = j;
            n+=2;
        }
        gN *=2;
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

