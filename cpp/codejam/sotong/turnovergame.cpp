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
#define H_MIN -987654321
#define H_EPSILON 0.000001
typedef long long ll;


void check(bool ret);

bool gDebug;
int gN;
int gM[16];
bool gVisited[1<<16];
int gCache[1<<16];
int gMin=H_MAX;


void print(int n){
    for (int i = 0; i < 16; i++){
        if (n&(1<<i)){
            printf("1");
        }else
            printf("0");    
        
        if (i%4==3){
            printf("\n");
        }
    }
    printf("\n");
}

int click(int i, int n){
    int r = i/4;
    int c = i%4;

    // left
    int ret = n;
    if (c>=1){
        ret ^= 1<<(r*4+c-1);
    }
    if (c!=3){
        ret ^= 1<<(r*4+c+1);
    }
    if (r>0){
        ret ^= 1<<((r-1)*4+c);
    }
    if (r<3){
        ret ^= 1<<((r+1)*4+c);
    }
    ret ^= 1<<i;
    return ret;
}

int serialize(int* m){
    int ret=0;
    for (int i = 0; i < 16; i++){
        if (m[i]==1){
            ret|= (1<<i);    
        }        
    }
    return ret;
}


int findMin(int n){// c: count, n : cur state
    queue< pair<int,int> > q;
    q.push(make_pair(0, n));
    bool visited[1<<17]={0,};
    
    while (q.size()>0) {
        pair<int,int> p = q.front();
        q.pop();
        
//        print(p.second);
        if (p.second == 0 || p.second == ((1<<16)-1)){
            return p.first;
        }
        
        if (visited[p.second]==true) {
            continue;
        }
        visited[p.second]=true;
        
        int c = p.second;
        for (int i=0; i<16; i++) {
            int nv = click(i, c);
            q.push(make_pair(p.first+1, nv));
        }
    }
    
    return -1;
}

void solve(){
    int cur = serialize(gM);

    // test
//    print(cur);
//    int after = click(0,cur);
//    print(after);
    
    int r = findMin(cur);
    if (r==-1){
        printf("impossible\n");                    
    }else
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
    int v =0;
    int nv = click(5, v) ;
    check(626, nv);
    
    v = 0;
    nv = click(12, v);
    print(nv);
    
    nv = click(14, v);
    v=nv;
    print(v);
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
    
//    test();

    for (int i = 0; i < (1<<16); i++){
        gVisited[i]=false;
        gCache[i]=-1;
    }
    
    // handling input
    int count, p;
    scanf("%d", &count);
    for (p=0; p<count; p++) {
        char s[5];
        int pos=0;
        int zeroCount=0;
        for (int i = 0; i < 4; i++){            
            scanf("%s", s);
            for (int j = 0; j <4; j++){
                if (s[j]=='b'){
                    gM[pos] = 0;
                    zeroCount++;
                }else{
                    gM[pos] =1;
                }
                pos++;
            }            
        }        
        solve();
    }    
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

