// algospot
// JLIS , joined longest increasing subsequence
// http://algospot.com/judge/problem/read/JLIS

#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string.h>
#include <limits>

using namespace std;
bool gDebug;
int N,M;
int A[100], B[100];
int cache[101][101];

typedef long long ll;
const ll NEGINF = numeric_limits<long long>::min();

int jlis2(int a, int b){

    int& ret = cache[a+1][b+1];
    if(ret!=-1){
        return ret;
    }
    
    ll sa = a ==-1 ? NEGINF : A[a] ;
    ll sb = b==-1? NEGINF : B[b];
    ll maxV = max(sa,sb);

    ret =2;
//    if(sa==sb)
//        ret = 1;

    int i;
    for(i=a+1;i<N;i++){
        if(maxV < A[i])
            ret = max(ret, 1+jlis2(i, b));
    }

    for(i=b+1;i<M;i++){
        if(maxV < B[i])
            ret = max(ret, 1+jlis2(a, i));
    }
    //printf("%d-%d=%d\n", a, b,ret);
    return ret;
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
        int maxLis = jlis2(-1,-1);
        //endMark();
        printf("%d\n", maxLis-2);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


