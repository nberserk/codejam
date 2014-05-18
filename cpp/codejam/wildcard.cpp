// algospot
// wildcard
// http://algospot.com/judge/problem/read/WILDCARD

#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;
bool gDebug;
char p[101];
char s[101];
int cache[101][101];
int P, S;

bool solve2(int idxP, int idxS){
    
    if (idxP==P && idxS == S) {
        return true;
    }
    
    if (idxP >= P && idxS >=S) {
        return false;
    }
    
    int& ret = cache[idxP][idxS];
    if (ret!=-1) {
        printf("hit cache %d,%d = %d\n", idxP, idxS, ret);
        return ret==1;
    }
    
    if ( p[idxP] == '*') {
        ret = 0;
        int n = S - idxS;
        for (int i=0; i<=n; i++) {
            if (solve2(idxP+1, idxS + i )){
                ret = 1;
                break;
            }
        }
    }else if (p[idxP] == '?'){
        if (solve2(idxP+1, idxS+1))
            ret = 1;
        else
            ret = 0;
    }else{
        ret =0;
        if (p[idxP] == s[idxS]) {
            if (solve2(idxP+1, idxS+1)) {
                ret =1;
            }
        }
    }
    
    return ret==1;
}


bool solve(int idxP, int idxS){
    
    if (idxP==P && idxS == S) {
        return true;
    }
    
    if (idxP >= P && idxS >=S) {
        return false;
    }
    
    if (cache[idxP][idxS]!=-1) {
        printf("hit cache %d, %d\n", idxP, idxS);
        return false;
    }
    
    if ( p[idxP] == '*') {
        int n = S - idxS;
        for (int i=0; i<=n; i++) {
            if (solve(idxP+1, idxS + i ))
                return true;
        }
    }else if (p[idxP] == '?'){
        if (solve(idxP+1, idxS+1))
            return true;
    }else{
        if (p[idxP] == s[idxS]) {
            if (solve(idxP+1, idxS+1)) {
                return true;
            }
        }
    }

    cache[idxP][idxS] = 0;
    return false;
}



int main(){
    char fn[] = "wildcard.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }
    
    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }
    
    int problemCount, idxProblem,y;
    scanf("%d", & problemCount);
    vector<string>  ss;
    int size,j;

    for (idxProblem=0; idxProblem<problemCount; idxProblem++) {
        scanf("%s", p );
        P = strlen(p);
        int n;
        scanf("%d", &n );
        
        ss.clear();
        for (y=0; y<n; y++) {
            scanf ("%s", s);
            S = strlen(s);
            
            memset(cache, -1, sizeof(cache));
            bool ret = solve2(0,0);
            // reset cache
            
            if (ret) {
                ss.push_back(string(s));
            }
        }
        
        std::sort(ss.begin(), ss.end());
        size = ss.size();
        for(j=0;j<size;j++){
            printf("%s\n",ss[j].c_str()   );
        }
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}


