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

using namespace std;
#define H_MAX 987654321

int gN, gQ;
int gH[100000];
int gS[10000], gE[10000];
bool gDebug;

struct RMQ{
    int rmin[400000]; // N*4 is enough!
    
    int init(int* v, int node, int left, int right){
        if (left==right){
            rmin[node]=v[left];
            return rmin[node];
        }
        int mid = (left+right)/2;
        int leftMin = init(v, node*2+1, left, mid);
        int rightMin = init(v, node*2+2, mid+1, right);
        rmin[node] = min(leftMin, rightMin);
        return rmin[node];
    }

    // my implementation
    int query2(int left, int right, int leftMargin, int rightMargin, int node){
        int mid = (leftMargin+rightMargin)/2;
        if (left==leftMargin && right==rightMargin)
            return rmin[node];
        else if (right<=mid){
            return query2(left,right,leftMargin, mid, node*2+1);
        }else if (mid<left)
            return query2(left, right, mid+1, rightMargin, node*2+2);
        else{
            return min(query2(left, mid,leftMargin,mid, node*2+1), query2(mid+1, right, mid+1, rightMargin, node*2+2));            
        }        
    }

    int query(int left, int right, int leftMargin, int rightMargin, int node){
        if (rightMargin<left || right<leftMargin){
            return H_MAX;
        }else if (left<=leftMargin && rightMargin<=right){
            return rmin[node];
        }
        
        int mid = (leftMargin+rightMargin)/2;
        return min(query(left,right, leftMargin, mid, node*2+1), query(left,right,mid+1,rightMargin, node*2+2));
    }
};

void solve(){
    RMQ r;
    r.init(gH, 0, 0, gN-1);

    for (int i = 0; i < gN; i++){
        gH[i] *=-1;
    }
    RMQ rmax;
    rmax.init(gH, 0, 0, gN-1);

    for (int i = 0; i < gQ; i++){
        int m = r.query(gS[i], gE[i], 0, gN-1, 0);
        int vmax = rmax.query(gS[i], gE[i], 0, gN-1, 0);        
        //printf("%d-%d=%d\n",gS[i], gE[i], (vmax+m)*-1);
        printf("%d\n", (vmax+m)*-1);
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
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d%d", &gN, &gQ);
        for (int i = 0; i < gN; i++){
            scanf("%d", &gH[i]);
        }
        for (int i = 0; i < gQ; i++){
            scanf("%d%d", &gS[i], &gE[i]);
        }
        solve();    
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
}
