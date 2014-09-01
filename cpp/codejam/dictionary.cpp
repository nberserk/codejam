// http://algospot.com/judge/problem/read/DICTIONARY
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

int gN;
char gWord[1000][30];
bool gDebug;
vector<vector<int>> gGraph;
int gState[26]; // 0:init  1 : temp mark, 2 : visited
bool gVisited[26];
vector<int> gOrder;

void printVector(vector<int>& v){
    for (int i=0; i<v.size(); i++) {
        printf("%c", v[i]+'a');
    }
    printf("\n");
}

int visit(int here){
    if (gState[here]==1){
        return -1;
    }
    if (gState[here]==2){
        return 0;
    }

    gState[here]=1;
    for (int i = 0; i < 26; i++){
        if (gGraph[here][i]){
            if (visit(i)==-1)
                return -1;
        }
    }
    gState[here]=2;
    gOrder.push_back(here);
    return here;
}
void fillGraph(){
    gGraph = vector<vector<int>>(26, vector<int>(26, 0));
    int i;
    for (int j=1; j<gN; j++) {
        i = j-1;
        int len = min(strlen(gWord[i]), strlen(gWord[j]) );
        for (int k=0; k<len; k++) {
            if (gWord[i][k] != gWord[j][k]  ) {
                int from = gWord[i][k] -'a';
                int to = gWord[j][k] - 'a';
                gGraph[from][to] = 1;
                //printf("G %d,%d=%c,%c\n", from, to, gWord[i][k], gWord[j][k]);
                break;
            }
        }
    }
}
void toposort(){
    gOrder.clear();
    memset(gState, 0, sizeof(gState));
    fillGraph();
    
    bool cycle=false;
    for (int i=0; i<26; i++) {
        if (visit(i)==-1){
            cycle=true;
            break;
        }
    }

    if (cycle){
        printf("INVALID HYPOTHESIS\n");
        return;
    }

    reverse(gOrder.begin(), gOrder.end());
    printVector(gOrder);    
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
    char fn[] = "dictionary.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    test();

    // handling input
    int count, p,j,k, i;
    char buf[30], prev[30];
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        for ( j = 0; j < gN; j++){
            scanf("%s", gWord[j]);
        }

        toposort();        
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

