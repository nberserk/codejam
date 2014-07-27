// http://algospot.com/judge/problem/read/RESTORE
// category: dp

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
int  gK;
char gStr[15][41];
int gLen[15];
int gCache [1<<15][15];
int gParent[1<<15][15];
int gDupCache[20][20];
bool gIgnore[20];

int getDuplicateCount(int f, int s){
    if (f==-1)
        return 0;
    int fSize = gLen[f];
    int sSize = gLen[s];
    int i,j;
    int& ret = gDupCache[f][s];
    if (ret!=-1){
        return ret;
    }
    ret =0;
    
    for ( i = 0; i < fSize; i++){
        for (j = 0; j < sSize; j++){
            if (i+j>=fSize) {
                break;
            }
            if (gStr[f][i+j]!=gStr[s][j]){
                break;
            }            
        }
        if (j==sSize){            
            ret=sSize;
            break;
        }else if (i+j==fSize){
            ret= j;
            break;
        }
    }
    
    return ret;
}

int restore(int index, int prev, int taken){
    if (index==gK){
        return 0;
    }

    int& ret= gCache[taken][prev+1];
    if (ret!=-1){
        return ret;
    }
    ret = H_MAX;
    int cur;
    for (int i = 0; i < gK; i++){
        if (gIgnore[i]) {
            continue;
        }
        if ((taken&(1<<i))==0){
            cur=gLen[i];
            cur -= getDuplicateCount(prev, i);
            int nextTaken = taken | 1<<i;
            
            cur += restore(index+1, i, nextTaken);
            if (cur < ret){
                ret = cur;
                gParent[taken][prev+1]=i;
            }
        }
    }
    
    //printf("%d,%d=%d\n", taken, prev, ret);
    return ret;
}

void reconstruct(string& s, int taken, int idx){

    int next = gParent[taken][idx+1];
    if (next==-1) {
        return;
    }
    int d = getDuplicateCount(idx, next);
    for (int i = d; i < gLen[next]; i++){
        s+=gStr[next][i];
    }
    
    reconstruct(s, taken|(1<<next), next);    
}

bool removeSubStr(){
    // remove substring
    int longer;
    int shorter;
    int ignoreCount = 0;
    int i,j;
    for ( i=0; i<gK; i++) {
        if (gIgnore[i]) {
            continue;
        }
        for (j=i; j<gK; j++) {
            if (i==j ) {
                continue;
            }
            if (gLen[i]>gLen[j]) {
                longer = i;
                shorter = j;
            }else{
                longer = j;
                shorter= i;
            }

            char* sub = strstr(gStr[longer], gStr[shorter]);
            if (sub!=NULL) {
                
                for (int k=shorter; k<gK-1; k++) {
                    strcpy(gStr[k], gStr[k+1]);
                    gLen[k]=gLen[k+1];
                }
                gK--;
                return true;
            }
        }
    }
    return false;
}

void solve(){    
    memset(gCache, -1, sizeof(gCache));
    memset(gParent, -1, sizeof(gParent));
    memset(gDupCache, -1, sizeof(gDupCache));
    memset(gIgnore, false, sizeof(gIgnore));
    
    while (removeSubStr()) {
       // printf("removed\n");
    }
    
    
    
    int r = restore(0, -1, 0);
    string v;
    reconstruct(v,0, -1);
    
    printf("%s\n", v.c_str());
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
    memset(gDupCache, -1, sizeof(gDupCache));
    strcpy(gStr[1], "oji");
    strcpy(gStr[2], "jing");
    gLen[1]=3;
    gLen[2] = 4;
    check(2, getDuplicateCount(1, 2));
    check(0, getDuplicateCount(2,1));
    
    strcpy(gStr[3], "cadabra");
    strcpy(gStr[4], "dabr");
    gLen[3] = 7;
    gLen[4] = 4;
    check(4, getDuplicateCount(3, 4));
}

int main(){
    char fn[] = "restore.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }

    //test();

    // handling input
    int count, p,j;
    scanf("%d", &count);
    for (p=0; p<count; p++) {
        scanf("%d", &gK);
        for ( j = 0; j < gK; j++){
            scanf("%s", gStr+j);
            gLen[j] = strlen(gStr[j]);
        }
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}
