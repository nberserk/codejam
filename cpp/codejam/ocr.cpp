// http://algospot.com/judge/problem/read/OCR

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string>
#include <string.h>
#include <stack>
#include <limits>
#include <set>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
int gM, gN;
char gWord[500][11];
int gInput[100];
double gStart[500];
double gNext[500][500];
double gClassifier[500][500];
double gCache[100][501];
int gParent[100][501];


double doSolve(int idx, int prev){
    if(idx>=gN)
        return 1;
    
    double& ret = gCache[idx][prev+1] ;
    if(ret > 0)
        return ret;

    int cur = gInput[idx];
    int i;
    ret = 0;
    double curP;
    for(i=0;i<gM;i++){
        //if(cur==i)continue;
        if(gClassifier[i][cur]<H_EPSILON) continue;
        curP = gClassifier[i][cur];
        if(prev==-1){
            curP*=gStart[i];
        }else{
            curP*= gNext[prev][i];
        }
        curP *= doSolve(idx+1, i);
        if(curP> ret){
            ret = curP;
            gParent[idx][prev+1] = i;
        }
    }
    
    //printf("%d,%d=%.4f\n", idx,prev,ret);
    return ret;
}

double doSolve2(int idx, int prev){
    if(idx>=gN)
        return 0;
    
    double& ret = gCache[idx][prev+1] ;
    if(ret != 1.0)
        return ret;
    
    int cur = gInput[idx];
    int i;
    ret = 0;
    double curP;
    for(i=0;i<gM;i++){
        curP = gClassifier[i][cur];
        if(prev==-1){
            curP+=gStart[i];
        }else{
            curP+= gNext[prev][i];
        }
        curP += doSolve2(idx+1, i);
        if(curP> ret){
            ret = curP;
            gParent[idx][prev+1] = i;
        }
    }
    
    //printf("%d,%d=%.4f\n", idx,prev,ret);
    return ret;
}


void reconstruct(int idx, int prev, vector<string>& out){
    int next = gParent[idx][prev+1];
    if (next==-1) {
        return;
    }

    out.push_back(gWord[next]);
    reconstruct(idx+1,next, out);
}

void solve(){
    int i,j;
    for( i=0;i<gN;i++){
        for(j=0;j<gM;j++){
            gCache[i][j] = 1.0;
        }
    }
//    memset(gCache, , sizeof(gCache));
    memset(gParent, -1, sizeof(gParent));

    int maxValue = doSolve2(0, -1);
    // reconstruct items
    
    vector<string> ret;
    reconstruct(0,-1, ret);
    
    //printf("%d %d\n", maxValue, ret.size());
    for (int i=0; i<ret.size(); i++) {
        printf("%s ", ret[i].c_str());
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


void test(){
    
   
}


int main(){
    char fn[] = "ocr.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
  
//    test();
    
    int count, p,j,k,n, i;
    scanf("%d %d", &gM, &count);

    for(int i=0;i<gM;i++){
        scanf("%s", gWord+i);
    }
    
    for( i=0;i<gM;i++){
        scanf("%lf", gStart+i);
    }

    for( i=0;i<gM;i++){
        for(j=0;j<gM;j++){
            scanf("%lf", &gNext[i][j]);
        }        
    }
    
    for( i=0;i<gM;i++){
        for(j=0;j<gM;j++){
            scanf("%lf", &gClassifier[i][j]);
        }        
    }

    char in[11];
    for (p=0; p<count; p++) {
        scanf("%d", &gN);
        for(j=0;j<gN;j++){
            scanf("%s", in);
            for (int k=0;k<gM;k++){
                if(strcmp(in, gWord[k])==0){
                    gInput[j] =k;
                    break;
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

