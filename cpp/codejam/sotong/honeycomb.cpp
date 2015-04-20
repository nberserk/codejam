#include <algorithm>
#ifdef _MSC_VER
#include <io.h>
#define F_OK 0
#else
#include <unistd.h>
#endif
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
typedef long long ll;

bool gDebug;
int gN;
int gn[200][200];
int gMax[200];
int gCache[200][200][2];

int findMax(int r, int c, int chance, int rowLength){
    if (r==2*gN-1 || c<0 || c>=rowLength){
        return 0;
    }
    int& ret = gCache[r][c][chance];
    if (ret!=-1){
        return ret;
    }

    int d =1;
    if (r>=gN-1){
        d = -1;
    }
    
    if (chance==1){
        ret = max(ret, findMax(r+1, c, 0, rowLength+d)+ gMax[r]);
        ret = max(ret, findMax(r+1, c+d, 0, rowLength+d) + gMax[r]);
    }
    ret = max(ret, findMax(r+1, c, chance, rowLength+d) + gn[r][c]);
    ret = max(ret, findMax(r+1, c+d, chance, rowLength+d) + gn[r][c]);

    //printf("%d-%d-%d=%d\n", r, c, chance, ret);
    return ret;
}

void solve(){
    memset(gCache, -1 , sizeof(gCache));
    int ret = -1;
    for (int i=0; i<gN; i++) {
        int c = findMax(0, i, 1, gN);
        ret = max(ret, c);
    }
    printf("%d\n", ret);
}

void test(){
}

int main(){
    string fn = __FILE__;
    size_t pos = fn.find(".cpp");
    fn = fn.substr(0,pos) + ".txt";    
    if (access(fn.c_str(), F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn.c_str(), "r", stdin);
    }
    
    //test();
    //h_generateRandomMap(100);
    
    // handling input
    int count, p,j,k, i,n;
    scanf("%d", &count);
    for (p=0; p<count; p++) {        
        scanf("%d", &gN);

        int line = gN;
        int m= gN+gN-1;
        int offset = 1;
        for (int i = 0; i < gN+gN-1; i++){
            int v = -1;
            for (int j = 0; j < line; j++){
                scanf("%d", &gn[i][j]);
                //printf("%d ", gn[i][j]);
                v = max(v, gn[i][j]);
            }
            gMax[i] =v;
            if (line==m){
                offset = -1;
            }
            line += offset;
            //printf("\n");
        }        
        solve();
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

