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
    // find A greater than curMax
    while (idxA==-1 || (idxA < N && A[idxA]<=curMax) ) {
        idxA++;
    }
    
    // move B greater than curMax
    while (idxB==-1 || (idxB < M && B[idxB]<=curMax)) {
        idxB++;
    }
    
    if (idxA>=N && idxB>=M) {
        return 0;
    }
    
    int& ret = cache[idxA+1][idxB+1];
    if (ret!=-1) {
        //printf("hit cache;%d-%d=%d(%d)\n", idxA, idxB,ret, curMax);
        return ret;
    }
    
    ret =-1;
    if (idxA!=N ){
        ret =  max(ret, 1+jlis(idxA+1, idxB, A[idxA])); // take a
        ret = max (ret, jlis(idxA+1, idxB, curMax)); // skip a
    }
    if (idxB!=M){
        ret = max (ret, 1 + jlis(idxA, idxB+1, B[idxB])); // take b
        ret = max(ret, jlis(idxA, idxB+1, curMax)); //skip b
    }
    
    //printf("%d-%d=%d(%d)\n", idxA, idxB,ret, curMax);
    return ret ;
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


