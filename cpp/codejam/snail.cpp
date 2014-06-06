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

bool gDebug;
int gN;
int gM[100][100];
int gCache[100][100]; // max sum from y,x to the end
int gCachePath[100][100];
int gMax;

typedef long long ll;
void check(bool ret);

int doSolve(int x, int y){
    if(x>y)
        return H_MIN;
    if(y==gN)
        return 0;
    
    int&ret = gCache[x][y];
    if(ret!=-1)
        return ret;

    int cur = gM[y][x];
    ret = max(cur+doSolve(x,y+1), cur+doSolve(x+1,y+1));
    return ret;
}

int countPath(int x, int y, int remainSum){
    if(x>y ) return 0;

    if(y==gN-1){
        if(remainSum==gM[y][x]) return 1;
        else return 0;
    }
    
    int ret = gCache[x][y];
    if(ret!=-1 && remainSum-ret !=0 ) return 0;    // not max sum path
    
    int& ret2 = gCachePath[x][y];
    if (ret2!=-1) {
        return ret2;
    }
    
    remainSum-= gM[y][x];

    ret2 =countPath(x, y+1, remainSum) + countPath(x+1,y+1,remainSum);
    //printf("countPath, %d,%d=%d\n",x,y,ret2);
    return ret2;
}

int solve(){
    memset(gCache, -1, sizeof(gCache));
    memset(gCachePath, -1, sizeof(gCachePath));
    gMax = doSolve(0,0);
    
    return countPath(0,0,gMax);
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
    gN = 4;
    gM[0][0] = 1;
    gM[1][0] = gM[1][1] = 1;
    gM[2][0] = gM[2][1] = gM[2][2] = 1;
    gM[3][0] = gM[3][1] = gM[3][2] = gM[3][3] = 1;

    solve();
    check(gMax==4);
    check(2, countPath(0,2,2));
    check(4, countPath(0, 1, 3));
    check(8, countPath(0,0,gMax));

    //    
    
}


int main(){
    char fn[] = "tripathcnt.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    //test();
    
    int count, p,j,k;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%d", &gN);
        for(j=0;j<gN;j++){
            for(k=0;k<j+1;k++){
                scanf("%d", &gM[j][k]);
            }
        }
        
        int maxV = solve();
        printf("%d\n", maxV);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

