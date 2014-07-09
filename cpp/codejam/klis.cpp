// http://algospot.com/judge/problem/read/KLIS

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


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
int gN, gK;
int gn[500];
int gCache[501];
int gCount[501];
int gLis;
vector<vector<int>> gFinal;

// 
int lis(int start){
    if(start >=gN)
        return 0;

    int& ret = gCache[start+1];
    if(ret!=-1)
        return ret;

    ret =1;
    int cur;
    for( int i=start+1;i<gN;i++){
        if(start==-1 || gn[i] > gn[start] ){
            cur = 1+lis(i);
            if(cur > ret){
                ret = cur;
            }
        }            
    }

    return ret;
}

void resetCache(){
    memset(gCache, -1, sizeof(gCache));
    memset(gCount, -1, sizeof(gCount));
}

int count(int start){
    int& ret = gCount[start+1];
    if (ret!=-1) {
        return ret;
    }
    int lis = gCache[start+1];
    if (lis==1) {
        ret =1;
    }else{
        ret = 0;
        for (int i=start+1; i<gN; i++) {
            if(start==-1 || gn[i]>gn[start]){
                if(lis == gCache[i+1]+1){
                    // tricky...
                    ret = min<long long>(2000000000,(long long)ret +  count(i));
//                    ret %= numeric_limits<int>::max()/2;
                }
            }        
        }
    }

    //printf("%d=%d\n", start, ret);
    return ret;
}

void reconstruct(int start, int k, vector<int>& v){
    if (v.size()==gLis-1) {
        return;
    }
    int lis = gCache[start+1];

    map<int,int> map;
    vector<int> candidates;

    for (int i=start+1; i<gN; i++) {
        if(start==-1 || gn[i]>gn[start]){
            if(lis == gCache[i+1]+1){
                candidates.push_back(gn[i]);
                map.insert(pair<int,int>(gn[i], i));                
            }
        }
    }
    
    //
    sort(candidates.begin(), candidates.end());

    int size = candidates.size();
    int idx;
    for(int i=0;i<size;i++){
        idx = map[candidates[i]];
        if(gCount[idx+1]>=k){
            v.push_back(gn[idx]);
            reconstruct(idx,k,v);
            break;
        }else{
            k-= gCount[idx+1];
        }
    }
}

void solve(){
    resetCache();
    gLis = lis(-1);

    int maxCount = count(-1);
    vector<int> v;
    reconstruct(-1,gK, v);
    
    printf("%d\n", gLis-1);
    int size = v.size();
    for(int i=0;i<size;i++){
        printf("%d ",v[i] );
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


void test(){
    gN=1;
    gn[0] =1;
    resetCache();
    check(1,lis( -1)-1);
    
    gN=2;
    gn[1] = 2;
    resetCache();
    check(2, lis( -1)-1);
    
    gN=3;
    gn[0] = 3;
    gn[1] = 2;
    gn[2]= 1;
    resetCache();
    check(1, lis(-1)-1);
    
    
    
}


int main(){
    char fn[] = "klis.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
  
    test();
    int count, p,j,k,n, i;
    scanf("%d", &count);
    
    for (p=0; p<count; p++) {
        scanf("%d %d", &gN ,&gK);
        for (i=0;i<gN;i++){
            scanf("%d", gn+i);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

