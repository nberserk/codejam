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
  - N <= 100,0000
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
int gCount[5][5][1000000];
int gCache[3][3][1000000];
char gS[1000001];


int count2(){

    gCache[]

    for (int i = 0; i < N; i++){
        
    }
    
}

// acc,  0:LL , 1:PP, 2:none
// prev, 0:L  , 1:P  2:none
int count(int acc,int prev, int remainingN){
    if (remainingN<0) {
        return 0;
    }
    if (remainingN==0){
        return 1;
    }

    int& r = gCount[acc][prev][remainingN];
    if (r!=-1){
        return r;
    }

    r=0;
    int nextN = remainingN-1;
    if (acc==0){
        if (prev==0){
            //LLxL + L
            //LLxL + P
            r+= count(0, 1, nextN);
        }else if(prev==1){
            //LLxP + L
            r += count(0,0,nextN);
            //LLxP + P
            r+= count(2,1,nextN);
        }else{
            // impossible case
            printf("impossible\n");
        }        
    }else if(acc==1){
        if (prev==0){
            // PPL + L
            r +=  count(2, 0, nextN);
            // PPL + P
            r += count(1, 1, nextN);
        }else if(prev==1){
            // PPxxP + L
            r += count(1, 0, nextN);
            // PPxxP + P
        }else{            
            // impossible case
            printf("impossible\n");
        }
    }else {
        if (prev==0){
            // L + L
            r += count(0, 0, nextN);
            // L + P
            r += count(acc, 1, nextN);
        }else if (prev==1){
            // P + L
            r += count(acc, 0, nextN);
            // P + P
            r += count(1, 1, nextN );
        }else{
            r += count(acc, 0, nextN);
            r += count(acc, 1, nextN);
        }
    }    
    
    //printf("%d,%d,%d=%d\n", acc, prev, remainingN, r);
    return r;
}

void solve(){    
    int acc=2;
    int prev=2;
    int r=1;
    for (int i = 0; i < gN; i++){        
        if (gS[i]=='P'){            
            int d=0;
            int na=acc;
            if (prev==0){
                // L+L
                if (na!=0){
                    na = na==1 ? 2 : 0;
                    d += count(na, 0, gN-i-1);
                }                
            }else if (prev==1){
                // P+L
                d+=count(na,0,gN-i-1);
            }else {
                //
                d+=count(na,0, gN-i-1);
            }
            
            printf(" %d, %d %d %d=%d\n",i, na, 0, gN-i-1,d );
            r+= d;
            r %=gM;            
        }

        if (gS[i]=='L' && prev==0){
            acc = acc==1 ? 2 : 0;
        }else if (gS[i]=='P' && prev==1){
            acc = acc==0 ? 2 : 1;
        }
        prev = gS[i]=='L' ? 0:1;
    }    
    printf("%d\n", r);
}

void test(){
    gM=1000000;
    check(2, count(2,2,1));
    check(1, count(0,0,1)); //LL+L
    check(2, count(0,1,1)); //LL+P
    
    check(4, count(2,2,2));
    check(6, count(2,2,3));
    check(3, count(0,0,3));
    check(5, count(1,0,3));
    
    check(5, count(2,0,3));
    check(5, count(2,0,3));
    
    check(8, count(2,0, 4));
    
//    check(10, count(0,0,4));
//    check(5, count(1,0, 3));
//    check(14, count(0,0,5));
//    check(2, count(1,-1,2));
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

    //h_generateRandomMap(100);
    for (int i=0; i<5; i++) {
        for (int j=0; j<5; j++) {
            for (int k=0; k<1000000; k++) {
                gCount[i][j][k]=-1;
            }
        }
    }
    //test();
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d%d", &gN, &gM);
        scanf("%s", gS);        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

