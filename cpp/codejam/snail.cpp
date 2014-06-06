// algospot
// http://algospot.com/judge/problem/read/SNAIL

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string.h>
#include <limits>
#include <set>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
typedef long long ll;

void check(bool ret);


bool gDebug;
int gN, gM;
double gCache[1001][1001]; // max sum from y,x to the end

double doSolve(int n, int m){
    if(n<=0) return 1;
    if(m==0) return 0;
    if(n>2*m)
        return 0;    
        
    double&ret = gCache[n][m];
    if(ret>0.0)
        return ret;

    ret= 0.75*doSolve(n-2, m-1) + 0.25*doSolve(n-1,m-1);    
    return ret;
}

void initCache(){
    int i,j;
    for(i=0;i<1001;i++)
        for(j=0;j<1001;j++)
            gCache[i][j] = -1.0;
    
}

double  solve(){
    //memset(gCache, -1, sizeof(gCache));
    return doSolve(gN, gM);
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

    check(0.75 , doSolve(2,1));
    check(1, doSolve(1,1));
    check(0.9375, doSolve(3,2));
    
}


int main(){
    char fn[] = "snail.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    initCache();
    //test();
    
    int count, p,j,k;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%d %d", &gN, &gM);
                
        double v = solve();
        printf("%.10f\n", v);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

