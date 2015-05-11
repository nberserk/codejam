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

//#define MAX_N 1000001
#define MAX_N 20
bool gDebug;
int gN, gM; 
int gCount[5][5][1000000];
//int gCache[3][3][1000000];
// L_N : ends L, 
// L_L : ends L, LL showed up before
// L_P : ends L, PP showed up before
int L_N[MAX_N];
int L_L[MAX_N];
int L_P[MAX_N];
int P_N[MAX_N];
int P_L[MAX_N];
int P_P[MAX_N];

char gS[1000001];


void precalc(){
    L_N[0]=1;
    L_L[0]=0;
    L_P[0]=1;
    P_N[0]=1;    
    P_L[0]=1;
    P_P[0]=0;    
    
//    L_N[1]=2;
//    L_L[1]=1;
//    L_P[1]=2;
//    
//    P_N[1]=2;
//    P_L[1]=2;
//    P_P[1]=1;
    
    for (int i = 1; i <= gN; i++){
        L_N[i]= (L_L[i-1]+P_N[i-1])%gM;
        L_L[i]= P_L[i-1];
        L_P[i]= (L_N[i-1]+P_P[i-1])%gM;

        P_N[i]= (L_N[i-1]+ P_P[i-1])%gM;
        P_L[i]= (L_L[i-1]+ P_N[i-1])%gM;
        P_P[i]= L_P[i-1];
        //printf("L_N-%d=%d\n", i, L_N[i]);
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
    r= r%gM;
    return r;
}

void solve(){
    for (int i=0; i<5; i++) {
        for (int j=0; j<5; j++) {
            for (int k=0; k<=gN; k++) {
                gCount[i][j][k]=-1;
            }
        }
    }
    
    int acc=2;
    int prev=2;
    int r=1;
    for (int i = 0; i < gN; i++){        
        if (gS[i]=='P'){            
            int d=0;
            int n = gN-i-1;
            if (acc==0) {
                if (prev==0){
                    //nope
                }else if (prev==1){
                    d=count(acc,0,n);
                }else{
                    printf("strange\n");
                }                
            }else if(acc==1){
                if (prev==0){
                    d=count(2,0,n);
                }else if (prev==1){
                    d=count(1,0,n);
                }else{
                    printf("strange\n");
                }
            }else{
                if (prev==0){
                    d=count(0,0,n);
                }else if (prev==1){
                    d=count(2,0,n);
                }else
                    d=count(2,0,n);
            }            
            printf(" %d, %d %d %d=%d\n",i, 100, 0, n,d );
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

void solve2(){
    precalc();
    
    int acc=2;
    int prev=2;
    int r=1;
    for (int i = 0; i < gN; i++){
        if (gS[i]=='P'){
            int n=gN-i-1;
            int d=0;
            if (acc==0){
                if (prev==0) {
                    // LL L + L                    
                }else if (prev==1) {
                    // LL PL
                    d=L_L[n];
                }else{
                    printf("strange\n");
                }
            }else if (acc==1){
                if (prev==0) {
                    // PP LL
                    d=L_N[n];
                }else if (prev==1) {
                    // PP PL
                    d=L_P[n];
                }else{
                    printf("strange\n");
                }
            }else if (acc==2){
                if (prev==0) {
                    d=L_L[n];
                }else if(prev==1)
                    d=L_N[n];
                else
                    d=L_N[n];
            }
            r+=d;
            r%=gM;
            //printf(" %d=%d\n", i, d);
        }
        if (gS[i]=='L' && prev==0){
            acc = acc==1 ? 2 : 0;
        }else if (gS[i]=='P' && prev==1){
            acc = acc==0 ? 2 : 1;
        }
        prev = gS[i]=='L' ? 0:1;
        //printf("%d, %d %d\n", i, acc, prev);
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
        //test();
    //precalc();
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d %d", &gN, &gM);
        scanf("%s", gS);
        //solve();
        solve2();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

