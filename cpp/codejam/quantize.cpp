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
int gN, gS;
int a[101];
int gSum[102];
int cache[10][101];

typedef long long ll;
void check(bool ret);

// s <= i <e
int calcError(int s, int e){
    if (s==e) return 0;
    
    int size = e-s;
    int sum=a[s];
    int i;
    for( i=s+1;i<e;i++){
        sum+=a[i];
    }
    float fsize = size;
    float avg = (float)sum/fsize;
    avg = floor(avg+0.5f);
    int iavg = (int)avg;
    int err=0, temp;
    for( i=s;i<e;i++){
        temp = a[i]-iavg;
        err += temp*temp;
    }

    return err;
}

// s <= i <e
int calcError2(int s, int e){
    if (s==e) return 0;
    
    int size = e-s+1;
    int sum=a[s];
    int i;
    for( i=s+1;i<=e;i++){
        sum+=a[i];
    }
    float fsize = size;
    float avg = (float)sum/fsize;
    avg = avg+0.5;
    int iavg = (int)avg;
    int err=0, temp;
    for( i=s;i<=e;i++){
        temp = a[i]-iavg;
        err += temp*temp;
    }

    return err;
}


int doSolve2(int region, int idx){
    if(region>=gN-idx)
        return 0;
    if(idx>=gN)
        return H_MAX;
    int& ret = cache[region][idx];
    if(ret!=-1){
        return ret;
    }

    if(region==1){
        ret = calcError2(idx,gN-1);
    }else{
        int t;
        for(int i=idx;i<gN;i++){
            t = calcError2(idx,i);
            ret = min(ret, t+doSolve2(region-1, i+1));
        }
    }
    return ret;
}

int quantize(int parts, int from){
    if (from==gN) {
        return 0;
    }
    
    if (parts==0) {
        return H_MAX;
    }
    
    int& ret = cache[parts][from];
    if (ret!=-1) {
        return ret;
    }
    
    ret = H_MAX;
    for (int partSize=1; from + partSize <=gN; partSize++   ) {
        ret = min(ret, calcError2(from, from+partSize-1) + quantize(parts-1, from+partSize));
    }
    
    
    return ret;
}



int doSolve(int region, int idx){
    if(region>=gN-idx)
        return 0;

    int& ret = cache[region][idx];
    if(ret!=-1){
        //printf("hit cache : %d,%d=%d\n", region, idx,ret);
        return ret;
    }

    ret = H_MAX;
    if(region==1){
        ret = calcError(idx, gN);
    }else{
        int temp;
        for(int e=idx+1;e<gN;e++){
            temp = calcError2(idx,e);
            //check(temp==calcError(idx, e));
            ret = min(ret, temp+doSolve(region-1, e));
        }        
    }
    
    printf("%d,%d=%d\n", region, idx,ret);
    return ret;
    
}

int solve(int region, int idx){
    std::sort(a, a+gN);
    memset(cache, -1, sizeof(cache));
    int sum = 0;
    for(int i=0;i<gN;i++){
        sum += a[i];
        gSum[i] = sum;
    }
    gSum[gN] = gSum[gN-1];
    return quantize(region, idx);
}

void check(bool ret){
    if (ret==false) {
        printf("failed\n");
    }
}


void test(){
    
    gN =1;
    a[0] =4;
    
//    check(calcError(0, 1) ==0);
//    check(solve(1,0) == 0);
    
    gN=2;
    a[1] = 2;
    //check(calcError(0, 2) == 2);
    
    check(solve(1,0) ==2);
    check(solve(2,0) == 0);
    
    gN=3;
    a[2] = 7;
    check(solve(2, 0) ==2);
    
    gN=4;
    a[3] = 8;
    check(solve(3, 0) ==1);
    
    
    a[0] =1; a[1]=4; a[2] =6;
    check(calcError2(0, 2)==13);
    a[2] = 5;
    check(calcError2(0, 2)== 9);
    
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
//    a[0] =1; a[1]=4; a[2] =6;
//    check(calcError(0, 3)==13);
    test();

    for (p=0; p<count; p++) {
        scanf("%d %d", &gN, &gS);
        for(j=0;j<gN;j++){
            scanf("%d",a+j);
        }
        
        //mark();
        int maxV = solve(gS,0);
        //endMark();
        printf("%d\n", maxV);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

