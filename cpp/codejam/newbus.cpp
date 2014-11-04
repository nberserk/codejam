// http://algospot.com/judge/problem/read/NEWBUS
// category: graph

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
int gMat[101][101];
int gDist[101][101];

void floyd(){
    for (int k=1; k<=gN; k++) {
        for (int i=1; i<=gN; i++) {
            gDist[k][i] = H_MAX;
            if (gMat[k][i]!=-1  ) {
                gDist[k][i] = gMat[k][i];
            }
        }
    }
    for (int i=1; i<=gN; i++) {
        gDist[i][i] = 0;
    }
    
    for (int k=1; k<=gN; k++) {
        for (int i=1; i<=gN; i++) {
            for (int j=1; j<=gN; j++) {
                int n = gDist[i][k]+gDist[k][j];
                if (n<gDist[i][j]) {
                    //gCount[i][j] = 1;
                    gDist[i][j] = n;
                }
//                else if (n==gMat[i][j]){
//                    gCount[i][j] ++;
//                }
            }
        }
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
        memset(gMat, -1, sizeof(gMat));
        for (int j = 0; j < m; j++){
            int from , to, distance;
            scanf("%d %d %d", &from, &to, &distance);
            gMat[from][to] = distance;
            gMat[to][from] = distance;
        }

        floyd();
        for (int j = 0; j < q; j++){
            int from , to;
            scanf("%d %d", &from, &to);

            if (gDist[from][to]>=H_MAX){
                printf("no\n");
            }else{
                int count=0;
                for (int k=1; k<=gN; k++) {
                    if (gMat[from][k]==-1)
                        continue;
                    if (gMat[from][k] + gDist[k][to]==gDist[from][to]) {
                        count++;
                        if (count==2){
                            break;
                        }
                    }
                }
                if(count==1)
                    printf("only\n");
                else
                    printf("many\n");
            }
            
        }

    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}





