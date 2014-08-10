// http://algospot.com/judge/problem/read/STRJOIN
// category: greedy

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
int gN;
int gLen[100];

void qsort(int* arr, int left, int right){
    int pivot = arr[(left+right)/2];
    int i=left, j=right;
    int tmp;

    while(i<=j){
        while(arr[i]<pivot)
            i++;
        while(arr[j]>pivot)
            j--;
        if (i<=j){
            tmp=arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
            i++;
            j--;
        }
    }

    if (left<j){
        qsort(arr, left, j);
    }
    if (right>i){
        qsort(arr, i, right);
    }
}

int solve(){
    int op=0;
    int n = gN;
    int cur;
    
    for (int i=0; i<gN-1; i++) {
        qsort(gLen, 0, gN-1);
        cur = gLen[0] + gLen[1];
        op+=cur;
        gLen[0]=cur;
        
        gLen[1] = H_MAX;
        n--;
    }

    return op;
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
    char fn[] = "strjoin.in";
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
            scanf("%d", &gLen[j]);
        }        
        int r = solve();
        printf("%d\n", r);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

