// http://algospot.com/judge/problem/read/NEWBUS
// category: 

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
int gMat[101][101];
int gCache[101][101];
int gCount;
int gMin;
vector<pair<int, int   >> gQuestion;
int gAnswer[20];

void search(int src, int to){
    vector<int> dist(gN+1, H_MAX);
    vector<int> count(gN+1, 0);
    // pair<distnace,node>
    dist[src]=0;
    count[src] =1;
    priority_queue<pair<int,int>> pq;
    pq.push(make_pair(0, src));
    
    while (pq.size()>0) {
        int d = -pq.top().first ;
        int here = pq.top().second;
        pq.pop();
        
        for (int i=1; i<=gN; i++) {
            if (gMat[here][i]==-1)
                continue;
            int nd =d+gMat[here][i];
            if (dist[i] >= nd){
                if (dist[i]==nd){
                    count[i] += count[here];
                }else{
                    dist[i]=nd;
                    count[i]=count[here];
                }
                pq.push(make_pair(-dist[i], i));
            }
        }
    }
    
    for (int i=1; i<=gN; i++) {
        printf("%d %d\n", dist[i], count[i]);
        for (int j=0; j<gQuestion.size(); j++) {
            if (src == gQuestion[j].first){
                gAnswer[j] = count[gQuestion[j].second];
            }
        }
    }
    
}

void dfs(int from, int to, int distance, vector<int>& visited){
    
    if (distance>gMin) {
        return;
    }
    if (from==to){
        if(distance<gMin){
            gCount=1;
            gMin=distance;
        }else if (distance==gMin){
            gCount++;
        }
        return;
    }

    for (int i = 1; i <= gN; i++){
        int d =gMat[from][i];
        if ( d==-1)
            continue;
        bool isVisited=false;
        for (int j=0; j<visited.size(); j++) {
            if(visited[j]==i){
                isVisited=true;
                break;
            }
        }
        if (isVisited) {
            continue;
        }
        visited.push_back(i);
        dfs(i, to, distance+d, visited);
        visited.pop_back();
    }        
}

void solve1(  ){
//    vector<int> v;
//    v.push_back(0);
//    dfs(from, to, 0, v);
//    if (gCount==0)
//        printf("no\n");
//    else if (gCount==1)
//        printf("only\n");
//    else
//        printf("many\n");
    
}

void solve(){
    gCount=0;
    gMin= H_MOD;
    memset(gAnswer, -1, sizeof(gAnswer));
    
    while (true) {
        int start =-1;
        for (int i=0; i<gN; i++) {
            if (gAnswer[i]==-1){
                start = gQuestion[i].first;
                break;
            }
        }
        if (start==-1) {
            break;
        }
        search(start, -1);
    }
    
    for (int i=0; i<gN; i++) {
        if (gAnswer[i]==0)
            printf("no\n");
        else if (gAnswer[i]==1)
            printf("only\n");
        else
            printf("many\n");
    }
}

void test(){
}

int main(){
    char fn[] = "newbus.in";
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
    scanf("%d", &count);
    for (p=0; p<count; p++) {
        int m, q;
        scanf("%d %d %d", &gN, &m, &q);
        memset(gMat, -1, sizeof(gMat));
        memset(gCache, -1, sizeof(gCache));
        for (int j = 0; j < m; j++){
            int from , to, distance;
            scanf("%d %d %d", &from, &to, &distance);
            gMat[from][to] = distance;
            gMat[to][from] = distance;
        }

        gQuestion.clear();
        for (int j = 0; j < q; j++){
            int from , to;
            scanf("%d %d", &from, &to);
            if (from>to) {
                swap(from, to);
            }
            gQuestion.push_back(make_pair(from, to));
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

