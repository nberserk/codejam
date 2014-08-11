// http://algospot.com/judge/problem/read/MINASTIRITH
// category: greedy

#include <algorithm>
#include <unistd.h>
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
int gN;
double gPoint[100][3];
int gLen[100];




int solve(){
    
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
    char fn[] = "minastirith.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    test();

    // handling input
    int count, p,j,k,n, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);
        for ( j = 0; j < gN; j++){            
            scanf("%f", &gPoint[j][0]);
            scanf("%f", &gPoint[j][1]);
            scanf("%f", &gPoint[j][2]);
        }        
        int r = solve();        
        printf("%d\n", r);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

