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
  - M< 500, person
  - N<1000, task
  - fatigue = {a,b,c} = ab + bc + ac, {a,b} = a*b {a} = a
 */

using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
typedef long long ll;



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
int gN, gM;
int gLoad[1000];
int gFatigue[1000][1000];
int gCache[1000][501];


int fatigue(int s, int e){
    if (s==e) return 0;
    int& r = gFatigue[s][e];
    if(r!=-1) return r;

    r = fatigue(s,e-1);
    for (int i = s; i <= e-1; i++){
        r+= gLoad[i]*gLoad[e];
    }
    return r;
}

int findMin(int task, int person){//person:remaining #person
    if (person==1){
        return fatigue(task, gN-1);
    }
    int& r = gCache[task][person];
    if (r!=-1){
        return r;
    }
    r=987654321;
    for (int i = 0; i < gN-task-person; i++){
        r = min(r, fatigue(task, task+i) + findMin(task+i+1, person-1));
    }
    //printf("%d-%d=%d\n", task,person, r);
    return r;
}

void solve(){
    for (int i = 0; i < gN; i++){
        for (int j = 0; j <= gM; j++){
            gCache[i][j]=-1;            
        }
        for (int j = 0; j < gN; j++){
            gFatigue[i][j]=-1;
        }
    }

    int r = findMin(0, gM);
    printf("%d\n", r);
}

void test(){
    for (int i = 0; i < 10; i++){
        for (int j = 0; j < 10; j++){
            gFatigue[i][j]=-1;
        }
    }
    
    for (int i = 0; i < 10; i++){
        gLoad[i] = i+1;
    }
    check(2, fatigue(0,1));
    check(0, fatigue(0,0));
    check(11, fatigue(0,2));
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
    //h_generateRow(1000, 1, 10);
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d %d", &gN , &gM);        
        for (int i = 0; i < gN; i++){
            scanf("%d",&gLoad[i]);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

