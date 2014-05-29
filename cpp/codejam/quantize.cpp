// algospot
// quaantize
// http://algospot.com/judge/problem/read/QUANTIZE

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
#define H_MAX 987654321;
bool gDebug;
int n, s;
int a[100];
int cache[10][100];

typedef long long ll;

int calcError(int s, int e){
    int size = e-s;
    if(size==1)return 0;
    int sum=a[s];
    int i;
    for( i=s+1;i<e;i++){
        sum+=a[i];
    }
    float fsize = size;
    float avg = (float)sum/fsize;
    avg = floor(avg+0.5);
    int iavg = (int)avg;
    int err=0, temp;
    for( i=s;i<e;i++){
        temp = a[i]-iavg;
        err += temp*temp;
    }

    return err;
}

int doSolve(int region, int idx){
    if(region>=n-idx)
        return 0;

    int& ret = cache[region][idx];
    if(ret!=-1){
        printf("hit cache : %d,%d=%d\n", region, idx,ret);
        return ret;
    }

    ret = H_MAX;
    if(region==1){
        ret = calcError(idx, n);
    }else{
        int temp;
        for(int e=idx+1;e<n;e++){
            temp = calcError(idx,e);
            ret = min(ret, temp+doSolve(region-1, e));
        }        
    }
    
    printf("%d,%d=%d\n", region, idx,ret);
    return ret;
    
}

int solve(int region, int idx){
    std::sort(a, a+n);
    memset(cache, -1, sizeof(cache));
    return doSolve(region, idx);
}

void check(bool ret){
    if (ret==false) {
        printf("failed\n");
    }
}


void test(){
    
    n =1;
    a[0] =4;
    
//    check(calcError(0, 1) ==0);
//    check(solve(1,0) == 0);
    
    n=2;
    a[1] = 2;
    //check(calcError(0, 2) == 2);
    
    check(solve(1,0) ==2);
    check(solve(2,0) == 0);
    
    n=3;
    a[2] = 7;
    check(solve(2, 0) ==2);
    
    n=4;
    a[3] = 8;
    check(solve(3, 0) ==1);
    
    
    a[0] =1; a[1]=4; a[2] =6;
    check(calcError(0, 3)==13);
    
}


int main(){
    char fn[] = "quantize.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    int count, p,j;    
    scanf("%d", & count);
    a[0] =1; a[1]=4; a[2] =6;
    check(calcError(0, 3)==13);
    //test();

    for (p=0; p<count; p++) {
        scanf("%d %d", &n, &s);
        for(j=0;j<n;j++){
            scanf("%d",a+j);
        }
        
        //mark();
        int maxV = solve(s,0);
        //endMark();
        printf("%d\n", maxV);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

