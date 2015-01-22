
#include <algorithm>
#ifdef _MSC_VER
#include <io.h>
#define F_OK 0
#else
#include <unistd.h>
#endif
#include <stdio.h>
#include <math.h>
#include <vector>
#include <map>
#include <queue>
#include <time.h>
#include <string>
#include <sstream>
#include <string.h>
#include <stack>
#include <list>
#include <limits>
#include <set>
#include <iostream>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
typedef long long ll;


void check(bool ret);

bool gDebug;
int gN;
int gMat[501][501];



void solve(){
    for (int k = 1; k <= gN; k++){
        for (int i = 1; i <= gN; i++){
	/*		if(gMat[i][k]==H_MAX)
				  continue;*/
			for (int j = 1; j <= gN; j++){
				gMat[i][j] = min(gMat[i][j], gMat[i][k] + gMat[k][j]);
			}
        }
    }
    
    int sum=0;
    for (int i = 1; i <= gN; i++){
        for (int j = 1; j <= gN; j++){     
            sum += gMat[i][j];
        }
    }
    float ret = sum/(float)(gN*(gN-1));
    printf("%.3f\n",  ret);
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

void check(char expected, char actual){
    if (expected!=actual) {
        printf("failed expected=%c, actual=%c\n", expected, actual);
    }
}

void test(){
}

int main(){
    string fn = __FILE__;
    size_t pos = fn.find(".cpp");
    fn = fn.substr(0,pos) + ".txt";    
    if (access(fn.c_str(), F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn.c_str(), "r", stdin);
    }
    
    //test();
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &n);

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                gMat[i][j] = H_MAX;
            }
            gMat[i][i]=0;
        }

        int s,e;
		gN=-1;
        for (int i = 0; i < n; i++){            
            scanf("%d%d", &s,&e);
            gMat[s][e] =1;
			gN = max(max(gN, s), e);			
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

