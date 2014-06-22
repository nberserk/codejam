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


void initCache(){
    memset(gCache, -1, sizeof(gCache));
    memset(gNext, -1, sizeof(gNext));
}

double doSolve(int idx, int prev){
    if(idx>=gN)
        return 0;
    
    double& ret = gCache[idx][prev+1] ;
    if(ret >= 0)
        return ret;

    int cur = gInput[idx];
    int i;
    ret = 0;
    for(i=0;i<gM;i++){
        if(cur==i)continue;
        if(gClassifier[i][cur]<H_EPSILON) continue;
        curP = gClassifier[i][cur];
        if(prev==-1){
            curP*=gStart[i];
        }else{
            curP*= gNext[prev][i];
        }
        ret = max(ret, curP*doSolve(idx,i));        
    }    
    
    //printf("%d,%d=%d\n", idx,capacity,ret);
    return ret;
}

void solve(){
    memset(gCache, -1, sizeof(gCache));

    int maxValue = doSolve(0, -1);
    // reconstruct items
    vector<string> ret = reconstruct();
    
    printf("%d %d\n", maxValue, ret.size());
    for (int i=0; i<ret.size(); i++) {
        printf("%s\n", ret[i].c_str());
    }
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
    gN = 1;
    gW=10;
    strcpy(gName[0], "dd");
    gSize[0] = 10;
    gValue[0]=10;
    check(10, doSolve(-1, gW));
    vector<string> r = reconstruct();
    check(1, r.size()  );
    check(r[0].compare("dd")==0);
    
   
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
    
    int count, p,j,k,n;
    scanf("%d %d", &gM, &count);

    for(int i=0;i<gM;i++){
        scanf("%s", gWord+i);
    }
    
    for( i=0;i<gM;i++){
        scanf("%f", gStart+i);
    }

    for( i=0;i<gM;i++){
        for(j=0;j<gM;j++){
            scanf("%f", gNext[i][j]);
        }        
    }
    
    for( i=0;i<gM;i++){
        for(j=0;j<gM;j++){
            scanf("%f", gClassifier[i][j]);
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

