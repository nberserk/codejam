// stopcoder, mining

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <map>
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
int gMoney, gN;
int gMap[1000][1000][2];
int gMax;
bool gVisited[1000];

struct Node {
    int gain,  at, money;
    Node *parent;
};

// bool operator<(const node &leftNode, const node &rightNode) {
//     if (leftNode.cost != rightNode.cost) return leftNode.cost < rightNode.cost;
//     if (leftNode.at != rightNode.at) return leftNode.at < rightNode.at;
//     return false;
// }


Node* dfs(Node *cur){

    Node *maxNode=cur;
    
    for (int i = 0; i < gN; i++){
        if (gVisited[i] || gMap[cur->at][i][0]==-1)
            continue;

        if (gMap[cur->at][i][0] > cur->money){
            continue;
        }

        Node *next = new Node();
        next->gain = cur->gain + gMap[cur->at][i][1];
        next->at=i;
        next->money = cur->money - gMap[cur->at][i][0];
        next->parent = cur;
        gVisited[next->at]=true;

        Node* v = dfs(next);
        if (v==0) {
            continue;
        }
        if ( v->gain > maxNode->gain){
            maxNode = v;
        }                  
    }

    printf("%d=%d\n", cur->at, maxNode->gain);
    return maxNode;    
}

void solve(){
    memset(gVisited, false, sizeof(gVisited));

    Node start;
    start.gain=0;
    start.at=0;
    start.money=gMoney;
    start.parent=0;
    gVisited[0] =true;

    Node* best= dfs(&start);
    printf("%d\n", best->gain);    
    
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
    char fn[] = "mine.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    //test();

    // handling input
    int count, p,j,k, i, cost, gain, from, to;
    scanf("%d", &count);
    for (p=0; p<count; p++) {
        
        scanf("%d %d", &gN , &gMoney);
        memset(gMap, -1, sizeof(gMap));

        for (int i = 0; i < gN; ++i){
            scanf("%d %d %d %d", &from, &to, &cost, &gain);
            gMap[from][to][0] = cost;
            gMap[from][to][1] = gain;
        }

        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}



















