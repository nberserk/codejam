// http://algospot.com/judge/problem/read/NUMB3RS

#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <time.h>
#include <string.h>
#include <stack>
#include <limits>
#include <set>


using namespace std;
#define H_MAX 987654321
#define H_MIN -987654321
#define H_MOD 10000000
typedef long long ll;

void check(bool ret);

bool gDebug;
char gN, gD, gT[50];
char gPriority[5];
int gCache[100][101];


void initCache(){
//    memset(gCache, -1, sizeof(gCache));
}


double solve(){
    
    int n = strlen(gS);
    stack<int> s;
    char c,p;
    for(int i=0;i<n;i++){
        c = gS[i];
        if(c=='{' || c=='<' || c=='(' || c=='['){
            s.push(i);
        }else{
            int idxOpen  = s.top();            s.pop();
            p = gS[idxOpen];
            if(p=='{' && c=='}'){
            }else if(p=='<' && c=='>'){
            }else if(p=='(' && c==')'){
            }else if(p=='[' && c==']'){
            }else{
                // fix
                int pOpen = getPriority(p);
                char pairCurrent = getPairOf(c);
                int pClose = getPriority(pairCurrent);
                if(pOpen<pClose){
                    gS[i] = getPairOf(p);
                }else{
                    gS[idxOpen] = getPairOf(c);
                }
            }
            
        }
    }

    if(s.size()>0)
        printf("strange\n");
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


void test(){
   
}


int main(){
    char fn[] = "numb3rs.in";
    if (access(fn, F_OK)!=-1) {
        gDebug = true;
    }

    FILE *fp;
    if (gDebug) {
        fp = freopen(fn, "r", stdin);
    }

    initCache();
    //test();
    
    int count, p,j,k;
    scanf("%d", & count);

    for (p=0; p<count; p++) {
        scanf("%d %d %d", gN, gD, gStartIndex);
        for(j=0;i<gN;j++){
            for(k=0;k<gN;k++){
                scanf("%d", &gM[j][k] );                
            }
        }

        int t;
        scanf("%d", &t);
        for(j=0;i<gN;j++){
            scanf("%d", gT+j);
        }
                
        solve();
        printf("%s\n", gS);
    }
    
    if (gDebug) {
        fclose(fp);
    }
    return 0;
    
}

