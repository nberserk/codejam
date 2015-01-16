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
int gMatched[26][26][100];
int gCount[26][26];
int gIn[100], gOut[100];
int gVisited[100];



void warn(){
    printf("strange\n");
}

void dfs(int node, vector<int>& path){    
    for (int i = 0; i < 26; i++){
        while (gMat[node][i]>0){
            gMat[node][i]--;
            dfs(i, path);
        }        
    }
    path.push_back(node);
    //printf("dfs-%d\n", node);
}

void makeGraph(){
    
    for (int i = 0; i < 26; i++){
        for (int j = 0; j < 26; j++){
            gCount[i][j] =0;
            gMat[i][j]=0;
        }
        gIn[i]=0;
        gOut[i]=0;        
    }
    
    for (int i = 0; i < gN; i++){
        int len = strlen(gWord[i]);
        int s = gWord[i][0]-'a';
        int e = gWord[i][len-1]-'a';
        
        gMat[s][e]++;
        gCount[s][e]++;
        gMatched[s][e][gCount[s][e]-1] = i;        
        gOut[s]++;
        gIn[e]++;        
    }
}

void solve(){
    makeGraph();    
    
    int start=-1;    
    for (int i=0; i<26; i++) {
        if ( gIn[i]+1 ==gOut[i] ) {
            start =i;
            break;
        }        
    }
    if(start==-1){
        for (int i=0; i<26; i++) {
            if ( gOut[i]>0 ) {
                start =i;
                break;
            }
        }
    }
    //printf("start-%d\n", start);
    vector<int> path;
    
    dfs(start, path);
    if (path.size()!=gN+1) {
        printf ("IMPOSSIBLE\n");
        return;
    }
    reverse(path.begin(), path.end());
    
    int size = path.size();
    int p = path[0];
    for (int i = 1; i <size ; i++){
        int c= path[i];
        gCount[p][c]--;
        int idx = gMatched[p][c][gCount[p][c]];
        if (gCount[p][c]<0){
            warn();
        }
        printf("%s ", gWord[idx]);
        p = c;
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

