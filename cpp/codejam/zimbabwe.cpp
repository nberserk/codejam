// http://algospot.com/judge/problem/read/ZIMBABWE

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
int  gM, gN;
char gE[16];
char gSorted[16];
ll gOrgNum;
int gCache[1<<14][20][2];
int gCount[501];
int gLength[51];

int getIndex(bool t[16]){
    int idx =0;
    for (int i = 0; i < gN; i++){
        if (t[i]){
            idx += (1<<i);
        }
    }
    
    return idx;
}

int num2(int index, int taken, int mod, int less){
    if (index == gN) {
        if (less==1 && mod==0) {
            return 1;
        }else
            return 0;
    }

    int& ret = gCache[taken][mod][less];
    if (ret!=-1){
        return ret;
    }
    ret =0;
    
//    if (taken==2) {
//        printf("dd");
//    }

    char c;
    map<int,char> done;
    for (int i = 0; i < gN; i++){
        if ( ((1<<i) & taken) ==0){ //if not taken
            c = gSorted[i];
            if (less==0 && c > gE[index])
                continue;

            if (done.find((int)c) !=done.end())
                continue;
            int nextMod = (mod*10+(c-'0'))%gM;
            int nextTaken = taken | 1<<i;
            int nextLess = less || c < gE[index];
            ret += num2(index+1, nextTaken, nextMod, nextLess);
            ret %= 1000000007;
            done[(int)c] = c;
        }
    }
    //printf("%d=%d\n", taken, ret);
    return ret;
}

int num(string price, bool taken[16], bool less, int mod){
    int size = price.size();    
    if (size==gN){
        if (mod==0 && less){
            return 1;            
        }else
            return 0;        
    }

    int idx = getIndex(taken);
    int &ret= gCache[idx][mod][less?1:0];
    if (ret!=-1){
        return ret;
    }
    ret =0;
    int nl = less;
    char c;
    map<int,char> done;
    ll temp;
    
    for (int i = 0; i < gN; i++){
        if (taken[i])
            continue;
        c = gSorted[i];
        if (!less){
            if (c>gE[size])
                continue;
            else if(c<gE[size])
                nl = true;
            else
                nl = false;        
        }

        if (done.find((int)c) !=done.end())
            continue;
        
        temp = (c-'0')*pow(10,gN-size-1);
        temp %=gM;
        int nm = mod-temp;
        if (nm<0) {
            nm+=gM;
        }

        taken[i]=true;
        ret += num(price+c, taken, nl, nm);
        taken[i]=false;
        ret %= 1000000007;
        done[(int)c] = c;
    }

    //printf("%s,%d=%d\n", price.c_str(), mod, ret);
    return ret;
}

void solve(){
    gN = strlen(gE);
    strcpy(gSorted, gE);
    sort(gSorted, gSorted+gN);
    
    bool taken[16];
    memset(taken, 0, sizeof(taken));
    memset(gCache, -1, sizeof(gCache));
    string s;
    //int r = num(s, taken, false, 0);
    int r = num2(0, 0, 0, 0);

    printf("%d\n", r);
    
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

    gN=10;
    bool b[16];
    memset(b, 0, sizeof(b));
    check(0, getIndex(b));
    b[0]=true;   
    check(1, getIndex(b));
    b[1] =true;
    check(3, getIndex(b));
}


int main(){
    char fn[] = "zimbabwe.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }

    //test();

    // handling input
    int count, p,j,k,n, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {
        scanf("%s %d", gE , &gM);        
        solve();        
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

