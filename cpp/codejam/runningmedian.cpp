// https://algospot.com/judge/problem/read/RUNNINGMEDIAN
// category: ??

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
#define H_MOD 20090711
typedef long long ll;


void check(bool ret);

bool gDebug;
int gN, gA,gB;
int gn[200000];

int next(int i){
    if (i==0){
        gn[i]= 1983;        
    }else{
        gn[i] = ((gn[i-1]*1LL*gA)%H_MOD+gB)%H_MOD;
    }    
    return gn[i];
}

void solve(){
    priority_queue<int> maxHeap;
    priority_queue<int, vector<int>, greater<int>> minHeap;
    
    int ret = 0;
    int n = next(0);
    maxHeap.push(n);
    ret += maxHeap.top();
    for (int i = 1; i < gN; i++){
        n =next(i);
        if (n>maxHeap.top()) {
            minHeap.push(n);
        }else{
            maxHeap.push(n);
        }
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.push(minHeap.top());
            minHeap.pop();
        }else if(maxHeap.size()> minHeap.size()+1){
            minHeap.push(maxHeap.top());
            maxHeap.pop();
        }
        
        ret = (ret + maxHeap.top())%H_MOD;
//        if (ret<0) {
//            printf("strange");
//        }
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
    
    // handling input
    int count, p,j,k, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d%d%d", &gN, &gA, &gB);        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

