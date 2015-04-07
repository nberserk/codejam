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

/*
  constraints :
  - x,y   < 300
  - #core < 100 
 */

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

bool gDebug;
int gN; // max 300x300
int gMat[300][300];
int gCore[100][2]; //0:x, 1:y
int gCoreCount;

int gMin[100][100];


// pq start


#define MAX_PQSIZE 100000
int pq_size;
Node q[MAX_PQSIZE];
void pq_init(){    
    pq_size=0;
}

void pq_swap(int a, int b){
    Node t = q[a];
    q[a] = q[b];
    q[b] = t;
}

void pq_push(int x, int y, int v){

    q[pq_size].x  = x;
    q[pq_size].y = y;
    q[pq_size].v = v;
    pq_size++;
    if (pq_size>=MAX_PQSIZE){
        printf("pq overflow\n");
    }
    
    if (pq_size==1){
        return;
    }    

    //
    int i=pq_size-1;    
    while (i>0){
        int p = (i-1)/2;
        if (q[p].v > q[i].v){
            pq_swap(p, i);
        }else{
            break;
        }
        i=p;
    }    
}

void pq_heapify(int c){
    int smallest=c;
    int l=2*c+1;
    int r=l+1;
    if (l<pq_size && q[l].v<q[smallest].v){
        smallest = l;
    }
    if (r<pq_size && q[r].v < q[smallest].v){
        smallest=r;
    }
    if (smallest!=c){
        pq_swap(c, smallest);
        pq_heapify(smallest);
    }    
}

Node pq_pop(){
    pq_size--;
    pq_swap(0, pq_size);
    pq_heapify(0);
    return q[pq_size];
}


void pq_print(){
    int l=0;
    int linebreak=1;
    for (int i = 0; i < pq_size; i++){
        printf("%2d ", q[i].v);
        l++;
        if (l==linebreak){
            printf("\n");
            linebreak*=2;
            l=0;
        }
    }
    printf("\n\n");
}
// pq end


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

int getCoreIndex(int x, int y){
    for (int i = 0; i < gCoreCount; i++){
        if(gCore[i][0]==x && gCore[i][1]==y)
            return i;
    }
    return -1;
}

void findMinUsingPriorityqueueAll(int s, int* shortest){    
    int visited[300][300] = {0,};
    priority_queue<Node, vector<Node>, Comparator> q;

    Node start;
    start.x = gCore[s][0];
    start.y = gCore[s][1];
    start.v = 1;
    q.push(start);
    
    int d[4][2] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    int remaining = gCoreCount-(s+1);
    
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
        if ( gMat[cur.y][cur.x]==0){
            int idx = getCoreIndex(cur.x, cur.y);
            if (idx==-1) {
                //printf("ddddd\n");
                continue;
            }
            if (idx<s)   continue;
            else if (idx!=s){
                shortest[idx] = cur.v;
                //printf("%d-%d=%d\n", s, idx, cur.v);
                remaining--;
                if (remaining==0){
                    return;
                }
                continue;
            }
        }
        
        //
        for (int i = 0; i < 4; i++){
            int nx = cur.x + d[i][0];
            int ny = cur.y + d[i][1];
            if (nx <0 || ny<0 || nx >=gN || ny>=gN) continue;
            if (gMat[ny][nx]==-1){
                continue;
            }            
            int nv = cur.v < gMat[ny][nx] ? gMat[ny][nx] : cur.v;            
            Node nn ;
            nn.x = nx;
            nn.y = ny;
            nn.v = nv;
            q.push(nn);
            //            printf("%d\n", count);
        }        
    }
}


int findMinUsingCustomPriorityqueue(int s, int e){
    int dx = gCore[e][0];
    int dy = gCore[e][1];
    
    int visited[300][300] = {0,};
    
    pq_init();
    pq_push(gCore[s][0], gCore[s][1], 1);
    
    int d[4][2] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    int ret = 1000;
    while (pq_size>0){
        // TODO: use simple dijkstra
        Node cur = pq_pop();
        
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
            pq_push(nx, ny, nv);
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
Unit gUnit2[100000];

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
            gUnit2[count].s = i;
            gUnit2[count].e = j;
            gUnit2[count].v = findMinUsingCustomPriorityqueue(i,j);
            //printf("%d-%d=%d\n", i, j, gUnit[count].v);
            count++;
        }
    }
    h_endTimeMeasure();

    // check each result
    count=0;
    for (int i = 0; i < gCoreCount; i++){
        for (int j = i+1; j < gCoreCount; j++){
            check(gUnit[count].v, gUnit2[count].v);
            count++;
        }
    }
}

void solve(){
    //comparePerformance();
    
    /*
    int count =0;
    h_startTimeMeasure();
    for (int i = 0; i < gCoreCount; i++){
        for (int j = i+1; j < gCoreCount; j++){
            gUnit[count].s = i;
            gUnit[count].e = j;
            gUnit[count].v = findMin(i,j);
            int v2 = findMinUsingPriorityqueue(i,j);
            if (gUnit[count].v!=v2 || v2==1000) {
                printf("oops\n");
            }
            //printf("%d-%d=%d\n", i, j, gUnit[count].v);
            count++;
        }
    }
    h_endTimeMeasure();
    */

    int shortest[100];
    int count =0;
    h_startTimeMeasure();
    for (int i = 0; i < gCoreCount; i++){
        findMinUsingPriorityqueueAll(i, shortest);
        for (int j=i+1; j<gCoreCount; j++) {
            check(gUnit[count].v , shortest[j] );
            count++;
        }
    }
    h_endTimeMeasure();


    sort(gUnit, gUnit+count, myCompare);

    int set[10000];
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






void test(){
    pq_init();
    pq_push(0,0, 100);

    pq_push(1,1, 99);
    pq_push(1,1, 97);
    pq_push(1,1, 50);
    pq_print();

    check(50, q[0].v);
    check(4, pq_size);

    pq_push(1,1,10);
    check(10, q[0].v);
    pq_print();
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

