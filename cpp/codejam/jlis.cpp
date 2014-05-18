// algospot
// JLIS , joined longest increasing subsequence
// http://algospot.com/judge/problem/read/JLIS

#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string.h>

using namespace std;
bool gDebug;
int N,M;
int A[100], B[100];
int cache[101][101];

// from -1 to the end , find jlis of two set
int jlis(int idxA, int idxB, int curMax){
    // move A
    while (curMax!=-1 && idxA < N-1 && curMax>A[idxA+1]) {
        idxA++;
    }
    
    // move B
    while (curMax!=-1 && idxB < N-1 && curMax>B[idxB+1]) {
        idxB++;
    }
    
    if (idxA==N && idxB==M) {
        return 0;
    }
    
    int& ret = cache[idxA+1][idxB+1];
    if (ret!=-1) {
        return ret;
    }
    
    if (idxB==M )
        // take A
        ret =  max(1+jlis(idxA+1, idxB, A[idxA+1]), jlis(idxA+1, idxB, curMax));
    }else if (idxA==M){
        ret = max (1 + jlis(idxA, idxB+1, B[idxB+1]), jlis(idxA, idxB+1,curMax));
    }else if( A[idxA+1] > B[idxB+1]){
        ret = 1 + jlis(idxA, idxB+1, B[idxB+1]);
    }else if (A[idxA+1] < B[idxB+1]) {
    }else if (A[idxA+1] == B[idxB+1]){
        ret = 1 + jlis(idxA+1, idxB+1, A[idxA+1]);
    }
    
    return ret ;
}

clock_t gMarkTime;
void mark(){
    gMarkTime = clock();
}

void endMark(){
    gMarkTime = clock() - gMarkTime;
    int ms = double(gMarkTime) / CLOCKS_PER_SEC * 1000;
    printf("elapsed time : %d\n",  ms);
}

int main(){
    char fn[] = "jlis.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    int count, p,j;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%d %d", &N, &M);
        for (j=0; j<N; j++) {
            scanf("%d", A+j);
        }
        
        for (j=0; j<M; j++) {
            scanf("%d", B+j);
        }

        memset(cache,-1, sizeof(cache));
        //mark();
        int maxLis = jlis(-1, -1, -1);
        //endMark();
        printf("%d\n", maxLis);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


