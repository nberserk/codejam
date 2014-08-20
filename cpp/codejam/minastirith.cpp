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
#define H_EPSILON 0.0000001
#define H_MOD 10000000
#define H_PI 3.14159265
typedef long long ll;

void check(bool ret);


bool gDebug;
int gN;
double gPoint[100][2];
bool gVisited[100];
int gLen[100];


int solve(){
    memset(gVisited, 0, sizeof(gVisited));

    // normalize
    for (int i=0; i<gN; i++) {
        
    }
    
    

    
    return 1;
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

void check(double expected, double actual){
    if (fabs(expected - actual)>H_EPSILON) {
        printf("failed expected=%lf, actual=%lf\n", expected, actual);
    }
}

void test(){

    check(45.0, atan(1.0)*180/H_PI);
    check(-45.0, atan(-1.0)*180/H_PI);
    
    
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
            double y,x,r;
            scanf("%lf %lf %lf", &y, &x, &r);
            double center = atan2(y,x)*180/H_PI;
            double range = asin(r/16.0)*180/H_PI;
            gPoint[j][0] = center;
            gPoint[j][1] = range;            
        }        
        int r = solve();
        printf("%d\n", r);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

