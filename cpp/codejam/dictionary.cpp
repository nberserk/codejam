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
bool gVisited[26];
int gLast;


int dfs(int here, int prev){
    int cur;
    gVisited[here] = true;
    for (int i = 0; i < 26; i++){        
        if (gGraph[here][i]==false)
            continue;
        if (gVisited[i]==true)
            continue;

        cur = dfs(i, here);
        if (cur!=-1){
            return cur;
        }
    }

    if (prev==-1){
        return -1;
    }

    gGraph[prev][here]=false;
    gLast = prev;
    return here;
}

void solve(){

    bool invalid=false;
    for (int i = 0; i < 26; i++){
        for (int j = 0; j < 26; j++){
            if (gGraph[i][j]==true && gGraph[j][i]==true){
                printf("INVALID HYPOTHESIS\n");
                return;                
            }
        }
    }
    
    int leaf;
    vector<int> v;
    vector<int>::iterator it;
    int idx =0;
    
    while(idx<=25){
        memset(gVisited, false, sizeof(gVisited));
        leaf = dfs(idx, -1);
        if (leaf!=-1){
            it = find(v.begin(), v.end(), leaf);
            if (it != v.end()){
                invalid=true;
                break;
            }                
            v.push_back(leaf);
        }else{
            v.push_back(gLast);
            idx++;
        }
    }

    if (invalid){
        printf("INVALID HYPOTHESIS\n");
    }else{
        bool used[26];
        memset(used, false, sizeof(used));
        int size = v.size();
        for (int i = 0; i < size; i++){
            used[v[i]]=true;
            printf("%c", v[i]+'a');
        }

        for (int i = 0; i < 26; i++){
            if (used[i]==true){
                continue;
            }
            printf("%c", i+'a');
        }
        printf("\n");
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

void fillGraph(char* prev, char* buf){
    unsigned long len = strlen(prev);
    int from, to;
    for (int i = 0; i < len; i++){
        if ( *(prev+i) == *(buf+i))
            continue;
        from = *(prev+i)-'a';
        to = *(buf+i)-'a';
        //printf("%d->%d\n", from, to );
        gGraph[to][from] = true;
        return;
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
    int count, p,j,k,gN, i;
    char buf[30], prev[30];
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        scanf("%s", prev);
        memset(gGraph, false, sizeof(gGraph));
        for ( j = 0; j < gN-1; j++){            
            scanf("%s", buf);
            fillGraph(prev, buf);
            strcpy(prev, buf);
        }

        solve();
        
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

