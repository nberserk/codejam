// algospot
// trianglepath
// http://algospot.com/judge/problem/read/TRIANGLEPATH

#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;
bool gDebug;
int N;
int m[100][100];
int c[100][100];

int solve(int row, int col){
    
    if (row>=N) {
        return 0;
    }

    int& ret = c[row][col];
    if (ret!=-1) {
        //printf("hit cache %d,%d\n", row, col);
        return ret;
    }
    int r1 = solve(row+1, col);
    int r2 = solve(row+1, col+1);

    ret = max(r1,r2) +m[row][col];
//    printf("%d,%d = %d\n", row, col, ret);
    return ret;
}



int main(){
    char fn[] = "trianglepath.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }
    
    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    int problemCount, idxProblem,j,k;
    scanf("%d", & problemCount);
    int size;

    for (idxProblem=0; idxProblem<problemCount; idxProblem++) {
        scanf("%d", &N );
        for (j=0; j<N; j++) {
            for (k=0; k<j+1; k++) {
                scanf("%d", &m[j][k]);
            }            
        }
        memset(c, -1, sizeof(c));
        int  ret = solve(0,0);
        printf("%d\n", ret  );
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


