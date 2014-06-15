// algospot
// LIS , longest increasing subsequence
// http://algospot.com/judge/problem/read/LIS

#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <string>
#include <time.h>
#include <string.h>

using namespace std;
bool gDebug;
int N;
int s[500];
int cache2[501];
int cache[500][500];
int gParent[501];

int lis(int idxMax, int pos){
    while (idxMax!=-1 &&  pos<N && s[idxMax]>=s[pos]) {
        pos++;
    }
    if (pos>=N) {
        return 0;
    }
    
    int ret;
    if(idxMax!=-1){
        ret = cache[idxMax][pos];
        if (ret!=-1) {
            //printf("hit cache %d-%d\n", idxMax, pos);
            return ret;
        }
    }
    
    ret = max( 1+ lis(pos, pos+1), lis(idxMax, pos+1) );
    return ret;
}

int lis2(int start){
    if (start>=N) {
        return 0;
    }
    
    int& ret = cache2[start+1];
    if (ret!=-1) {
       // printf("hit cache %d =%d\n", start,ret);
        return ret;
    }
    
    ret  = 1;
    int cur;
    for (int i=start+1; i<N; i++) {
        if (start==-1 ||s[start]< s[i]) {
            cur = 1+lis2(i);
            if(ret < cur){
                gParent[start+1] = i;
                ret = cur;
            }            
        }
    }
    printf("%d=%d, %d\n", start, ret, gParent[start+1]);
    return ret;
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
    char fn[] = "lis.in";
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
        scanf("%d", &N);
        for (j=0; j<N; j++) {
            scanf("%d", &s[j]);
        }

        //memset(cache,-1, sizeof(cache));
        memset(cache2,-1, sizeof(cache2));
        memset(gParent, -1, sizeof(gParent));
        //mark();
        //int maxLis = lis(-1, 0);
        int maxLis2 = lis2(-1);

        // reconstruct
        vector<int>  result;
        int cur=gParent[0];
        while(cur!=-1){
            result.push_back(s[cur]);
            cur = gParent[cur+1];
        }
        
        maxLis2 --;
//        if (maxLis != maxLis2) {
//            printf("strange");
//        }
        //endMark();
        for(j=0;j<result.size(); j++){
            printf("%d, ", result[j]);
        }
        printf(" --> %d \n", maxLis2);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


