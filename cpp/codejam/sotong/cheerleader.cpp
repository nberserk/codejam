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
char gS[11];

void permutation(char* s, int c){
    if (c==gN-1){
        printf("%s\n", s);
        return;
    }

    char t[11];
    strcpy(t,s);

    sort(s+c, s+gN);    
    for (int i = c; i < gN; i++){                
        if (c!=i && *(s+c)==*(s+i)){
            continue;
        }        
        swap(*(s+c), *(s+i));
        permutation(s, c+1);        
        swap(*(s+c), *(s+i));        
    }
    strcpy(s, t);
}

bool nextP(char* c){
    
    for (int i = gN-2; i >= 0; i--){
        char* src = c+i;
        char* smallest = 0;
        for (int j = i+1; j < gN; j++){
            char* dest = c+j;
            if(*src>=*dest)continue;
            if (smallest==0 || *smallest>*dest){
                smallest = dest;
            }            
        }
        if (smallest!=0){
            swap(*src, *smallest);
            sort(c+i+1, c+gN);
            printf("%s\n", c);
            return true;
        }
    }
    return false;
}

void solve(){
    sort(gS, gS+gN);
    printf("%s\n", gS);
    while(nextP(gS));
    
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

void check(char expected, char actual){
    if (expected!=actual) {
        printf("failed expected=%c, actual=%c\n", expected, actual);
    }
}

void test(){
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
    
    //test();
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%s", gS);
        gN = strlen(gS);        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

