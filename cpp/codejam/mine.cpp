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

struct path{
    int cost, gain;
};

void check(bool ret);

bool gDebug;
int gMoney, gN;

int gGain[1000][1000];
int gCost[1000][1000];


ll factorial[15];
int gMax;


struct node {
 int cost;
 int at;
};

bool operator<(const node &leftNode, const node &rightNode) {

    if (leftNode.cost != rightNode.cost) return leftNode.cost < rightNode.cost;
    if (leftNode.at != rightNode.at) return leftNode.at < rightNode.at;
    return false;
}


int solve(int here, int money){
    int ret=0;
    int cur;
    for (int i =0; i< gN; i++){
        if (gCost[here][i]==-1 || gCost[here][i] > money){
            continue;            
        }

        cur= gGain[here][i] + solve(i, money-gCost[here][i] );
        if (cur>ret){
            ret=cur;            
        }        
    }

    printf("%d,%d=%d\n", here,money,ret);
    return ret;    
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
    check(6, factorial[3]);
    
    
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
        memset(gCost, -1, sizeof(gCost));
        memset(gGain, -1, sizeof(gGain));

        for (int i = 0; i < gN; ++i){
            scanf("%d %d %d %d", &from, &to, &cost, &gain);
            gGain[from][to] = gain;
            gCost[from][to] = cost;
        }

        gain = solve(0, gMoney);
        printf("%d\n", gain);
        
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

