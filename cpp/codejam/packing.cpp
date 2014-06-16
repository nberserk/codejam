// http://algospot.com/judge/problem/read/PACKING

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string>
#include <string.h>
#include <stack>
#include <limits>
#include <set>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
int gN, gW, gSize[100], gValue[100];
char gName[100][21];
int gCache[101][1000];
int gNext[101][1000];

void initCache(){
    memset(gCache, -1, sizeof(gCache));
    memset(gNext, -1, sizeof(gNext));
}

int doSolve(int idx, int capacity){
    if(idx==gN || capacity <=0)
        return 0;
    
    int& ret = gCache[idx+1][capacity] ;
    if(ret !=-1)
        return ret;

    int cur, base=0, size=0;
    ret = 0;    
    if(idx!=-1){
        base = gValue[idx];
        ret = base;
        size = gSize[idx];
    }
    for(int i=idx+1;i<gN;i++){        
        if(gSize[i] <= capacity-size){
            cur =  base + doSolve(i,capacity-size);
            if(cur > ret){
                gNext[idx+1][capacity] = i;
                ret = cur;
            }
        }
    }
    
    //printf("%d,%d=%d\n", idx,capacity,ret);
    return ret;
}
vector<string> reconstruct(){
    int count = 0;
    vector<string> ret;
    string out;
    
    int cap = gW;
    int i=gNext[0][cap], next;
    while(i<gN){

        ret.push_back(string(gName[i]));
                    
        next = gNext[i+1][cap];
        cap -= gSize[i];
        i=next;
                
        if(i==-1)
            break;        
    }

    return ret;
}

void solve(){
    initCache();
    int maxValue = doSolve(-1, gW);
    // reconstruct items
    vector<string> ret = reconstruct();
    
    printf("%d %d\n", maxValue, ret.size());
    for (int i=0; i<ret.size(); i++) {
        printf("%s\n", ret[i].c_str());
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


void test(){
    gN = 1;
    gW=10;
    strcpy(gName[0], "dd");
    gSize[0] = 10;
    gValue[0]=10;
    check(10, doSolve(-1, gW));
    vector<string> r = reconstruct();
    check(1, r.size()  );
    check(r[0].compare("dd")==0);
    
   
}


int main(){
    char fn[] = "packing.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }

  
//    test();
    
    int count, p,j,k;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%d %d ", &gN, &gW);
        for(j=0;j<gN;j++){
            scanf("%s %d %d", &gName[j], gSize+j, gValue+j );            
        }
                        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

