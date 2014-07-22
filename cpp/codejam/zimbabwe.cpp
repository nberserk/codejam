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
int  gM;
char gE[16];
vector<int> gOrgNumber;
ll gOrgNum;
int gCache[501];
int gCount[501];
int gLength[51];
ll factorial[15];

int getIndex(vector<int>& r){
    
    return 0;
}

int num(vector<int>& remaining, ll cur){
        
    // int& ret = gCache[1];
    // if (ret!=-1) {
    //     return ret;
    // }
    
    
    int size = remaining.size();
    if (size==0) {
        if (cur%gM==0) {
            return 1;
        }else
            return 0;
    }
    int ret = 0;
    ll temp;
    map<int,int> done;
    for (int i=0; i<size; i++) {

        temp = remaining[i]*pow(10,size-1);
        if (cur+temp >= gOrgNum)
            continue;
        if (done.find(remaining[i]) !=done.end())
            continue;
        temp += cur;
        vector<int> nv(remaining);
        nv.erase(nv.begin()+i);
        ret += num(nv, temp);
        done[remaining[i]] = i;
    }    

//   for (int i = 0; i < size; i++){
//       printf("%d,", remaining[i]);        
//   }
//    printf("(%lld)=%d\n", cur, ret);
    return ret;
}

void solve(){
    int size = strlen(gE);
    int n;
    gOrgNumber.clear();
    gOrgNum=0;
    for(int i=0;i<size;i++){
        n = gE[i] - '0';
        gOrgNumber.push_back(n);
        gOrgNum += n*pow(10,size-i-1);
    }
    
    vector<int> copy(gOrgNumber);
    
    n = num(gOrgNumber, 0);
    printf("%d\n", n);
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
    check(6, factorial[3]);
    
    
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

    // init factorial
    ll v;
    factorial[0]= factorial[1]=1;
    for(int i=2;i<15;i++){
        factorial[i]=factorial[i-1]*i;
    }    
    
    test();

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

