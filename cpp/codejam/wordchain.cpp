// https://algospot.com/judge/problem/read/WORDCHAIN
// category: graph

#include <algorithm>
#include <unistd.h>
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
#define H_MOD 10000000
typedef long long ll;


void check(bool ret);

bool gDebug;
int gN;
char gWord[100][15];
int gMat[100][100];
int gIn[100], gOut[100];
int gVisited[100];


void dfs(int node, vector<int>& path){
    if (gVisited[node]==1){
        return;
    }
    gVisited[node]=1;

    for (int i = 0; i < gN; i++){
        if (gMat[node][i]==1){
            dfs(i, path);
        }
    }
    path.push_back(node);    
}

void solve(){
    
    memset(gMat, -1, sizeof(gMat));
    for (int i = 0; i < gN; i++){
        gIn[i]=0;
        gOut[i]=0;
    }
    for (int i = 0; i < gN; i++){
        int len = strlen(gWord[i]);

        char e = gWord[i][len-1];
        for (int j = 0; j < gN; j++){
            if (i==j)continue;
            if (e==gWord[j][0]){
                gMat[i][j]=1;
                gOut[i]++;
                gIn[j]++;
            }
            
        }
    }
    
    int start=-1;
    for (int i=0; i<gN; i++) {
        if (gIn[i]==0 ) {
            start =i;
            break;
        }
    }
    if(start==-1)
        start = gN-1;

    vector<int> path;
    memset(gVisited, -1, sizeof(gVisited));
    dfs(start, path);
    if (path.size()!=gN) {
        printf ("IMPOSSIBLE\n");
        return;
    }
    for (int i = path.size()-1; i >=0 ; i--){
        printf("%s ", gWord[path[i]]);
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
    int count, p,j,k, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        for (int i = 0; i < gN; i++){
            scanf("%s",gWord+i);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

