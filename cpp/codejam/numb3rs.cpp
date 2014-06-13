// http://algospot.com/judge/problem/read/NUMB3RS

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
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
int gN, gDay, gTargetCount ,gTarget[50], gStart, gM[50][50];
double gP[50][50];
char gPriority[5];
double gCache[101][51];


void initCache(){
//    memset(gCache, -1, sizeof(gCache));
}

double doSolve(int day, int dest){
    if(day==0)
        return gP[gStart][dest];
    double& ret = gCache[day][dest] ;
    if(ret >=0)
        return ret;

    ret =0;
    for(int i=0;i<gN;i++){
        ret += gP[i][dest]*doSolve(day-1,i);
    }

    //printf("%d,%d=%f", day,dest,ret);
    return ret;
}

void solve(){

    // calc g
    int i,j,k;
    for (i=0;i<gN;i++){
        int n=0;
        for (j=0;j<gN;j++){
            if (gM[i][j]==1)
                n++;
        }
        for (j=0;j<gN;j++){
            gP[i][j] = gM[i][j]==1? 1/(double)n : 0;
        }
    }
    
    // init cache
    for (i=0; i<gDay; i++) {
        for (j=0;j<gN;j++)
            gCache[i][j] = -1;
    }

    for (i=0;i<gTargetCount;i++){
        double v = doSolve(gDay-1, gTarget[i]);
        printf("%.10f ", v);
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
   
}


int main(){
    char fn[] = "numb3rs.in";
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
        scanf("%d %d %d", &gN, &gDay, &gStart);
        for(j=0;j<gN;j++){
            for(k=0;k<gN;k++){
                scanf("%d", &gM[j][k] );                
            }
        }

        scanf("%d", &gTargetCount);
        for(j=0;j<gTargetCount;j++){
            scanf("%d", gTarget+j);
        }
                
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

