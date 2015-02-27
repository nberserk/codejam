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
#include "../myutil.h"


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
typedef long long ll;


struct Node{
    int x,y,v;
};

class Comparator{
public:
    bool operator()(const Node& l, const Node& r){
        return l.v > r.v;
    }
};

void check(bool ret);

bool gDebug;
int gN; // max 300x300
int gMat[300][300];
int gCore[100][2]; //0:x, 1:y
int gCoreCount;
Node q[100000];
int gMin[100][100];


// using priority_queue is 3-4 times faster
// elapsed time: 8504ms with priority_queue
// elapsed time: 34127ms without priority_queue
int findMinUsingPriorityqueue(int s, int e){
    int dx = gCore[e][0];
    int dy = gCore[e][1];
    
    int visited[300][300] = {0,};
    priority_queue<Node, vector<Node>, Comparator> q;

    Node start;
    start.x = gCore[s][0];
    start.y = gCore[s][1];
    start.v = 1;
    q.push(start);
    
    int d[4][2] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    int ret = 1000;
    while (q.size()>0){
        // TODO: use simple dijkstra
        Node cur = q.top();
        q.pop();
        
        // cache
        if (visited[cur.y][cur.x]!=0 && cur.v >= visited[cur.y][cur.x]){
            continue;
        }
        visited[cur.y][cur.x] = cur.v;
        
        //
        for (int i = 0; i < 4; i++){
            int nx = cur.x + d[i][0];
            int ny = cur.y + d[i][1];
            if (nx <0 || ny<0 || nx >=gN || ny>=gN) continue;
            if (gMat[ny][nx]==-1){
                continue;
            }else if (gMat[ny][nx]==0){
                if (nx==dx && ny==dy){
                    return cur.v;
                }else
                    continue;
            }
            int nv = cur.v < gMat[ny][nx] ? gMat[ny][nx] : cur.v;
            if (nv>ret){
                continue;
            }
            Node nn ;
            nn.x = nx;
            nn.y = ny;
            nn.v = nv;
            q.push(nn);
            //            printf("%d\n", count);
        }        
    }
    
    return ret;    
}

int findMin(int s, int e){
    int dx = gCore[e][0];
    int dy = gCore[e][1];

    int visited[300][300] = {0,};
    q[0].x = gCore[s][0];
    q[0].y = gCore[s][1];
    q[0].v = 1;
    int count =1;

    int d[4][2] = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    int ret = 1000;
    while (count>0){
        // TODO: use simple dijkstra
        Node cur = q[count-1];
        count--;

        // cache
        if (visited[cur.y][cur.x]!=0 && cur.v >= visited[cur.y][cur.x]){
            continue;
        }
        visited[cur.y][cur.x] = cur.v;

        //
        for (int i = 0; i < 4; i++){
            int nx = cur.x + d[i][0];
            int ny = cur.y + d[i][1];
            if (nx <0 || ny<0 || nx >=gN || ny>=gN) continue;
            if (gMat[ny][nx]==-1){
                continue;                
            }else if (gMat[ny][nx]==0){
                if (nx==dx && ny==dy){
                    if (cur.v<ret){
                        ret = cur.v;
                    }                            
                }else
                    continue;                
            }
            int nv = cur.v < gMat[ny][nx] ? gMat[ny][nx] : cur.v;
            if (nv>ret){
                continue;
            }

            q[count].x = nx;
            q[count].y = ny;
            q[count].v = nv;
            count++;
//            printf("%d\n", count);
        }        
    }

    return ret;    
}

struct Unit{
    int s,e,v;
};
Unit gUnit[100000];

bool myCompare(const Unit& l, const Unit& r){
    return l.v - r.v;
}

void comparePerformance(){
    int count=0;
    h_startTimeMeasure();
    for (int i = 0; i < gCoreCount; i++){
        for (int j = i+1; j < gCoreCount; j++){
            gUnit[count].s = i;
            gUnit[count].e = j;
            //gUnit[count].v = findMin(i,j);
            gUnit[count].v = findMinUsingPriorityqueue(i,j);
            //printf("%d-%d=%d\n", i, j, gUnit[count].v);
            count++;
        }
    }
    h_endTimeMeasure();
    
    count=0;
    h_startTimeMeasure();
    for (int i = 0; i < gCoreCount; i++){
        for (int j = i+1; j < gCoreCount; j++){
            gUnit[count].s = i;
            gUnit[count].e = j;
            gUnit[count].v = findMin(i,j);
            //printf("%d-%d=%d\n", i, j, gUnit[count].v);
            count++;
        }
    }
    h_endTimeMeasure();

}

void solve(){
    comparePerformance();
    
    int count =0;
    for (int i = 0; i < gCoreCount; i++){
        for (int j = i+1; j < gCoreCount; j++){
            gUnit[count].s = i;
            gUnit[count].e = j;
            //gUnit[count].v = findMin(i,j);
            gUnit[count].v = findMinUsingPriorityqueue(i,j);
            //printf("%d-%d=%d\n", i, j, gUnit[count].v);
            count++;
        }
    }


    sort(gUnit, gUnit+count, myCompare);

    int set[100];
    for (int i = 0; i < count; i++)
        set[i] = -1;

    int ret = -1;
    int setIndex =0;
    for (int i = 0; i < count; i++){
        int f = set[gUnit[i].s];
        int t = set[gUnit[i].e];
        if (f==t && f!=-1) continue;
        if (f==-1 && t==-1){
            set[gUnit[i].s] = setIndex;
            set[gUnit[i].e] = setIndex;
            setIndex++;
        }else if (f==-1){
            set[gUnit[i].s] = set[gUnit[i].e];            
        }else if (t==-1){
            set[gUnit[i].e] = set[gUnit[i].s];
        }else{
            //merge set
            int from = set[gUnit[i].s];
            int to = set[gUnit[i].e];

            for (int i = 0; i < count; i++){
                if (set[i]==from){
                    set[i]=to;
                }
            }
        }
        if (ret<gUnit[i].v){
            ret = gUnit[i].v;
        }
    }

    printf("%d\n", ret);
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
    //h_generateRandomMap(100);
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        gCoreCount=0;
        for (int i = 0; i < gN; i++){            
            for (int j = 0; j < gN; j++){
                scanf("%d",&gMat[i][j]);
                if (gMat[i][j]==0){
                    gCore[gCoreCount][0] = j;
                    gCore[gCoreCount][1] = i;
                    gCoreCount++;
                }
            }
        }        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

