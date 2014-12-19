// each person want to have present. gN:Person, gM:#Present, find maximum possible matching
// input example:
// 2 2 5 : person1 have 2 favorite presents. 2 5
// category: maxflow

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
int gN, gM;
int gMat[401][401];
bool gVisited[401];


bool dfs(int here){
    if (gVisited[here])
        return false;            
    gVisited[here]=true;        

    if (here>=201){
        bool first=true;
        for (int i = 1; i <= gN; i++){
            if (gMat[here][i]==-1)
                continue;
            
            first=false;
            if (dfs(i)){
                gMat[here][i]=-1;
                gMat[i][here]= 1;
                return true;
            }
        }
        if(first){
            return true;
        }
    }else{
        for (int i = 200+1; i <= 200+gM; i++){
            if (gMat[here][i]==-1)
                continue;
            if (dfs(i)){
                gMat[here][i] =-1;
                gMat[i][here] =1;
                return true;
            }
                
        }
    }    

    return false;
}

void solve(){
    int flow =0;
    for (int i = 1; i <= gN; i++){
        memset(gVisited, 0, sizeof(gVisited));
        if (dfs(i)){
            //i--;
            flow++;
        }
    }
    printf("%d\n", flow);
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
    char fn[] = "christmasGift.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    test();

    // handling input
    int count, p,j,k, i;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d %d", &gN, &gM);

        memset(gMat, -1, sizeof(gMat));
        for (int i = 1; i <= gN; i++){
            int c;
            scanf("%d", &c);
            for (int j = 0; j < c; j++){
                int p;
                scanf("%d", &p);
                gMat[i][p+200] =1;
            }
        }
        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

