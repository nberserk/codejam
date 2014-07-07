// http://algospot.com/judge/problem/read/KLIS

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
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
}

int recon(int idx, int r){
    if(r==0)
        return 1;

    int c = 0;
    for(int i=idx+1;i<gN;i++){
        if(gCache[i+1]==r-1 && gn[i] > gn[idx]){
            c += recon(i+1, r-1);
        }
    }

    gCount[idx] = c;
    printf("gCount-%d=%d\n", idx, c);
    return c;
}

void reconstruct(int i, string& r){
    if (i>=gN)
        return;

    if(i!=-1){
        stringstream ss;
        ss << gn[i] << " ";
        r += ss.str();
    }
    
    for( int j=i+1;j<gN;j++){
        if(gCache[j+1] == gCache[i+1]-1){
            reconstruct(j, r);
            break;
        }
    }    
}

void solve(){
    resetCache();
    int v = lis(-1);

    recon(-1, v);

    string r;
    reconstruct(-1, r);
    
    printf("%d\n", v-1);
    printf("%s\n", r.c_str());
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

