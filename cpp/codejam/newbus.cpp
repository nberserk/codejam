// http://algospot.com/judge/problem/read/NEWBUS
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
#define H_MAX 87654321
#define H_MIN -987654321
#define H_EPSILON 0.000001
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
int gN;
int gMat[101][101];
int gCount[101][101];
vector<pair<int, int   >> gQuestion;



void floyd(){
    for (int i=1; i<=gN; i++) {
        gMat[i][i] = 0;
    }
    
    for (int k=1; k<=gN; k++) {
        for (int i=1; i<=gN; i++) {
            for (int j=1; j<=gN; j++) {
                int n = gMat[i][k]+gMat[k][j];
                if (n>=H_MAX)
                    continue;
                if (n<gMat[i][j]) {
                    gCount[i][j] = 1;
                    gMat[i][j] = n;
                }else if (n==gMat[i][j]){
                    gCount[i][j] ++;
                }
            }
        }
    }
    
    
    for (int i=0; i<gQuestion.size(); i++) {
        int from = gQuestion[i].first;
        int to = gQuestion[i].second;
        int v = gCount[from][to];
        if (gMat[from][to]>100000)
            printf("no\n");
        else if(v==2)
            printf("only\n");
        else
            printf("many\n");
    }
}


void test(){
}

int main(){
    char fn[] = "newbus.in";
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
        int m, q;
        scanf("%d %d %d", &gN, &m, &q);
        
        for (int j=1;j<=gN; j++){
            for (int k=1; k<=gN; k++) {
                gMat[j][k] = H_MAX;
            }
        }
        memset(gCount, 0, sizeof(gCount));
        for (int j = 0; j < m; j++){
            int from , to, distance;
            scanf("%d %d %d", &from, &to, &distance);
            gMat[from][to] = distance;
            gMat[to][from] = distance;
        }

        gQuestion.clear();
        for (int j = 0; j < q; j++){
            int from , to;
            scanf("%d %d", &from, &to);
            gQuestion.push_back(make_pair(from, to));
        }
        floyd();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}





