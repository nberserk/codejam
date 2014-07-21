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
int gCache[501];
int gCount[501];
int gLength[51];
ll factorial[15];

int getIndex(vector<int>& r){
    
    return 0;
}

int num(vector<int>& remaining, int modulus){
        
    // int& ret = gCache[1];
    // if (ret!=-1) {
    //     return ret;
    // }
    int ret=0;
    
    int size = remaining.size();
    ret = 0;
    int nth = gOrgNumber.size()-size;
    ll temp=size*10;
    int org, remainCount=0;
    
    for (int i=0; i<size; i++) {
        if (remaining[i]==-1) {
            continue;
        }
        remainCount++;
        // todo: r==0, same number is not allwed
//        if(size==1){
//            if ( remaining[i] >= gOrgNumber[nth]) 
//                continue;            
//        }else{
            if ( remaining[i] > gOrgNumber[nth])
                continue;            
//        }

        org = remaining[i];
        
        temp *=remaining[i];
        temp %= gM;
        temp -= modulus;
        if (temp<0){
            temp+=gM;
        }

        remaining[i]=-1;
        ret += num(remaining, temp);
        remaining[i] =org;
    }

    if (remainCount==0){
        if (modulus==0){
            return 1;
        }else
            return 0;
    }
    
    return ret;
}

void solve(){
    int size = strlen(gE);
    int n;
    gOrgNumber.clear();
    for(int i=0;i<size;i++){
        n = gE[i] - '0';
        gOrgNumber.push_back(n);
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

