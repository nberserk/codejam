// http://algospot.com/judge/problem/read/TICTACTOE
// category: dp

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
char gMap[3][4];

int winner(char m[3][4], int turn){
    int ret = -1;
    char symbol = turn==0?'x':'o';
    // win ?
    for (int i = 0; i < 3; i++){        
        if (m[i][0] == symbol && m[i][1]==symbol && m[i][2]==symbol)
            return turn;
        if (m[0][i] == symbol && m[1][i]==symbol && m[2][i]==symbol)
            return turn;        
    }
    if (m[1][1]==symbol){
        if (m[0][0]==symbol && m[2][2] ==symbol){
            return turn;
        }
        if (m[0][2]==symbol && m[2][0] ==symbol){
            return turn;
        }
    }

    // block
    symbol = turn==1?'o':'x';
    int count =0;
    for (int i = 0; i < 3; i++){
        count=0;
        int idx = -1;
        if (m[i][0] == symbol){
            count ++;
        }else{
            idx=0;
        }
            
        if (m[i][1]==symbol)
            count ++;
        else
            idx = 1;
        if (m[i][2]==symbol)
            count ++;
        else idx=2;

        if (count >=2){
            
        }        
            
        if (m[0][i] == symbol && m[1][i]==symbol && m[2][i]==symbol)
            return turn;        
    }
    if (m[1][1]==symbol){
        if (m[0][0]==symbol && m[2][2] ==symbol){
            return turn;
        }
        if (m[0][2]==symbol && m[2][0] ==symbol){
            return turn;
        }
    }
    

    
    
    
}

void solve(){
    int turn =0;                // x=0, 1=o
    int nth=0;
    for (int i = 0; i < 3; i++){
        for (int j = 0; j < 3; j++){
            if (gMap[i][j]!= '.'){
                nth++;
            }
        }
    }
    if (nth%2==1){
        turn =1;
    }

    int ret = winner(gMap, 0);
    printf("%d\n", ret);        
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
    char fn[] = "tictactoe.in";
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
        scanf("%s", gMap);
        scanf("%s", gMap+1);
        scanf("%s", gMap+2);

        solve();        
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

