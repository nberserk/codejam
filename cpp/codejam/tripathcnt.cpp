// algospot
// http://algospot.com/judge/problem/read/TRIPATHCNT

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
int gCache[100][100];
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

int solve(){
    memset(gCache, -1, sizeof(gCache));
    gMax = doSolve(0,0);

    countPath();
}

void check(bool ret){
    if (ret==false) {
        printf("failed\n");
    }
}


void test(){    
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

