// http://algospot.com/judge/problem/read/MORSE

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
#define H_EPSILON 0.000001
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
int gM, gN, gK;
ll gCache[101][101];
int gParent[100][501];


ll combination(int n, int m){
    if(n==0 && m==0)
        return 0;
    if(n==0 || m==0)
        return 1;
    
    ll& ret = gCache[n][m] ;
    if(ret != -1)
        return ret;
    
    ret =  combination(n-1, m) + combination(n,m-1);
    printf("%d,%d=%lld\n", n,m,ret);
    return ret;
}


void reconstruct(int n, int m,ll k, string& ret){
    if(k==0)
        return;
    if(n>0){
        ll half = combination(n-1,m);
        if(k>half){
            ret += 'o';
            reconstruct(n, m-1, k-half, ret);
        }else{
            ret += '-';
            reconstruct(n-1, m, k, ret);
        }
    }else{
        for (int i=0;i<m;i++)
            ret += 'o';
    }    
}

void solve(){
    ll maxValue = combination(gN, gM);
    //printf("%d", maxValue);
    string ret;
    reconstruct(gN, gM, gK, ret);

    printf("%lld-%s\n", maxValue, ret.c_str());
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
    gN=gM=1;
    gK = 1;
    check(2, (int)combination(1,1));
    string t;
    reconstruct(1,1,1,t);
    
    check( strcmp(t.c_str(), "-o") ==0);
    t.clear();
    reconstruct(1,1,2,t);
    check( strcmp(t.c_str(), "o-")==0);
    
    check(3, combination(1, 2));
    t.clear();
    reconstruct(1, 2, 1, t);
    check(strcmp(t.c_str(),"-oo")==0);
    
    t.clear();
    reconstruct(1, 2, 3, t);
    check(strcmp(t.c_str(),"oo-")==0);

   
}


int main(){
    char fn[] = "morse.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
  
    memset(gCache, -1 , sizeof(gCache));
    //test();
    int count, p,j,k,n, i;
    scanf("%d", &count);
    
    for (p=0; p<count; p++) {
        scanf("%d %d %d", &gN , &gM, &gK);
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

