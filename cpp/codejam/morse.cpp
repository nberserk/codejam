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
long gCache[100][100];
int gParent[100][501];


long doSolve(int n, int m){
    if(n==0 || n==0)
        return 1;
    
    long& ret = gCache[n][m] ;
    if(ret != -1)
        return ret;
    
    ret =  doSolve(n-1, m) + doSolve(n,m-1);    
    //printf("%d,%d=%.4f\n", idx,prev,ret);
    return ret;
}


void reconstruct(int n, int m,int k, string& ret){
    if(k==0)
        return;
    long half = doSolve(n-1,m);
    if(k>half){
        ret += 'o';
        reconstruct(n, m-1, k-half, ret);
    }else{
        ret += '-';
        reconstruct(n-1, m, k, ret);
    }
}

void solve(){
    long maxValue = doSolve(gN, gM);
    string ret;
    reconstruct(gN, gM, gK, ret);

    printf("%s\n", ret.c_str());
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
  
//    test();    

    memset(gCache, -1 , sizeof(gCache));
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

