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
int gN, gK;
int gMap[1000][1000];
bool gVisited[1000][1000];
int gCount; // neighbor count
int gPath[1000000][2];
int gHeightCount;
int gHeight[100];
int gMaxHeight;

void addHeight(int nh){
    for (int i = 0; i < gHeightCount; i++){
        if(gHeight[i]==nh)
            return;
    }
    gHeight[gHeightCount]=nh;
    gHeightCount++;
}

void traverse(int x,int y){
    if (x<0 || y<0 || x>=gN || y>=gN){
        addHeight(-1);    
        return;
    }
    if (gVisited[y][x]){
        return;
    }
    gVisited[y][x]=true;
    if (gMap[y][x]!=0){
        addHeight(gMap[y][x]);
        return;
    }    

    gPath[gCount][0]=x;
    gPath[gCount][1]=y;
    gCount++;

    traverse(x-1, y);
    traverse(x+1, y);
    traverse(x, y-1);
    traverse(x, y+1);    
}

void traverseWrap(int x, int y){
    gCount=0;
    gHeightCount=0;

    traverse(x,y);

    printf("hCount=%d, ", gHeightCount);
    for (int i = 0; i < gHeightCount; i++){
        printf("%d ", gHeight[i]);
    }
    printf("\n");
    if (gHeightCount==1 && gHeight[0]==gMaxHeight){
        int l,r,t,b;
        printf("gCount=%d\n", gCount);
        if (gK==1) {
            printf("%d\n", gCount);
        }
        
    }else{
        for (int i = 0; i < gCount; i++){
            int x = gPath[i][0];
            int y = gPath[i][1];
            gMap[y][x] = -100;
        }
    }
}

void solve(){
    for (int y = 0; y < gN; y++){
        for (int x = 0; x < gN; x++){
            if (gMap[y][x]!=0){
                continue;
            }
            if(gMap[y][x]==0 && gVisited[y][x]==false){
                traverseWrap(x,y);
            }
                
            
        }
    }    
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

        gMaxHeight=0;
        for (int i = 0; i<gN; i++){
            scanf("%s", buf);
            for (int j = 0; j < gN; j++){
                gMap[i][j] = buf[j]-'0';
                if(gMap[i][j]>gMaxHeight)
                    gMaxHeight=gMap[i][j];
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

