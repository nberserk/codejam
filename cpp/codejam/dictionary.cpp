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



bool gDebug;
bool gGraph[26][26];

int dfs(int here){
    int ret = -1;

    for (int i = 0; i < 26; i++){
        if
    }
    
}

int solve(){
    
    int leaf;
    vector<int> v;
    int idx =0;
    while(idx<=25)
        leaf = dfs(i);
        if (leaf!=-1){
            v.push_back(leaf);
        }else{
            idx++;
        }
    
    return 0;
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

bool fillGraph(char* prev, char* buf){
    int len = strlen(prev);
    int from, to;
    for (int i = 0; i < len; i++){
        if ( *(prev+i) == *(buf+i))
            continue;
        from = *(prev+i)-'a';
        to = *(buf+i)='a';
        if (gGraph[to][from]==true)
            return false; // invalid        
        gGraph[from][to] = true;
        return true;
    }
    
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
    int count, p,j,k,n, i;
    char buf[30], prev[30];
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        scanf("%s", prev);
        bool valid =true;
        memset(gGraph, -1, sizeof(gGraph));
        for ( j = 0; j < gN-1; j++){            
            scanf("%s", buf);
            valid = fillGraph(prev, buf);
            if (valid==true){
                break;
            }
        }

        if (valid==false){
            printf("INVALID HYPOTHESIS\n");
        }else{
            solve();
        }        
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

