// algospot
// PI 
// http://algospot.com/judge/problem/read/PI

#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string.h>
#include <limits>

using namespace std;
bool gDebug;
int N;
int n[10000];
int cache[10000];

typedef long long ll;

bool isSame(int idx, int size){
    int v = n[idx];
    for(int i=1;i<size;i++){
        if(v!=n[i+idx])
            return false;
    }
    return true;
}

bool isIncDec(int idx, int size){
    int diff = n[idx] - n[idx+1];
    for(int i=1;i<size-1;i++){
        if(diff != n[idx+i]-n[idx+i+1] )
            return false;
    }
    if(diff*diff!=1)
        return false;
    return true;
}

bool isSameDiff(int idx, int size){
    int diff = n[idx] - n[idx+1];
    for(int i=1;i<size-1;i++){
        if(diff != n[idx+i]-n[idx+i+1] )
            return false;
    }
    return true;
}

bool isTwo(int idx, int size){
    int f = n[idx];
    int s = n[idx+1];
    bool first = true;
    for(int i=2;i<size;i++){
        if(first && n[idx+i]!=f){
            return false;
        }
        if(!first && n[idx+i]!= s){
            return false;
        }
        first= !first;
    }
    return true;
}

int classify(int idx, int size){
    bool isAllSame = true;
    int n0=n[idx];    
    for(int i=1;i<size;i++){
        if(n0!=n[idx+i]){
            isAllSame = false;
            break;
        }        
    }
    if(isAllSame)
        return 1;

    bool isSameDiff = true;
    int firstDiff = n[idx]-n[idx+1];
    for(int i=1;i<size-1;i++){
        if(firstDiff != n[idx+i] - n[idx+i+1]){
            isSameDiff=false;
            break;
        }
    }

    if(isSameDiff && firstDiff*firstDiff == 1)
        return 2;

    bool isTwoNumberOnly = true;
    for(int i=0;i<size-2;i++){
        if(n[idx+i] !=n[idx+i+2]){
            isTwoNumberOnly = false;
            break;
        }
    }
    if(isTwoNumberOnly)
        return 4;

    if(isSameDiff)
        return 5;

    return 10;    
}


int solve(int idx){
    if (idx==N) {
        return 0;
    }
    if(idx>N-3)
        return 987654321;

//    if (idx >=N) {
//        printf("dddd");
//    }

    int& ret = cache[idx];
    if(ret!=-1){
        return ret;
    }

    int i;
    ret = 987654321;
    for(i=3;i<=5;i++){
        if(idx+i>N)continue;
        //int cost = classify(idx,i);
        if(isSame(idx, i)){
            ret=min(ret, 1+solve(idx+i));
        }
        if(isIncDec(idx, i)){
            ret=min(ret, 2+solve(idx+i));
        }
        if(isTwo(idx,i)){
            ret = min(ret, 4+solve(idx+i));
        }
        if(isSameDiff(idx,i)){
            ret = min(ret , 5+solve(idx+i));
        }
        ret = min(ret, 10+solve(idx+i));
        //ret = min(ret, cost+solve(idx+i));
    }
    
    //printf("%d=%d\n", idx,ret);
    return ret;
}

void printStrange(){
    printf("failed\n");
}


void test(){
    
    n[0] = 3; n[1]=2; n[2] = 3;
    if ( isTwo(0,3) != true){
        printStrange();
    }
    
    n[0] = 3; n[1]=2; n[2] = 1; n[3] = 2;
    if ( isSameDiff(0, 3) != true)
        printStrange();
    
    if ( isIncDec(0, 3) != true)
        printStrange();
    
    n[0] = 3; n[1]=2; n[2] = 3; n[3] = 1; n[4]=3; n[5]=1; n[6] = 3;
    if (isTwo(0, 3)!=true) {
        printStrange();
    }
    
    if (isTwo(2, 5)!=true) {
        printStrange();
    }

    
    if (isTwo(0, 4)!=false) {
        printStrange();
    }
    
    
}


int main(){
    char fn[] = "pi.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    int count, p,j;
    char tt[10001];
    scanf("%d", & count);
    test();

    for (p=0; p<count; p++) {
        scanf("%s", tt);
        N = strlen(tt);
        for(j=0;j<N;j++){
            n[j] = tt[j] - '0';
        }

        memset(cache,-1, sizeof(cache));
        //mark();
        int maxV = solve(0);
        //endMark();
        printf("%d\n", maxV);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

