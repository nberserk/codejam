/*
  3 1 : N=3, K=1
  111
  101
  111
  --> 중심0에 1개의 기지국을 만들 수 있다.

  each cell's value is heght. 0 means 평평한 곳. 가장 높은 곳의 평평한 곳에 기지국을 세울 수 있다.
  N : cell size,    3<=N<=1000
  K : 기지국의 크기    1<=K<=100
 */

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
int gMap[1000][1000];
bool gVisited[1000][1000];




void find(int sx, int sy, int ex, int ey){
    for (int y = sy; y <= ey; y++){
        for (int x = sx; x <= ex; x++){
            if(gVisited[y][x]) continue;
            
        }
    }
}

void solve(){
    find(0,0,gN-1,gN-1);
    
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
    char buf[10002];
    for (p=0; p<count; p++) {        
        scanf("%d%d", &gN, &gK);

        for (int i = 0; i<gN; i++){
            scanf("%s", buf);
            for (int j = 0; j < gN; j++){
                gMap[i][j] = buf[j]-'0';
                gVisited[i][j] = false;
            }            
        }        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

