// algospot
// quaantize
// http://algospot.com/judge/problem/read/QUANTIZE

#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string.h>
#include <limits>

using namespace std;
bool gDebug;
int n, s;
int a[100];
int cache[10000];

typedef long long ll;


int solve(){

    int ret =0;
    //printf("%d=%d\n", idx,ret);
    return ret;
}

void printStrange(){
    printf("failed\n");
}


void test(){
    
    
    
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
    //test();

    for (p=0; p<count; p++) {
        scanf("%d %d", &n, &s);
        for(j=0;j<n;j++){
            scanf("%d",a+j);
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

