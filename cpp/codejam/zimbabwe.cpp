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
int gCache[1000000][20];
int gCount[501];
int gLength[51];
ll factorial[15];

int getIndex(vector<int>& r){
    int ret=0, size = r.size();
    for (int i = 0; i < size; i++){
        ret += pow(10, size-i-1)*r[i];
    }
    
    return ret;
}

int num(vector<int>& remaining, ll cur, int modulus){
        
    
    int size = remaining.size();
    if (size==1) {
        if (cur+remaining[0]<gOrgNum && remaining[0]%gM==modulus) {
            return 1;
        }else
            return 0;
    }

    int idx=-1;
    if (size<=5){
        sort(remaining.begin(), remaining.end());
        idx = getIndex(remaining);
        int& ret = gCache[idx][modulus];
        if (ret!=-1) {
            return ret;
        }
    }

    int ret=0;
    ll temp;
    map<int,int> done;
    for (int i=0; i<size; i++) {

        temp = remaining[i]*pow(10,size-1);
        if (cur+temp >= gOrgNum)
            continue;
        if (done.find(remaining[i]) !=done.end())
            continue;
        int nm = (modulus-temp%gM);
        if (nm<0) {
            nm+=gM;
        }
        
        temp += cur;
        vector<int> nv(remaining);
        nv.erase(nv.begin()+i);
        
        ret += num(nv, temp, nm);
        done[remaining[i]] = i;
    }

//   for (int i = 0; i < size; i++){
//       printf("%d,", remaining[i]);        
//   }
//    printf("(%lld)=%d\n", cur, ret);
    if (idx!=-1){
        gCache[idx][modulus] = ret;
        printf("(%d,%d)=%d\n", idx, modulus, ret);

    }
    return ret;
}


void solve(){
    int size = strlen(gE);
    int n;
    gOrgNumber.clear();
    gOrgNum=0;
    memset(gCache, -1, sizeof(gCache));
    for(int i=0;i<size;i++){
        n = gE[i] - '0';
        gOrgNumber.push_back(n);
        gOrgNum += n*pow(10,size-i-1);
    }
    
    vector<int> copy(gOrgNumber);
    
    sort(copy.begin(), copy.end());
    n = num(gOrgNumber, 0, 0);
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
    
    vector<int> r;
    r.push_back(1);
    r.push_back(2);
    r.push_back(5);
    
    check(125, getIndex(r));
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

