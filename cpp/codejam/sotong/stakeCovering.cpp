
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
float gX[500];
float gH[500];
float gMaxHeight;
int selected[500];

void calc(int s, int e, float& a, float& b){
    a = (gH[e]-gH[s])/(gX[e]-gX[s]);
    b = gH[e]-a*gX[e];
}

void split(int s, int e){
    if (s>e){
        return;
    }

    selected[s]=1;
    selected[e]=1;

    float a,b;
    float y,d;
    float maxDiff=-2000;
    int idx[500];
    int count =0;
    calc(s,e,a,b);
    for (int i = s+1; i < e; i++){
        y = a*gX[i]+b;
        d = gH[i]-y;
        if (d<0)continue;
        if (d> maxDiff){
            maxDiff = d;
            count=0;
            idx[count++] = i;            
        }else if (d==maxDiff){
            idx[count++]=i;
        }
    }

    if (count==0){
        return;
    }

    for (int i = 0; i < count; i++){
        selected[idx[i]]=1;
    }
    
    if (count>1){
        split(s, idx[0]);
        split(idx[count-1], e);        
    }else{
        split(s,idx[0]);
        split(idx[0], e);
    }    
}

void solve(){
    for (int i = 0; i < gN; i++){
        selected[i]=-1;
    }    
    selected[0] = 1;
    selected[gN-1] =1;
    
    
    int left=-1;
    int right=-1;
    for (int i = 0; i < gN; i++){
        if (gH[i]==gMaxHeight){
            selected[i]=true;
            if (left==-1){
                left = i;
            }
            right =i;            
        }
    }

    

    // left
    split(0, left);    

    // right    
    split(right, gN-1);    

    for (int i = 0; i < gN; i++){
        if (selected[i]==1){
            printf("%d ", i+1);
        }
    }
    printf("\n");
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
        scanf("%d", &gN);
        gMaxHeight = -1;
        for (int i = 0; i < gN; i++){
            scanf("%f %f",gX+i, gH+i);
            gMaxHeight = max(gH[i], gMaxHeight);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

