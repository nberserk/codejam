// https://algospot.com/judge/problem/read/URI
// category: 

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
#include <list>
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
char gStr[100];
char gFrom[7][5];
char gTo[7][5];

void solve(){
    int size = strlen(gStr);
    for (int i = 0; i < size; i++){
        if (gStr[i]=='%' && i<size-2 && gStr[i+1]=='2'){
            int match = -1;
            for (int j = 0; j < 7; j++){
                if(gStr[i+2]==gFrom[j][0]){
                    match = j;                    
                    break;
                }
            }

            if (match !=-1){                
                printf("%c", gTo[match][0]);
                i+=2;
                continue;
            }
        }
        
        printf("%c", gStr[i]);        
    }
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
    char fn[] = "uri.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    test();

    strcpy(gFrom[0], "0");
    strcpy(gTo[0], " ");
    
    strcpy(gFrom[1], "1");
    strcpy(gTo[1], "!");
    
    strcpy(gFrom[2], "4");
    strcpy(gTo[2], "$");
    
    strcpy(gFrom[3], "5");
    strcpy(gTo[3], "%");
    
    strcpy(gFrom[4], "8");
    strcpy(gTo[4], "(");
    
    strcpy(gFrom[5], "9");
    strcpy(gTo[5], ")");

    strcpy(gFrom[6], "a");
    strcpy(gTo[6], "*");

    // handling input
    int count, p,j,k, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%s", &gStr);        
        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

